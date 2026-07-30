package FINKIAPSbook.hash.CBHTexercises;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;

public class Phonebook {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, String> Phonebook = new HashMap<String, String>();

        int n = Integer.parseInt(br.readLine().trim());
        for (int i=0; i<n; i++){
            String line = br.readLine();
            String[] token = line.split(" ");
            String phone = token[0];
            String name = token[1];
            Phonebook.put(phone, name);
        }

        String query = br.readLine();
        if (query.charAt(0) == '+'){
            query = "0" + query.substring(4);
            System.out.println(Phonebook.getOrDefault(query, "Unknown number"));
        } else {
            System.out.println(Phonebook.getOrDefault(query, "Unknown number"));
        }
    }
}
