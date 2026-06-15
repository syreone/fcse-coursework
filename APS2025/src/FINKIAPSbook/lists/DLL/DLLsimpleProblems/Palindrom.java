package FINKIAPSbook.lists.DLL.DLLsimpleProblems;

import FINKIAPSbook.lists.DLL.DLL;
import FINKIAPSbook.lists.DLL.DLLNode;

import java.util.Scanner;

public class Palindrom {
    public static int isItPalindrome(DLL<Integer> list){
        DLLNode<Integer> poceten = list.getFirst();
        DLLNode<Integer> posleden = list.getLast();
        while((poceten != posleden) && (poceten.pred != posleden)){
            if (!poceten.element.equals(posleden.element))
                return -1;
            poceten = poceten.succ;
            posleden = posleden.pred;
        }
        return 1;
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        DLL<Integer> list = new DLL<Integer>();
        for (int i=0; i<n; i++){
            list.insertLast(in.nextInt());
        }
        in.close();
        System.out.println(isItPalindrome(list));
    }
}
