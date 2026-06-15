package colloquium1.exercise4;
import java.util.Scanner;

public class Main {

    private static int najdolgaOpagackaSekvenca(int[] a) {
        int n = a.length;
        int[] longest = new int[n];

        for (int i = 0; i < n; i++) longest[i] = 1;

        int max = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (a[j] > a[i]) {
                    if (longest[j] + 1 > longest[i]) {
                        longest[i] = longest[j] + 1;
                    }
                }
            }
            if (longest[i] > max) max = longest[i];
        }

        return max;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }

        System.out.println(najdolgaOpagackaSekvenca(a));
    }
}
