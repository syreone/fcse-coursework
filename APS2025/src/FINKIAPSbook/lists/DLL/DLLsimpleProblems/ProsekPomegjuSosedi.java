package FINKIAPSbook.lists.DLL.DLLsimpleProblems;

import FINKIAPSbook.lists.DLL.DLL;
import FINKIAPSbook.lists.DLL.DLLNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProsekPomegjuSosedi {
    public static void main(String[] args) throws IOException {
        DLL<Integer> lista = new DLL<Integer>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int N = Integer.parseInt(s);
        s = br.readLine();
        String[] pomniza = s.split(" ");
        for (int i = 0; i < N; i++) {
            lista.insertLast(Integer.parseInt(pomniza[i]));
        }

        DLLNode<Integer> tmp = lista.getFirst();
        DLLNode<Integer> next = tmp.succ;
        while (tmp != null && next != null) {
            float a = tmp.element;
            float b = next.element;
            Integer nov = Math.round((a + b) / 2);
            lista.insertAfter(nov, tmp);
            tmp = next;
            next = tmp.succ;
        }

        tmp = lista.getFirst();
        while (tmp != null) {
            System.out.print(tmp.element + " ");
            tmp = tmp.succ;
        }
    }
}
