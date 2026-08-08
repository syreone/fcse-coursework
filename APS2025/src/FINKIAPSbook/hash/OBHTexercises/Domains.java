package FINKIAPSbook.hash.OBHTexercises;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Domains {
    public static boolean findTheDot(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '.') {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> domains = new HashMap<>();

        int n = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            String[] tokens = line.split(" ");
            int number = Integer.parseInt(tokens[0]);
            String domain = tokens[1];
            String[] parts = domain.split("\\.");
            for (String part : parts) {
                if (!domains.containsKey(part)) {
                    domains.put(part, number);
                } else {
                    domains.put(part, domains.get(part) + number);
                }
            }
        }

        ArrayList<String> webSites = new ArrayList<>();

        int m = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < m; i++) {
            String line = br.readLine();
            if (findTheDot(line)) {
                String[] tokens = line.split("\\.");
                String domain = tokens[0];
                if (!webSites.contains(domain)) {
                    webSites.add(line);
                } else if (!webSites.contains(line)) {
                    webSites.add(line);
                }
            }
        }
        for (String domain : webSites) {
            if (domains.containsKey(domain)) {
                System.out.println(domains.get(domain));
            } else {
                System.out.println("Not Found");
            }
        }
    }
}