package exercises.hashing.exercise2;

import java.util.*;
import java.io.*;

class Customer {
    String name;
    String surname;
    int budget;
    String ipAddress;
    String time;
    String city;
    int price;

    Customer(String name, String surname, int budget, String ipAddress, String time, String city, int price) {
        this.name = name;
        this.surname = surname;
        this.budget = budget;
        this.ipAddress = ipAddress;
        this.time = time;
        this.city = city;
        this.price = price;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        HashMap<String, List<Customer>> table = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = br.readLine().split("\\s+");
            String name = tokens[0];
            String surname = tokens[1];
            int budget = Integer.parseInt(tokens[2]);
            String ipAddress = tokens[3];
            String time = tokens[4];
            String city = tokens[5];
            int price = Integer.parseInt(tokens[6]);

            Customer c = new Customer(name, surname, budget, ipAddress, time, city, price);

            if (table.containsKey(city)) {
                List<Customer> cityCustomers = table.get(city);
                cityCustomers.add(c);
            } else {
                List<Customer> cityCustomers = new ArrayList<>();
                cityCustomers.add(c);
                table.put(city, cityCustomers);
            }
        }

        String line = br.readLine();
        while (line != null && line.trim().isEmpty()) {
            line = br.readLine();
        }
        int m = Integer.parseInt(line.trim());

        for (int i = 0; i < m; i++) {
            String[] tokens = br.readLine().split("\\s+");
            String city = tokens[5];

            List<Customer> customersForCity = table.get(city);

            int count = 0;
            Customer maxCustomer = null;

            for (Customer cust : customersForCity) {
                if (cust.budget >= cust.price) {
                    count++;
                    if (maxCustomer == null || cust.price > maxCustomer.price) {
                        maxCustomer = cust;
                    }
                }
            }

            System.out.println("City: " + city + " has the following number of customers:");
            System.out.println(count);
            System.out.println("The user who spent the most purchasing for that city is:");
            System.out.println(maxCustomer.name + " " + maxCustomer.surname + " with salary " + maxCustomer.budget + " from address " + maxCustomer.ipAddress + " who spent " + maxCustomer.price);
            System.out.println();
        }
    }
}