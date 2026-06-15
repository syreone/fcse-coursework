package FINKIAPSbook.lists.DLL;

public class DLLNode<E> {
    public E element;
    public DLLNode<E> pred;
    public DLLNode<E> succ;
    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}


