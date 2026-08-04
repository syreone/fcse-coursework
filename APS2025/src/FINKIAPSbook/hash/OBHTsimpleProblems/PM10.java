package FINKIAPSbook.hash.OBHTsimpleProblems;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PM10 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashMap<String, ArrayList<Double>> hashtable = new HashMap<>(2*n);

        for (int i=0; i<n; i++) {
            String input = br.readLine();
            String[] row = input.split(" ");
            String neighbourhood = row[0];
            double pm10 = Double.parseDouble(row[1]);

            hashtable.putIfAbsent(neighbourhood,new ArrayList<>());
            hashtable.get(neighbourhood).add(pm10);
        }

        String neighbourhoodSearch = br.readLine().trim();
        ArrayList<Double> result = hashtable.get(neighbourhoodSearch);

        if (result != null && !result.isEmpty()) {
            double sum = 0;
            for (double val : result){
                sum += val;
            }
            System.out.printf("%.2f", sum/result.size());
        } else {
            System.out.println("No info");
        }
    }
}
