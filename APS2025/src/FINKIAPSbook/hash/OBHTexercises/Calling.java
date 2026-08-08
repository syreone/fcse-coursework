package FINKIAPSbook.hash.OBHTexercises;

import java.io.*;
import java.util.HashMap;

public class Calling {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, String> dialingCodes = new HashMap<>();

        int n = Integer.parseInt(br.readLine().trim());
        for (int i=0; i<n; i++) {
            String line = br.readLine();
            String[] tokens = line.split(" ");
            String code = tokens[0];
            String country = tokens[1];
            if (!dialingCodes.containsKey(code)){
                dialingCodes.put(code, country);
            }

            String query = br.readLine();
            char howManyDigits = query.charAt(1);
            if (howManyDigits == '1') {
                query = query.substring(1, 2);
            } else if (howManyDigits == '2') {
                query = query.substring(1, 3);
            } else if (howManyDigits == '3') {
                query = query.substring(1, 4);
            }

            System.out.println(dialingCodes.get(query));
        }
    }
}
