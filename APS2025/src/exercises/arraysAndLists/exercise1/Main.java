package exercises.arraysAndLists.exercise1;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] a = new int[N];

        for (int i = 0; i < N; i++) {
            a[i] = sc.nextInt();
        }

        System.out.print("{");
        for (int i = 0; i < N; i++) {
            System.out.print(a[i]);
            if (i != N - 1){
                System.out.print(",");
            }
        }
        System.out.println("}");


        double sum = 0;
        for (int i = 0; i < N; i++) {
            sum += a[i];
        }
        double avg = sum / N;

        System.out.print("{");
        boolean first = true;
        for (int i = 0; i < N; i++) {
            if (a[i] >= avg) {
                if (!first) System.out.print(",");
                System.out.print(a[i]);
                first = false;
            }
        }
        System.out.println("}");
    }
}
