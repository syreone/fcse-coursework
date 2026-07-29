package FINKIAPSbook.hash.CBHTadvancedProblems;
import FINKIAPSbook.hash.SLLNode;
import FINKIAPSbook.hash.CBHT;
import FINKIAPSbook.hash.MapEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Drug {

    private String name;
    private int posList;
    private int price;
    private int quantity;

    public Drug(String name, int posList, int price, int quantity) {
        this.name = name.toUpperCase();
        this.posList = posList;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosList() {
        return posList;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Drug)) {
            return false;
        }
        Drug other = (Drug) obj;
        return this.name.equals(other.name);
    }

    @Override
    public String toString() {
        String status = (posList == 1) ? "POS" : "NEG";
        return name + " " + status + " " + price + " " + quantity;
    }
}

public class Pharmacy {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        CBHT<Name, Drug> hashtable = new CBHT<>(656565);

        int drugCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < drugCount; i++) {
            String[] input = br.readLine().split(" ");
            Drug drug = new Drug(
                    input[0],
                    Integer.parseInt(input[1]),
                    Integer.parseInt(input[2]),
                    Integer.parseInt(input[3])
            );
            hashtable.insert(new Name(input[0]), drug);
        }

        String order = br.readLine().toUpperCase();

        while (!order.equals("END")) {
            int quantity = Integer.parseInt(br.readLine());
            SLLNode<MapEntry<Name, Drug>> result = hashtable.search(new Name(order));

            if (result == null) {
                System.out.println("No such drug");
            } else {
                Drug drug = result.element.value;
                System.out.println(drug.toString());

                if (drug.getQuantity() < quantity) {
                    System.out.println("No drugs available");
                } else {
                    drug.setQuantity(drug.getQuantity() - quantity);
                    hashtable.insert(new Name(order), drug);
                    System.out.println("Order made");
                }
            }

            order = br.readLine().toUpperCase();
        }
    }
}