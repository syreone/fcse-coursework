package FINKIAPSbook.lists.DLL.DLLadvancedProblems;

import FINKIAPSbook.lists.DLL.DLL;
import FINKIAPSbook.lists.DLL.DLLNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PrevrtiLista {
    public static void prevrtiLista(DLL<Integer> lista, DLL<Integer> pomosna) {

        DLLNode<Integer> pom = lista.getLast();

        while (pom != null){
            if (pom.element % 2 == 0) {
                pomosna.insertLast(pom.element);
                if (pom==lista.getFirst()){
                    lista.deleteFirst();
                }
                else if (pom==lista.getLast()){
                    lista.deleteLast();
                }
                else {
                    (pom.pred).succ = pom.succ;
                    (pom.succ).pred = pom.pred;
                }
            }

            pom = pom.pred;
        }

        pom = lista.getLast();
        while (pom != null) {
            pomosna.insertLast(pom.element);
            pom = pom.pred;
        }
    }

    public static void main(String[] args) throws IOException {
        DLL<Integer> lista = new DLL<Integer>(), pomosna = new DLL<Integer>();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        s = stdin.readLine();
        String[] pomniza = s.split(" ");
        for (int i = 0; i < N; i++) {
            lista.insertLast(Integer.parseInt(pomniza[i]));
        }

        prevrtiLista(lista, pomosna);
        DLLNode<Integer> tmp1 = pomosna.getFirst();
        while (tmp1 != null) {
            System.out.print(tmp1.element);
            if (tmp1.succ != null)
                System.out.print(" ");
            tmp1 = tmp1.succ;
        }
        System.out.println();
    }
}
