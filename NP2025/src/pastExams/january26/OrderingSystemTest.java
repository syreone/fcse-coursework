package pastExams.january26;
import java.util.*;
import java.io.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.concurrent.CopyOnWriteArrayList;

class Product {
    String id;
    String name;
    int available;
    int price;
    int sold;

    public Product(String id, String name, int price, int available) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.available = available;
        sold = 0;
    }

    @Override
    public String toString() {
        return name + " | price=" + price + " | available=" + available + " | sold=" + sold;
    }
}

class Item {
    String id;
    String name;
    int quantity;
    int price;

    public Item(String id, String name, int quantity, int price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " x" + quantity + " (" + price + " each)";
    }
}

enum BasketStatus {
    ACTIVE, ORDERED, CANCELED
}

class Basket {
    String id;
    String userId;
    Map<String, Item> items;
    BasketStatus status;

    public Basket(String id, String userId) {
        this.id = id;
        this.userId = userId;
        this.items = new LinkedHashMap<>();
        this.status = BasketStatus.ACTIVE;
    }

    public void addItem(Item item) {
        items.put(item.id, item);
    }

    @Override
    public String toString() {
        return String.format("Basket %s (%s)\n%s", id, userId, items.values().stream().map(Item::toString).collect(Collectors.joining("\n")));
    }
}

class Order {
    String id;
    String userId;
    Map<String, Item> items;
    static int ORDER_ID = 1;

    public Order(String userId) {
        this.id = String.valueOf(ORDER_ID++);
        this.userId = userId;
        this.items = new LinkedHashMap<>();
    }

    public Order(Basket basket) {
        this.id = String.valueOf(ORDER_ID++);
        this.userId = basket.userId;
        this.items = new LinkedHashMap<>(basket.items);
    }

    int totalPrice() {
        int sum = 0;
        for (Item item : items.values()) {
            sum += item.price * item.quantity;
        }
        return sum;
    }

    @Override
    public String toString() {
        return String.format("Order %s (%s)\n%s\nTotal price: %d", id, userId, items.values().stream().map(Item::toString).collect(Collectors.joining("\n")), totalPrice());
    }
}

class OrderingSystem {
    final Map<String, Product> products = new ConcurrentHashMap<>();
    private final Map<String, Basket> baskets = new ConcurrentHashMap<>();
    private final Map<String, List<Order>> ordersByUser = new ConcurrentHashMap<>();

    void addProduct (String id, String name, int price, int available) {
        products.put(id, new Product(id, name, price, available));
    }

    void openBasket(String basketId, String userId) {
        baskets.put(basketId, new Basket(basketId, userId));
    }

    boolean addToBasket(String basketId, String productId, int quantity) {
        Basket basket = baskets.get(basketId);
        Product product = products.get(productId);
        if (basket == null || product == null || basket.status != BasketStatus.ACTIVE){
            return false;
        }
        synchronized (product) {
            if (product.available < quantity) {
                return false;
            }
            product.available -= quantity;
        }

        synchronized (basket) {
            Item existing = basket.items.get(productId);
            if (existing != null) {
                existing.quantity += quantity;
            } else {
                basket.addItem(new Item(productId, product.name, quantity, product.price));
            }
        }
        return true;
    }

    public void cancelBasket(String basketId) {
        Basket basket = baskets.get(basketId);
        if (basket == null) return;

        synchronized (basket) {
            if (basket.status != BasketStatus.ACTIVE) return;
            for (Item item : basket.items.values()) {
                Product product = products.get(item.id);
                synchronized(product) {
                    product.available += item.quantity;
                }
            }
            basket.status = BasketStatus.CANCELED;
        }
    }

    public void orderBasket(String basketId){
        Basket basket = baskets.get(basketId);
        if (basket == null) return;

        synchronized (basket) {
            if (basket.status != BasketStatus.ACTIVE) return;
            for (Item item : basket.items.values()) {
                Product product = products.get(item.id);
                synchronized (product) {
                    product.sold += item.quantity;
                }
            }
            basket.status = BasketStatus.ORDERED;
        }

        Order order = new Order(basket);
        ordersByUser.computeIfAbsent(basket.userId, k -> new CopyOnWriteArrayList<>()).add(order);
    }

