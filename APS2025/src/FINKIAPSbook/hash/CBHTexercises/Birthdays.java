package FINKIAPSbook.hash.CBHTexercises;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Birthdays {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, ArrayList<String>> monthsAndNames = new HashMap<>();

        int n = Integer.parseInt(br.readLine().trim());
        for (int i=0; i<n; i++) {
            String line = br.readLine();
            String[] tokens = line.split(" ");
            String name = tokens[0];
            String dateOfBirth = tokens[1];
            int month = Integer.parseInt(dateOfBirth.split("\\.")[1]);
            if (!monthsAndNames.containsKey(month)) {
                monthsAndNames.put(month, new ArrayList<>());
                monthsAndNames.get(month).add(name);
            } else if (!monthsAndNames.get(month).contains(name)){
                monthsAndNames.get(month).add(name);
            }
        }
        int month = Integer.parseInt(br.readLine().trim());
        if (!monthsAndNames.containsKey(month)) {
            System.out.println("Empty");
        }
        else for (String name : monthsAndNames.get(month)) {
            System.out.println(name + " ");
        }
    }
}
