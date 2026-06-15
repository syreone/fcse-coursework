package FINKIAPSbook.lists.DLL.exercises;

import FINKIAPSbook.lists.DLL.DLL;
import FINKIAPSbook.lists.DLL.DLLNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class BrisenjePodlista {
    public static void brisenjePodlista(DLL<Integer> list1, DLL<Integer> list2){
        DLLNode<Integer> cur = list1.getFirst();
        while (cur != null){
            DLLNode<Integer> t1 = cur;
            DLLNode<Integer> t2 = list2.getFirst();
            while (t1 != null && t2 != null && t1.element.equals(t2.element)){
                t1 = t1.succ;
                t2 = t2.succ;
            }
            if (t2 == null){
                DLLNode<Integer> next = t1;
                DLLNode<Integer> del = cur;
                while (del != t1){
                    DLLNode<Integer> toDelete = del;
                    del = del.succ;
                    if (toDelete.pred != null) toDelete.pred.succ = toDelete.succ;
                    else list1.setFirst(toDelete.succ);
                    if (toDelete.succ != null) toDelete.succ.pred = toDelete.pred;
                    else list1.setLast(toDelete.pred);
                }
                cur = next;
            }
            else {
                cur = cur.succ;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        DLL<Integer> list1 = new DLL<Integer>(), list2 = new DLL<Integer>();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        s = stdin.readLine();
        String[] pomniza = s.split(" ");
        for (int i = 0; i < N; i++){
            list1.insertLast(Integer.parseInt(pomniza[i]));
        }
        s = stdin.readLine();
        int M = Integer.parseInt(s);
        s = stdin.readLine();
        pomniza = s.split(" ");
        for (int i = 0; i < M; i++){
            list2.insertLast(Integer.parseInt(pomniza[i]));
        }

        brisenjePodlista(list1, list2);

        if (list1.getFirst() == null){
            System.out.println("Prazna lista");
        }
        else {
            DLLNode<Integer> tmp = list1.getFirst();
            while (tmp != null){
                System.out.print(tmp.element);
                if (tmp.succ != null)
                    System.out.print(" ");
                tmp = tmp.succ;
            }
            System.out.println();
        }
    }
}