    public Map<String, Integer> totalSpentByUser() {
        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<String, List<Order>> entry : ordersByUser.entrySet()) {
            int total = entry.getValue().stream().mapToInt(Order::totalPrice).sum();
            result.put(entry.getKey(), total);
        }
        return result;
    }

    public void printOrdersOfUser(String userId) {
        List<Order> orders = ordersByUser.getOrDefault(userId, Collections.emptyList());
        orders.stream().sorted((a,b) -> b.totalPrice() - a.totalPrice()).forEach(System.out::println);
    }

    public void basketStatsPerUsers() {
        Map<String, int[]> stats = new TreeMap<>();
        for (Basket b : baskets.values()) {
            int[] counts = stats.computeIfAbsent(b.userId, k -> new int[3]);
            switch (b.status) {
                case ACTIVE -> counts[0]++;
                case CANCELED -> counts[1]++;
                case ORDERED -> counts[2]++;
            }
        }
        for (Map.Entry<String, int[]> e : stats.entrySet()) {
            int [] c = e.getValue();
            System.out.println(e.getKey() + ": active=" + c[0] + ", canceled=" + c[1] + ", ordered=" + c[2]);
        }
    }

    public Product getProduct(String productId) {
        return products.get(productId);
    }
}

/* ===================== TEST ===================== */

public class OrderingSystemTest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        OrderingSystem system = new OrderingSystem();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            String command = parts[0];

            switch (command) {

                case "ADD_PRODUCT": {
                    String id = parts[1];
                    String name = parts[2];
                    int price = Integer.parseInt(parts[3]);
                    int available = Integer.parseInt(parts[4]);
                    system.addProduct(id, name, price, available);
                    break;
                }

                case "OPEN_BASKET": {
                    String basketId = parts[1];
                    String userId = parts[2];
                    system.openBasket(basketId, userId);
                    break;
                }

                case "ADD_TO_BASKET": {
                    String basketId = parts[1];
                    String productId = parts[2];
                    int quantity = Integer.parseInt(parts[3]);

                    system.addToBasket(basketId, productId, quantity);

                    break;
                }

                case "CANCEL_BASKET": {
                    String basketId = parts[1];
                    system.cancelBasket(basketId);
                    break;
                }

                case "ORDER_BASKET": {
                    String basketId = parts[1];
                    system.orderBasket(basketId);
                    break;
                }

                case "GET_PRODUCT": {
                    System.out.println(system.getProduct(parts[1]));
                    break;
                }

                case "PRINT_ORDERS": {
                    String userId = parts[1];
                    System.out.println("Orders of user " + userId + ":");
                    system.printOrdersOfUser(userId);
                    break;
                }

                case "TOTAL_SPENT": {
                    system.totalSpentByUser().entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
                    break;
                }

                case "BASKET_STATS": {
                    system.basketStatsPerUsers();
                    break;
                }
                case "CONCURRENT_VERIFY": {
                    int threads = Integer.parseInt(parts[1]);
                    int iterations = Integer.parseInt(parts[2]);
                    String productId = parts[3];

                    // initial stock snapshot (test-side)
                    int initialStock = system.products.get(productId).available;

                    // test-side counters (NO system involvement)
                    AtomicInteger adds =
                            new AtomicInteger(0);
                    AtomicInteger cancels =
                            new AtomicInteger(0);
                    AtomicInteger orders =
                            new AtomicInteger(0);

                    ExecutorService pool = Executors.newFixedThreadPool(threads);

                    for (int t = 0; t < threads; t++) {
                        final int threadId = t;

                        pool.submit(() -> {
                            for (int i = 0; i < iterations; i++) {
                                String basketId = "B_" + threadId + "_" + i;
                                String userId = "U_" + threadId;

                                system.openBasket(basketId, userId);

                                int q = 1 + (int) Math.ceil((Math.random() * 100));

                                boolean added = system.addToBasket(basketId, productId, q);
                                if (!added) {
                                    continue;
                                }

                                // track successful reservation
                                adds.addAndGet(q);

                                if (Math.random() < 0.9) {
                                    system.cancelBasket(basketId);
                                    cancels.addAndGet(q);
                                } else {
                                    system.orderBasket(basketId);
                                    orders.addAndGet(q);
                                }
                            }
                        });
                    }

                    pool.shutdown();
                    try {
                        pool.awaitTermination(1, TimeUnit.MINUTES);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Product p = system.products.get(productId);

                    int expectedReserved =
                            adds.get() - cancels.get() - orders.get();

                    int expectedAvailable =
                            initialStock - adds.get() + cancels.get();

                    boolean ok = true;

                    if (p.available != expectedAvailable) {
                        System.out.println("FAIL ❌: available mismatch");
                        ok = false;
                    }
                    if (p.sold != orders.get()) {
                        System.out.println("FAIL ❌: sold mismatch");
                        ok = false;
                    }
                    if (expectedReserved < 0) {
                        System.out.println("FAIL ❌: negative reserved (test logic error)");
                        ok = false;
                    }
                    if (p.available + p.sold + expectedReserved != initialStock) {
                        System.out.println("FAIL ❌: stock invariant violated");
                        ok = false;
                    }

                    if (ok) {
                        System.out.println("PASS ✅: state is consistent");
                    }

                    System.out.println("--------------------------------");

                    break;
                }




                case "END":
                    return;


                default:
                    System.out.println("Unknown command: " + command);
            }
        }
    }
}

