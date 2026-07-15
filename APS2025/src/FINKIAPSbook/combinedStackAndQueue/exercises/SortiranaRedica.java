package FINKIAPSbook.combinedStackAndQueue.exercises;

import FINKIAPSbook.combinedStackAndQueue.ArrayQueue;
import FINKIAPSbook.combinedStackAndQueue.ArrayStack;
import java.util.*;

public class SortiranaRedica {
    public static boolean canSort(ArrayQueue<Integer> input, ArrayStack<Integer> stack, ArrayQueue<Integer> output, int n) {
        int expected = 1;

        while (expected <= n) {
            if (!stack.isEmpty() && stack.peek() == expected) {
                output.enqueue(stack.pop());
                expected++;
            } else if (!input.isEmpty()) {
                stack.push(input.dequeue());
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        ArrayQueue<Integer> input = new ArrayQueue<>(n);
        ArrayStack<Integer> stack = new ArrayStack<>(n);
        ArrayQueue<Integer> output = new ArrayQueue<>(n);

        for (int i = 0; i < n; i++) {
            input.enqueue(scanner.nextInt());
        }

        boolean sortable = canSort(input, stack, output, n);
        System.out.println(sortable ? "Да" : "Не");

        scanner.close();
    }
}
