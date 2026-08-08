package FINKIAPSbook.hash.OBHTexercises;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Company {

    static Map<String, List<String>> bosses = new HashMap<>();
    static Map<String, Integer> employeeCount = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        for (int i = -0; i < n; i++) {
            String line = br.readLine();
            String[] tokens = line.split(" ");
            String employee = tokens[0];
            String boss = tokens[1];
            bosses.putIfAbsent(boss, new ArrayList<>());
            bosses.get(boss).add(employee);
        }

        for (String boss : bosses.keySet()) {
            countEmployees(boss);
        }

        ArrayList<String> result = new ArrayList<>(bosses.keySet());
        Collections.sort(result);

        for (String boss : result) {
            System.out.println(boss + ": " + employeeCount.get(boss));
        }
    }

    private static int countEmployees(String boss) {
        if (employeeCount.containsKey(boss)) {
            return employeeCount.get(boss);
        }

        int count = 0;
        if (bosses.containsKey(boss)) {
            for (String employee : bosses.get(boss)) {
                if (boss.equals(employee)) {
                    continue;
                }
                count += 1 + countEmployees(employee);
            }
        }
        employeeCount.put(boss, count);
        return count;
    }
}
