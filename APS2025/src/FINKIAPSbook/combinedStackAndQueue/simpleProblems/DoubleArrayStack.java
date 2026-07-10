package FINKIAPSbook.combinedStackAndQueue.simpleProblems;

import java.util.NoSuchElementException;

public class DoubleArrayStack<E>{
    private E[] elems;
    private int depth1;
    private int depth2;

    @SuppressWarnings("unchecked")
    public DoubleArrayStack(int maxDepth) {
        elems = (E[]) new Object[maxDepth];
        depth1 = 0;
        depth2 = 0;
    }

    public boolean isEmptyFirst(){
        return (depth1 == 0);
    }

    public boolean isEmptySecond(){
        return (depth2 == 0);
    }

    public boolean isFull(){
        return (depth1 + depth2 == elems.length);
    }

    public E peekFirst(){
        if (depth1 == 0)
            throw new NoSuchElementException();
        return elems[depth1 - 1];
    }

    public E peekSecond() {
        if (depth2 == 0)
            throw new NoSuchElementException();
        return elems[elems.length - depth2];
    }

    public void clearFirst() {
        for (int i = 0; i < depth1; i++)
            elems[i] = null;
        depth1 = 0;
    }

    public void clearSecond() {
        for (int i = elems.length - 1; i >= elems.length - depth2; i--)
            elems[i] = null;
        depth2 = 0;
    }

    public void pushFirst(E x) {
        if (!this.isFull())
            elems[depth1++] = x;
        else
            System.out.println("Error, the array is full");
    }

    public void pushSecond(E x) {
        if (!this.isFull())
            elems[elems.length - (++depth2)] = x;
        else
            System.out.println("Error, the array is full");
    }

    public E popFirst() {
        if (depth1 == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth1];
        elems[depth1] = null;
        return topmost;
    }

    public E popSecond() {
        if (depth2 == 0)
            throw new NoSuchElementException();
        E topmost = elems[elems.length - depth2];
        elems[depth2--] = null;
        return topmost;
    }

    public String pecatiNizata() {
        StringBuilder ret = new StringBuilder("Elementite se: ");
        return ret.toString();
    }

    public static void main(String[] args){
        DoubleArrayStack<Integer> d = new DoubleArrayStack<Integer>(6);
        d.pushFirst(1);
        d.pushFirst(2);
        d.pushFirst(3);
        d.pushFirst(-1);
        d.pushFirst(-2);
        d.pushFirst(-3);
        System.out.println("Vrv na prv: " + d.peekFirst() + ", dolzina na prv: " + d.depth1);
        System.out.println("Vrv na vtor: " + d.peekSecond() + ", dolzina na vtor: " + d.depth2);
        d.pushFirst(4);
        d.popFirst();
        d.pushFirst(4);
        System.out.println("Vrv na prv: " + d.peekFirst() + ", dolzina na prv: " + d.depth1);
        d.pecatiNizata();
    }
}
