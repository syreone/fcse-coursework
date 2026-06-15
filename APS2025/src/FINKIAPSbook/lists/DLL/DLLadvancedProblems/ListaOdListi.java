package FINKIAPSbook.lists.DLL.DLLadvancedProblems;

import FINKIAPSbook.lists.DLL.DLL;
import FINKIAPSbook.lists.DLL.DLLNode;

import java.util.Scanner;

public class ListaOdListi {
    public static long findMagicNumber(DLL<DLL<Integer>> list) {
        DLLNode<DLL<Integer>> current = list.getFirst();
        long prod = 1;
        while (true) {
            int sum = 0;
            DLLNode<Integer> current1 = current.element.getFirst();
            while (true) {
                sum += current1.element;
                if (current1 == current.element.getLast()) {
                    break;
                }
                current1 = current1.succ;
            }
            prod *= sum;
            if (current == list.getLast()) {
                break;
            }
            current = current.succ;
        }
        return prod;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        DLL<DLL<Integer>> list = new DLL<DLL<Integer>>();
        for (int i = 0; i < 9; i++) {
            DLL<Integer> tmp = new DLL<Integer>();
            for (int j = 0; j < m; j++) {
                tmp.insertLast(in.nextInt());
            }
            list.insertLast(tmp);
        }
        in.close();
        System.out.println(findMagicNumber(list));
    }
}
