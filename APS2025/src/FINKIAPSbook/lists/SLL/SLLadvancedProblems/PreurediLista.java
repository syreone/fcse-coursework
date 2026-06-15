package FINKIAPSbook.lists.SLL.SLLadvancedProblems;

import java.util.Scanner;

public class PreurediLista {

    private static class SLLNode<E> {
        E element;
        SLLNode<E> succ;

        public SLLNode(E element, SLLNode<E> succ) {
            this.element = element;
            this.succ = succ;
        }
    }

    private static class SLL<E> {
        private SLLNode<E> first;
        private int size;

        public SLL() {
            first = null;
            size = 0;
        }

        public SLLNode<E> getFirst() {
            return first;
        }

        public int size() {
            return size;
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
            size++;
        }

        public SLLNode<E> reverseList(SLLNode<E> node) {
            SLLNode<E> prev = null, curr = node, next;
            while (curr != null) {
                next = curr.succ;
                curr.succ = prev;
                prev = curr;
                curr = next;
            }
            return prev;
        }

        public void rearrange() {
            SLLNode<E> sredina = this.getFirst();
            for (int i = 1; i < this.size() / 2; i++) {
                sredina = sredina.succ;
            }
            System.out.println(sredina.element);

            SLLNode<E> sredina2 = this.getFirst();
            sredina2.succ = sredina.succ;
            sredina.succ = null;

            sredina2 = reverseList(sredina2);

            SLLNode<E> node1 = this.getFirst();
            SLLNode<E> node2 = sredina2;

            SLLNode<E> curr = new SLLNode<>(null, null);
            SLLNode<E> node = curr;

            while (node1 != null || node2 != null) {
                if (node1 != null) {
                    curr.succ = node1;
                    curr = curr.succ;
                    node1 = node1.succ;
                }

                // Pa jadе jazol od vtorata lista
                if (node2 != null) {
                    curr.succ = node2;
                    curr = curr.succ;
                    node2 = node2.succ;
                }
            }
            node = node.succ;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            SLLNode<E> tmp = first;
            while (tmp != null) {
                sb.append(tmp.element);
                if (tmp.succ != null) sb.append(" -> ");
                tmp = tmp.succ;
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        SLL<Integer> lista = new SLL<Integer>();

        for (int i = 0; i < n; i++) {
            int el = sc.nextInt();
            lista.insertLast(el);
        }

        lista.rearrange();
        System.out.println(lista.toString());
    }
}