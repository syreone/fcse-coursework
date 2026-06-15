package FINKIAPSbook.lists.DLL.DLLadvancedProblems;

import FINKIAPSbook.lists.DLL.DLL;
import FINKIAPSbook.lists.DLL.DLLNode;

public class PrevrtiLista2 {
    public <E> void mirror() {
        DLLNode<E> tmp = null;
        DLLNode<E> first = null;
        DLLNode<E> current = first;
        DLLNode<E> last = first;
        while (current != null) {
            tmp = current.pred;
            current.pred = current.succ;
            current.succ = tmp;
            current = current.pred;
        }
        if (tmp != null && tmp.pred != null){
            first=tmp.pred;
        }
    }

    public static void main(String[] args) {
        DLL<String> lista = new DLL<String>();
        lista.insertLast("ovaa");
        lista.insertLast("lista");
        lista.insertLast("kje");
        lista.insertLast("bide");
        lista.insertLast("prevrtena");

        System.out.println("Listata pred da bide prevrtena: ");
        System.out.println(lista.toString());

        lista.mirror();

        System.out.println("Listata otkako e prevrtena: ");
        System.out.println(lista.toString());


    }
}
