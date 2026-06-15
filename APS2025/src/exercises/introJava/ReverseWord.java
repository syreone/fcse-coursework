package exercises.introJava;

import java.util.Scanner;

public class ReverseWord {

    public static void printReversed(String word) {
        for (int i = word.length() - 1; i >= 0; i--) {
            System.out.print(word.charAt(i));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                if (sc.hasNext()) {
                    String word = sc.next();
                    printReversed(word);
                }
            }
        }
        sc.close();
    }
}