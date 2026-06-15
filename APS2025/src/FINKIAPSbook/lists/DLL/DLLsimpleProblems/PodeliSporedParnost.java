package FINKIAPSbook.lists.DLL.DLLsimpleProblems;

import FINKIAPSbook.lists.DLL.DLL;
import FINKIAPSbook.lists.DLL.DLLNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PodeliSporedParnost {
    public static void podeliPARNOST (DLL<Integer> lista, DLL<Integer> lparni, DLL<Integer> lneparni) {
        DLLNode<Integer> pom1 = lista.getFirst();
        DLLNode<Integer> pom2 = lista.getLast();

        while (pom1 != pom2 && pom2.succ!=pom1) {
            if (pom1.element % 2 == 0)
                lparni.insertLast(pom1.element);
            else
                lneparni.insertLast(pom1.element);
            if (pom2.element % 2 == 0)
                lparni.insertLast(pom2.element);
            else
                lneparni.insertLast(pom2.element);
            pom1 = pom1.succ;
            pom2 = pom2.pred;
        }
        if (pom1==pom2) {
            if (pom1.element % 2 == 0)
                lparni.insertLast(pom1.element);
            else
                lneparni.insertLast(pom1.element);
            return;
        }
    }

    public static void main (String[] args) throws IOException {
        DLL<Integer> lista = new DLL<Integer>(), parni = new DLL<Integer>(), neparni = new DLL<Integer>();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        s = stdin.readLine();
        String[] pomniza = s.split(" ");
        for (int i = 0; i < N; i++) {
            lista.insertLast(Integer.parseInt(pomniza[i]));
        }

        podeliPARNOST (lista, parni, neparni);

        DLLNode<Integer> tmp = parni.getFirst();
        while (tmp != null) {
            System.out.print(tmp.element);
            if (tmp.succ != null)
                System.out.print(" ");
            tmp = tmp.succ;
        }
        System.out.println();
        tmp = neparni.getFirst();
        while (tmp != null) {
            System.out.print(tmp.element);
            if (tmp.succ != null)
                System.out.print(" ");
            tmp = tmp.succ;
        }
        System.out.println();

    }
}
