package FINKIAPSbook.hash.OBHTexercises;

import java.io.*;
import java.util.HashMap;

class Street {
    String name;
    String number;

    Street(String name, String number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return name + " " + number;
    }
}

public class Santa {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Street> kidToStreet = new HashMap<>();
        HashMap<String, String> oldToNewStreet = new HashMap<>();

        int n = Integer.parseInt(br.readLine().trim());
        for (int i=0; i<n; i++) {
            String line = br.readLine();
            String[] tokens = line.split(" ");
            String kid = tokens[0];
            String name = tokens[1];
            String number = tokens[2];
            Street street = new Street(name, number);
            kidToStreet.putIfAbsent(kid, street);
        }

        int m = Integer.parseInt(br.readLine().trim());
        for (int i=0; i<m; i++) {
            String line = br.readLine();
            String[] tokens = line.split(" ");
            String oldStreet = tokens[0];
            String newStreet = tokens[1];
            oldToNewStreet.putIfAbsent(oldStreet, newStreet);
        }

        String query = br.readLine();
        if (kidToStreet.containsKey(query)) {
            Street street = kidToStreet.get(query);
            if (oldToNewStreet.containsKey(street.name)) {
                street.name = oldToNewStreet.get(street.name);
            }
            System.out.println(street);
        }
    }
}
