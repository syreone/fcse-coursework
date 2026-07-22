package FINKINPBook.ReadWrite;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Item {
    private String name;
    private int price;

    public Item(String name) {
        this.name = name;
        this.price = 0;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

class Order implements Comparable<Order> {
    private int id;
    private List<Item> items;

    public Order() {
        this.id = -1;
        items = new ArrayList<>();
    }

    public Order(int id, List<Item> items) {
        this.id = id;
        this.items = items;
    }

    public static Order createOrder(String line) {
        String[] parts = line.split("\\s+");
        int orderId = Integer.parseInt(parts[0]);
        List<Item> items = new ArrayList<>();
        Item item = null;
        for (int i = 1; i < parts.length; i++) {
            if (i % 2 == 1) { // item name is here
                item = new Item(parts[i]);
            } else {
                item.setPrice(Integer.parseInt(parts[i]));
                items.add(item);
            }
        }
        return new Order(orderId, items);
    }

    public List<Item> getItems() {
        return items;
    }

    public int getNumberOfItems() {
        return items.size();
    }

    @Override
    public int compareTo(Order o) {
        return Integer.compare(this.items.size(), o.items.size());
    }

    @Override
    public String toString() {
        return this.id + " " + this.items.size();
    }
}

class CakeShopApplication {
    private List<Order> orders;

    public CakeShopApplication() {
        this.orders = new ArrayList<>();
    }

    public int readCakeOrders(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = br.readLine()) != null) {
            orders.add(Order.createOrder(line));
        }

        int sum = 0;
        for (Order order : orders) {
            sum += order.getNumberOfItems();
        }

        br.close();
        return sum;
    }

    public void printLongestOrder(OutputStream outputStream) {
        PrintWriter printWriter = new PrintWriter(outputStream);
        Order longestOrder = orders.get(0);

        for (Order order : orders) {
            if (order.compareTo(longestOrder) > 0) {
                longestOrder = order;
            }
        }

        printWriter.println(longestOrder);

        printWriter.flush();
        printWriter.close();
    }
}

public class CakeShopApplicationTest1 {

    public static void main(String[] args) {
        CakeShopApplication cakeShopApplication = new CakeShopApplication();

        System.out.println("--- READING FROM INPUT STREAM ---");
        try {
            System.out.println(cakeShopApplication.readCakeOrders(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("--- PRINTING LARGEST ORDER TO OUTPUT STREAM ---");
        cakeShopApplication.printLongestOrder(System.out);
    }
}
