package FINKIAPSbook.hash.CBHTadvancedProblems;

import FINKIAPSbook.hash.CBHT;
import FINKIAPSbook.hash.MapEntry;
import FINKIAPSbook.hash.SLLNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InfluenzaRiskFactor {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        CBHT<String, Integer> positivePatients = new CBHT<>(100);
        CBHT<String, Integer> negativePatients = new CBHT<>(100);

        for (int i = 0; i < n; i++) {
            String input[] = br.readLine().split(" ");
            if (input[2].equals("positive")) {
                SLLNode<MapEntry<String, Integer>> positiveRes = positivePatients.search(input[0]);

                if (positiveRes == null) {
                    positivePatients.insert(input[0], 1);
                } else {
                    Integer numPositive = positiveRes.element.value + 1;
                    positivePatients.insert(input[0], numPositive);
                }
            } else {
                SLLNode<MapEntry<String, Integer>> negativeRes = negativePatients.search(input[0]);

                if (negativeRes == null) {
                    negativePatients.insert(input[0], 1);
                } else {
                    Integer numNegative = negativeRes.element.value + 1;
                    positivePatients.insert(input[0], numNegative);
                }
            }
        }

        String municipality = br.readLine();
        SLLNode<MapEntry<String, Integer>> positiveRes = positivePatients.search(municipality);
        SLLNode<MapEntry<String, Integer>> negativeRes = negativePatients.search(municipality);

        Integer positiveCount = positiveRes.element.value;
        Integer negativeCount = negativeRes.element.value;

        System.out.println(String.format("%.2f", positiveCount * 1.00 / (negativeCount + positiveCount)));

    }
}
