package FINKIAPSbook.combinedStackAndQueue.exercises;

import FINKIAPSbook.combinedStackAndQueue.ArrayQueue;
import FINKIAPSbook.combinedStackAndQueue.ArrayStack;
import java.util.*;

public class StekoRed {
    private ArrayStack<Integer> stack;
    private ArrayQueue<Integer> queue;

    public StekoRed(int stackSize, int queueSize) {
        this.stack = new ArrayStack<>(stackSize);
        this.queue = new ArrayQueue<>(queueSize);
    }

    public void insert(int x) {
        if (stack.isEmpty()) {
            stack.push(x);
        } else {
            queue.enqueue(x);
        }
    }

    public boolean isEmpty() {
        return stack.isEmpty() && queue.isEmpty();
    }

    public Integer peek() {
        if (isEmpty()) return null;
        if (stack.isEmpty()) return queue.peek();
        if (queue.isEmpty()) return stack.peek();

        int sVal = stack.peek();
        int qVal = queue.peek();
        if (sVal >= qVal) {
            return sVal;
        } else {
            return qVal;
        }
    }

    public Integer remove() {
        if (isEmpty()) return null;
        if (stack.isEmpty()) return queue.dequeue();
        if (queue.isEmpty()) return stack.pop();

        int sVal = stack.peek();
        int qVal = queue.peek();

        if (sVal >= qVal) {
            return stack.pop();
        } else {
            return queue.dequeue();
        }
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int stackSize = sc.nextInt();
        int queueSize = sc.nextInt();
        int n = sc.nextInt();

        StekoRed stekoRed = new StekoRed(stackSize, queueSize);

        for (int i = 0; i < n; i++) {
            stekoRed.insert(sc.nextInt());
        }

        System.out.println(stekoRed.peek());

        StringBuilder sb = new StringBuilder();
        while (!stekoRed.isEmpty()) {
            sb.append(stekoRed.remove()).append(" ");
        }
        System.out.println(sb.toString().trim());
        sc.close();
    }
}
