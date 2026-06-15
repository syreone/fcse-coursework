package FINKIAPSbook.queues.simpleProblems;

import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

abstract class QuasiQueue<E extends Comparable<E>> implements Queue<E> {
    E[] elems;
    int length, front, rear, maxlength;

    @SuppressWarnings("unchecked")
    public QuasiQueue (int maxlength) {
        elems = (E[]) new Comparable[maxlength];
        this.maxlength = maxlength;
        clear();
    }

    public boolean isEmpty () {
        return (length == 0);
    }

    public int size () {
        return length;
    }

    public E peekFirst () {
        if (length > 0)
            return elems[front];
        else
            throw new NoSuchElementException();
    }

    public E peekLast () {
        if (length > 0)
            return elems[rear-1];
        else
            throw new NoSuchElementException();
    }

    public void clear () {
        length = 0;
        front = rear = 0;
    }

    public void enqueue (E x) {
        if (rear == maxlength)
            elems[0] = x;
        else {
            elems[rear++] = x;
            length++;
        }
    }

    public E dequeue () {
        if (length > 0) {
            E frontmost = elems[front];
            E rearmost = elems[rear-1];
            if (front == rear) {
                front = 0;
                rear = 0;
                elems[front] = null;
                length--;
                return frontmost;
            }

            if(frontmost.compareTo(rearmost) == -1 || frontmost.compareTo(rearmost) == 0) {
                elems[front++] = null;
                length--;
                return frontmost;
            } else {
                elems[rear-1] = null;
                rear--;
                length--;
                return rearmost;
            }
        } else
            throw new NoSuchElementException();
    }
}

public class NovaStrukturaVevo {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int maxElementi = Integer.parseInt(br.readLine());
        QuasiQueue<Integer> qq = new QuasiQueue<Integer>(maxElementi) {
            @Override
            public Integer peek() {
                return 0;
            }
        };
        int brElementi = Integer.parseInt(br.readLine());

        for(int i=0; i<brElementi; i++) {
            int x = Integer.parseInt(br.readLine());
            qq.enqueue(x);
        }

        System.out.println(qq.peekFirst());
        System.out.println(qq.peekLast());

        while(!qq.isEmpty()) {
            System.out.print(qq.dequeue()+" ");
        }
    }
}
