package additionalPracticeBeforeExam;

import java.io.*;
import java.util.*;

public class MostFrequentSubstring {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> table = new HashMap<>();

        String word = br.readLine().trim();

        for (int start = 0; start < word.length(); start++) {
            for (int end = start + 1; end <= word.length(); end++) {
                String sub = word.substring(start, end);

                if (!table.containsKey(sub)) {
                    int count = 0;
                    int idx = 0;
                    while (true) {
                        int pos = word.indexOf(sub, idx);
                        if (pos == -1) break;
                        count++;
                        idx = pos + 1;
                    }
                    table.put(sub, count);
                }
            }
        }

        String best = null;

        for (String candidate : table.keySet()) {
            if (best == null) {
                best = candidate;
                continue;
            }

            int candidateFreq = table.get(candidate);
            int bestFreq = table.get(best);

            if (candidateFreq > bestFreq) {
                best = candidate;
            } else if (candidateFreq == bestFreq) {
                if (candidate.length() > best.length()) {
                    best = candidate;
                } else if (candidate.length() == best.length()) {
                    if (candidate.compareTo(best) < 0) {
                        best = candidate;
                    }
                }
            }
        }
        System.out.println(best);
    }
}
