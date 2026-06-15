package FINKIAPSbook.lists.SLL.SLLsimpleProblems;

import FINKIAPSbook.lists.SLL.SLL;
import FINKIAPSbook.lists.SLL.SLLNode;

import java.util.Scanner;

public class IzbrishiPosleden {
    public static void main(String[] args){
        Scanner sc =  new Scanner(System.in);
        int n = sc.nextInt();
        SLL<Integer> lista = new SLL<Integer>();
        for (int i = 0; i < n; i++){
            int element = sc.nextInt();
            lista.insertLast(element);
        }
        int todelete = sc.nextInt();

        SLLNode<Integer> node = lista.getFirst();
        SLLNode<Integer> brisi = null;

        while (node!=null){
            if (node.
                    element == todelete){
                brisi = node;
            }
            node = node.succ;
        }
        if (brisi!=null){
            lista.delete(brisi);
            System.out.println(lista.toString());
        }
    }
}
