package additionalPracticeBeforeExam;

import java.util.*;
import java.io.*;

class Zbor implements Comparable<Zbor> {
    String zbor;

    public Zbor(String zbor) {
        this.zbor = zbor;
    }

    @Override
    public boolean equals(Object obj) {
        Zbor pom = (Zbor) obj;
        return this.zbor.equals(pom.zbor);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < zbor.length(); i++) {
            hash = hash * 31 + zbor.charAt(i);
        }
        return Math.abs(hash);
    }

    @Override
    public String toString() {
        return zbor;
    }

    @Override
    public int compareTo(Zbor arg0) {
        return zbor.compareTo(arg0.zbor);
    }
}

public class Speluvanje {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashMap<Zbor, String> tabela = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String w = br.readLine().trim().toLowerCase();
            tabela.put(new Zbor(w), w);
        }

        String tekst = br.readLine();
        String[] words = tekst.split(" ");

        List<String> misspelled = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            if (word.isEmpty()) {
                continue;
            }

            char lastChar = word.charAt(word.length() - 1);
            String core;

            if (lastChar == '.' || lastChar == ',' || lastChar == '!' || lastChar == '?') {
                core = word.substring(0, word.length() - 1);
            } else {
                core = word;
            }

            if (core.isEmpty()) {
                continue;
            }

            String lowerCore = core.toLowerCase();

            if (!tabela.containsKey(new Zbor(lowerCore))) {
                misspelled.add(core);
            }
        }

        if (misspelled.isEmpty()) {
            System.out.println("Bravo");
        } else {
            for (String w : misspelled) {
                System.out.println(w);
            }
        }
    }
}