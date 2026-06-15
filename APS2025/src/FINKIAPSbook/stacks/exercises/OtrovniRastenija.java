package FINKIAPSbook.stacks.exercises;

import java.util.Scanner;

public class OtrovniRastenija {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] pesticide = new int[n];
        for (int i = 0; i < n; i++) {
            pesticide[i] = sc.nextInt();
        }

        int[] days = new int[n];
        int[] stack = new int[n];
        int top = -1;
        int maxDays = 0;

        for (int i = 0; i < n; i++) {
            int currentDays = 0;

            while (top >= 0 && pesticide[stack[top]] >= pesticide[i]) {
                currentDays = Math.max(currentDays, days[stack[top]]);
                top--;
            }

            if (top >= 0) {
                days[i] = currentDays + 1;
            } else {
                days[i] = 0;
            }

            maxDays = Math.max(maxDays, days[i]);
            stack[++top] = i;
        }

        System.out.println(maxDays);
        sc.close();
    }
}