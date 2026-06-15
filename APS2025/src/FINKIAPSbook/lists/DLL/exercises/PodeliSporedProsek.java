package FINKIAPSbook.lists.DLL.exercises;

import java.util.Scanner;

public class PodeliSporedProsek {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        DLL<Integer> list = new DLL<Integer>();
        for (int i=0; i<n; i++){
            list.insertLast(in.nextInt());
        }
        in.close();
        System.out.println(PodeliSporedProsek(list));
    }

    public static String PodeliSporedProsek(DLL<Integer> list) {
        double sum = 0;
        int count = 0;
        DLLNode<Integer> tmp = list.getFirst();
        while (tmp != null) {
            sum += tmp.element;
            count++;
            tmp = tmp.succ;
        }
        double avg = sum / count;

        DLL<Integer> below = new DLL<>();
        DLL<Integer> above = new DLL<>();

        tmp = list.getLast();
        while (tmp != null) {
            if (tmp.element <= avg) below.insertLast(tmp.element);
            else above.insertLast(tmp.element);
            tmp = tmp.pred;
        }

        return below + "\n" + above;
    }
}

class DLLNode<E> {
    E element;
    DLLNode<E> pred, succ;
    DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) { this.element = elem; this.pred = pred; this.succ = succ; }
    public String toString() { return element.toString(); }
}

class DLL<E> {
    private DLLNode<E> first, last;

    void insertLast(E o) {
        if (first == null) first = last = new DLLNode<>(o, null, null);
        else { DLLNode<E> ins = new DLLNode<>(o, last, null); last.succ = ins; last = ins; }
    }

    DLLNode<E> getFirst() { return first; }
    DLLNode<E> getLast() { return last; }
    void setFirst(DLLNode<E> node) { first = node; }
    void setLast(DLLNode<E> node) { last = node; }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        DLLNode<E> tmp = first;
        if (tmp != null) sb.append(tmp);
        while (tmp != null && tmp.succ != null) { tmp = tmp.succ; sb.append("<->").append(tmp); }
        return sb.toString();
    }
}