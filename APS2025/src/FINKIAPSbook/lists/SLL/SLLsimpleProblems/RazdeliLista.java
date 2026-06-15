package FINKIAPSbook.lists.SLL.SLLsimpleProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RazdeliLista {
    public static void main(String[] args) throws IOException {
        SLL<Integer> lista = new SLL<Integer>();
        SLL<Integer> parni = new SLL<Integer>();
        SLL<Integer> neparni = new SLL<Integer>();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        s = stdin.readLine();
        String[] pomniza = s.split(" ");
        for (int i = 0; i < N; i++) {
            lista.insertLast(Integer.parseInt(pomniza[i]));
        }

        SLLNode<Integer> move = lista.getFirst();

        while (move != null) {
            while (move.succ != null && move.element % 2 == 0 && move.succ.element % 2 == 0) {
                move = move.succ;
            }
            while (move.succ != null && !(move.element % 2 == 0) && !(move.succ.element % 2 == 0)) {
                move = move.succ;
            }
            if (move.element % 2 == 0)
                parni.insertLast(move.element);
            else
                neparni.insertLast(move.element);
            move = move.succ;
        }
        if (parni.size() == 0) System.out.println("Prazna lista");
        else System.out.println(parni);

        if (neparni.size() == 0) System.out.println("Prazna lista");
        else System.out.println(neparni);
    }
}
