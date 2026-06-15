package FINKIAPSbook.lists.SLL.SLLsimpleProblems;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ZameniSosedi {
    public static void main(String[] args) throws Exception {
        SLL<Integer> lista = new SLL<Integer>();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(stdin.readLine());
        for (int i = 0; i < n; i++) {
            lista.insertLast(Integer.parseInt(stdin.readLine()));
        }

        SLLNode<Integer> node = lista.getFirst();

        while (node != null && node.succ != null) {
            Integer move = node.element;
            node.element = node.succ.element;
            node.succ.element = move;
            node = node.succ.succ;
        }
        System.out.print(lista.toString());
    }
}

