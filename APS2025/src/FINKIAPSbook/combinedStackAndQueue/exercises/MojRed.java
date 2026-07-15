package FINKIAPSbook.combinedStackAndQueue.exercises;

import FINKIAPSbook.combinedStackAndQueue.ArrayStack;
import java.util.*;

public class MojRed {
    private ArrayStack<Integer> inStack;
    private ArrayStack<Integer> outStack;
    private int size;

    public MojRed(int capacity) {
        this.inStack = new ArrayStack<>(capacity);
        this.outStack = new ArrayStack<>(capacity);
        this.size = 0;
    }

    public void enqueue(int x) {
        inStack.push(x);
        size++;
    }

    public Integer dequeue() {
        if (isEmpty()) return null;

        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        size--;
        return outStack.pop();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}

class MyQueueMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine().trim());
        MojRed queue = new MojRed(n + 100);

        for (int i = 0; i < n; i++) {
            int val = Integer.parseInt(scanner.nextLine().trim());
            queue.enqueue(val);
        }

        StringBuilder sb = new StringBuilder();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.equals("KRAJ")) break;
            if (line.isEmpty()) continue;

            int action = Integer.parseInt(line);
            if (action == 1) {
                Integer removed = queue.dequeue();
                if (removed != null) sb.append(removed).append("\n");
            } else if (action == 2) {
                int val = Integer.parseInt(scanner.nextLine().trim());
                queue.enqueue(val);
            }
        }

        sb.append("Goleminata na redicata e: ").append(queue.size());
        System.out.println(sb.toString());

        scanner.close();
    }
}