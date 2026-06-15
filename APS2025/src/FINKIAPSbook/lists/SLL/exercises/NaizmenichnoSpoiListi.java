package FINKIAPSbook.lists.SLL.exercises;

import java.util.Scanner;

public class NaizmenichnoSpoiListi {
    private static class SLLNode<E> {
        E element;
        SLLNode<E> succ;

        public SLLNode(E elem, SLLNode<E> succ) {
            this.element = elem;
            this.succ = succ;
        }
    }

    private static class SLL<E> {
        private SLLNode<E> first;

        public SLL() {
            first = null;
        }

        public SLLNode<E> getFirst() {
            return first;
        }

        public void insertLast(E element) {
            if (first == null) {
                first = new SLLNode<>(element, null);
            } else {
                SLLNode<E> tmp = first;
                while (tmp.succ != null) {
                    tmp = tmp.succ;
                }
                tmp.succ = new SLLNode<>(element, null);
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            SLLNode<E> tmp = first;
            while (tmp != null) {
                sb.append(tmp.element);
                if (tmp.succ != null) sb.append("->");
                tmp = tmp.succ;
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n1 = sc.nextInt();
        SLL<Integer> lista1 = new SLL<>();
        for (int i = 0; i < n1; i++) lista1.insertLast(sc.nextInt());

        int n2 = sc.nextInt();
        SLL<Integer> lista2 = new SLL<>();
        for (int i = 0; i < n2; i++) lista2.insertLast(sc.nextInt());

        SLL<Integer> rezultat = new SLL<>();

        SLLNode<Integer> node1 = lista1.getFirst();
        SLLNode<Integer> node2 = lista2.getFirst();

        while (node1 != null && node2 != null) {
            for (int i = 0; i < 2 && node1 != null; i++) {
                rezultat.insertLast(node1.element);
                node1 = node1.succ;
            }
            for (int i = 0; i < 2 && node2 != null; i++) {
                rezultat.insertLast(node2.element);
                node2 = node2.succ;
            }
        }

        while (node1 != null) {
            rezultat.insertLast(node1.element);
            node1 = node1.succ;
        }

        while (node2 != null) {
            rezultat.insertLast(node2.element);
            node2 = node2.succ;
        }

        System.out.println(rezultat.toString());
    }
}