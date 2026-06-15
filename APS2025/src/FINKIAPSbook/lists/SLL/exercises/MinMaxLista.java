package FINKIAPSbook.lists.SLL.exercises;

import java.util.Scanner;

public class MinMaxLista {
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
        int n = sc.nextInt();
        SLL<Integer> lista = new SLL<>();

        for (int i = 0; i < n; i++) {
            lista.insertLast(sc.nextInt());
        }

        SLLNode<Integer> node = lista.getFirst();
        int min = node.element;
        int max = node.element;

        while (node != null) {
            if (node.element < min) min = node.element;
            if (node.element > max) max = node.element;
            node = node.succ;
        }

        SLL<Integer> listaMin = new SLL<>();
        SLL<Integer> listaMax = new SLL<>();

        node = lista.getFirst();
        while (node != null) {
            int distMin = Math.abs(node.element - min);
            int distMax = Math.abs(node.element - max);

            if (distMin <= distMax) {
                listaMin.insertLast(node.element);
            } else {
                listaMax.insertLast(node.element);
            }
            node = node.succ;
        }
        System.out.println(listaMin.toString());
        System.out.println(listaMax.toString());
    }
}
