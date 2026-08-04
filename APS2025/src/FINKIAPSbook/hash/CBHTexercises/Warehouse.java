package FINKIAPSbook.hash.CBHTexercises;

import java.io.*;
import java.util.HashMap;

class Medicine {
    String name;
    String purpose;
    int price;

    Medicine(String name, String purpose, int price) {
        this.name = name;
        this.purpose = purpose;
        this.price = price;
    }

    @Override
    public String toString() {
        return name;
    }
}

public class Warehouse {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Medicine> medicines = new HashMap<>();

        int n = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            String[] tokens = line.split("@");
            String name = tokens[0];
            String purpose = tokens[1];
            int price = Integer.parseInt(tokens[2]);
            Medicine medicine = new Medicine(name, purpose, price);
            if (!medicines.containsKey(purpose)){
                medicines.put(purpose, medicine);
            } else if (medicines.get(purpose).price > price) {
                medicines.put(purpose, medicine);
            }
        }

        String query = br.readLine();
        System.out.println(medicines.get(query).toString());
    }
}
