package additionalPracticeBeforeExam;
import java.io.*;
import java.util.*;

public class KumanovskiDijalekt {
    public static void main (String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashMap<String, String> recnik = new HashMap<>();

        for (int i=0; i<n; i++) {
            String[] tokens = br.readLine().split(" ");
            String ku = tokens[0];
            String mk = tokens[1];

            recnik.put(ku.toLowerCase(), mk);

        }

        String tekst=br.readLine();
        String words[] = tekst.split(" ");

        for (int i = 0;  i < words.length; i++){
            String word = words[i];
            char lastChar = word.charAt(word.length() - 1);
            String core;
            String punct;

            if (lastChar == '.' || lastChar == ',' || lastChar == '!' || lastChar == '?') {
                core = word.substring(0, word.length() - 1);
                punct = word.substring(word.length() - 1);
            } else {
                core = word;
                punct = "";
            }

            String lowerCore = core.toLowerCase();

            if (recnik.containsKey(lowerCore)) {
                String translated = recnik.get(lowerCore);

                if (Character.isUpperCase(core.charAt(0))) {
                    translated = Character.toUpperCase(translated.charAt(0)) + translated.substring(1);
                } else {
                    translated = Character.toLowerCase(translated.charAt(0)) + translated.substring(1);
                }

                System.out.print(translated + punct);
            } else {
                System.out.print(word);
            }

            if (i != words.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
