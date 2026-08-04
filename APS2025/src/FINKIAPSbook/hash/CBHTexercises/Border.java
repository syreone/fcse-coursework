package FINKIAPSbook.hash.CBHTexercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Border {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, String> passports = new HashMap<>();
        HashMap<String, String> namesChanged = new HashMap<>();

        int n = Integer.parseInt(br.readLine().trim());
        for (int i=0; i<n; i++) {
            String line = br.readLine();
            String[] tokens = line.split(" ");
            String passportID = tokens[0];
            String passportName = tokens[1];
            passports.put(passportID, passportName);
        }

        int m = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < m; i++){
            String line = br.readLine();
            String[] tokens = line.split(" ");
            String name = tokens[0];
            String changedName = tokens[1];
            namesChanged.put(name, changedName);
        }

        String query = br.readLine();
        String name = passports.get(query);
        if (namesChanged.containsKey(name)) {
            System.out.println("Not Allowed");
        } else {
            System.out.println("Allowed");
        }
    }
}
