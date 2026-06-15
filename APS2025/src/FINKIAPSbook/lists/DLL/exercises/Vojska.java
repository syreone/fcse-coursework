package FINKIAPSbook.lists.DLL.exercises;

import FINKIAPSbook.lists.DLL.DLL;
import FINKIAPSbook.lists.DLL.DLLNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Vojska {
    public static void zameni(DLL<Integer> lista, int a1, int a2, int b1, int b2){
        DLLNode<Integer> startA = null, endA = null, startB = null, endB = null;
        DLLNode<Integer> pom = lista.getFirst();
        while (pom != null){
            if (pom.element == a1) startA = pom;
            if (pom.element == a2) endA = pom;
            if (pom.element == b1) startB = pom;
            if (pom.element == b2) endB = pom;
            pom = pom.succ;
        }

        DLLNode<Integer> beforeA = startA.pred;
        DLLNode<Integer> afterA = endA.succ;
        DLLNode<Integer> beforeB = startB.pred;
        DLLNode<Integer> afterB = endB.succ;

        if (afterA == startB){
            if (beforeA != null) beforeA.succ = startB;
            else lista.setFirst(startB);
            endB.succ = startA;
            startA.pred = endB;
            endA.succ = afterB;
            startB.pred = beforeA;
            if (afterB != null) afterB.pred = endA;
            else lista.setLast(endA);
        }
        else if (afterB == startA){
            if (beforeB != null) beforeB.succ = startA;
            else lista.setFirst(startA);
            endA.succ = startB;
            startB.pred = endA;
            endB.succ = afterA;
            startA.pred = beforeB;
            if (afterA != null) afterA.pred = endB;
            else lista.setLast(endB);
        }
        else {
            if (beforeA != null) beforeA.succ = startB;
            else lista.setFirst(startB);
            startB.pred = beforeA;

            if (afterB != null) afterB.pred = endA;
            else lista.setLast(endA);
            endA.succ = afterB;

            if (beforeB != null) beforeB.succ = startA;
            else lista.setFirst(startA);
            startA.pred = beforeB;

            if (afterA != null) afterA.pred = endB;
            else lista.setLast(endB);
            endB.succ = afterA;
        }
    }

    public static void main(String[] args) throws IOException {
        DLL<Integer> lista = new DLL<Integer>();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        s = stdin.readLine();
        String[] pomniza = s.split(" ");
        for (int i = 0; i < N; i++){
            lista.insertLast(Integer.parseInt(pomniza[i]));
        }
        s = stdin.readLine();
        pomniza = s.split(" ");
        int a1 = Integer.parseInt(pomniza[0]);
        int a2 = Integer.parseInt(pomniza[1]);
        s = stdin.readLine();
        pomniza = s.split(" ");
        int b1 = Integer.parseInt(pomniza[0]);
        int b2 = Integer.parseInt(pomniza[1]);

        zameni(lista, a1, a2, b1, b2);

        DLLNode<Integer> tmp = lista.getFirst();
        while (tmp != null){
            System.out.print(tmp.element);
            if (tmp.succ != null)
                System.out.print(" ");
            tmp = tmp.succ;
        }
        System.out.println();
    }
}