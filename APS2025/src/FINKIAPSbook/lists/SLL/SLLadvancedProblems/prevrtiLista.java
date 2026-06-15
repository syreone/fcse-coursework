package FINKIAPSbook.lists.SLL.SLLadvancedProblems;

public class prevrtiLista {

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

        public SLL() {
            first = null;
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

        public void mirror() {
            if (first != null) {
                SLLNode<E> tmp = first;
                SLLNode<E> newsucc = null;
                SLLNode<E> next;

                while (tmp != null) {
                    next = tmp.succ;
                    tmp.succ = newsucc;
                    newsucc = tmp;
                    tmp = next;
                }
                first = newsucc;
            }
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
        SLL<String> listaIminja = new SLL<String>();
        listaIminja.insertLast("Ana");
        listaIminja.insertLast("Bojana");
        listaIminja.insertLast("Cece");
        listaIminja.insertLast("Dina");
        System.out.print("Listata pred prevrtuvanje: ");
        System.out.println(listaIminja.toString());
        listaIminja.mirror();
        System.out.print("Listata po prevrtuvanje: ");
        System.out.println(listaIminja.toString());
    }
}