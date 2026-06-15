package labExercises.lab2.fromHome;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        DLL<Integer> list = new DLL<>();
        for (int i = 0; i < n; i++) list.insertLast(sc.nextInt());
        int M = sc.nextInt();
        int k = sc.nextInt();

        System.out.println(list);

        DLLNode<Integer> curr = list.getFirst();
        while (curr != null && !curr.element.equals(M)) curr = curr.succ;

        if (curr == null) {
            System.out.println("Elementot ne postoi vo listata");
            System.out.println(list);
            return;
        }

        for (int i = 0; i < k; i++) {
            if (curr.pred != null) {
                DLLNode<Integer> prev = curr.pred;
                if (curr.succ != null) curr.succ.pred = prev;
                else list.setLast(prev);
                prev.succ = curr.succ;

                curr.pred = prev.pred;
                curr.succ = prev;
                if (prev.pred != null) prev.pred.succ = curr;
                else list.setFirst(curr);
                prev.pred = curr;
            } else {
                DLLNode<Integer> first = curr;
                list.setFirst(curr.succ);
                if (curr.succ != null) curr.succ.pred = null;
                first.pred = list.getLast();
                first.succ = null;
                if (list.getLast() != null) list.getLast().succ = first;
                list.setLast(first);
            }
        }

        System.out.println(list);
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

