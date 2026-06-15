package FINKIAPSbook.stacks.exercises;

import java.util.Scanner;
import java.util.Stack;

public class IgriSoTopchinja {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;

        int n = sc.nextInt();
        Stack<Character> k1 = new Stack<>();
        Stack<Character> k2 = new Stack<>();
        Stack<Character> k3 = new Stack<>();

        for (int i = 0; i < n; i++) {
            char ball = sc.next().charAt(0);
            k1.push(ball);

            if (ball == 'R' && k1.size() >= 3) {
                int size = k1.size();
                if (k1.get(size - 2) == 'R' && k1.get(size - 3) == 'R') {
                    while (!k1.isEmpty() && k1.peek() == 'R') {
                        k1.pop();
                    }
                }
            }
        }

        while (!k1.isEmpty()) {
            char ball = k1.pop();
            if (ball == 'B') {
                k3.push(ball);
            } else {
                k2.push(ball);
            }
        }

        while (!k2.isEmpty()) {
            char ball = k2.pop();
            if (ball == 'G') {
                k3.push(ball);
            } else {
                k1.push(ball);
            }
        }

        while (!k1.isEmpty()) {
            k3.push(k1.pop());
        }

        Stack<Character> result = new Stack<>();
        while (!k3.isEmpty()) {
            result.push(k3.pop());
        }

        if (result.isEmpty()) {
            System.out.println("Prazna kutija");
        } else {
            while (!result.isEmpty()) {
                System.out.print(result.pop() + " ");
            }
            System.out.println();
        }
    }
}
