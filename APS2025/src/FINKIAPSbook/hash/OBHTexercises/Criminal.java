package FINKIAPSbook.hash.OBHTexercises;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Criminal {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, ArrayList<String>> dna = new HashMap<>();

        int n = Integer.parseInt(br.readLine().trim());
        for (int i=0; i<n; i++) {
            String name = br.readLine();
            String dnaOne = br.readLine();
            String dnaTwo = br.readLine();
            if (!dna.containsKey(name)) {
                dna.put(name, new ArrayList<>());
                dna.get(name).add(dnaOne);
                dna.get(name).add(dnaTwo);
            } else {
                dna.get(name).add(dnaOne);
                dna.get(name).add(dnaTwo);
            }
        }

        String queryDnaOne = br.readLine();
        String queryDnaTwo = br.readLine();
        boolean found = false;

        for (String s : dna.keySet()) {
            if (dna.get(s).contains(queryDnaOne) && dna.get(s).contains(queryDnaTwo)) {
                found = true;
                System.out.println(s);
                break;
            }
        }
        if (!found) {
            System.out.println("Unknown");
        }
    }
}
