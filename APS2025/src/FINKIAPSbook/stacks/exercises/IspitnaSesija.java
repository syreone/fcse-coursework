package FINKIAPSbook.stacks.exercises;

import java.util.Scanner;
import java.util.Stack;

public class IspitnaSesija {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int M = sc.nextInt();
        int N = sc.nextInt();

        Stack<String> kup = new Stack<>();
        Stack<String> originalenRedosled = new Stack<>();
        Stack<Integer> brojacKup = new Stack<>();

        for(int i = 0; i < M; i++) {
            String kniga = sc.next();
            kup.push(kniga);
            originalenRedosled.push(kniga);
            brojacKup.push(0);
        }

        Stack<String> tempPomoshen = new Stack<>();
        while(!originalenRedosled.isEmpty()) {
            tempPomoshen.push(originalenRedosled.pop());
        }
        originalenRedosled = tempPomoshen;

        for(int i = 0; i < N; i++) {
            String baranaKniga = sc.next();

            Stack<String> privremenKnigi = new Stack<>();
            Stack<Integer> privremenBrojaci = new Stack<>();

            while(!kup.isEmpty() && !kup.peek().equals(baranaKniga)) {
                privremenKnigi.push(kup.pop());
                privremenBrojaci.push(brojacKup.pop() + 1);
            }

            if(!kup.isEmpty()) {
                String ispolagana = kup.pop();
                int tekovenBroj = brojacKup.pop() + 1;

                while(!privremenKnigi.isEmpty()) {
                    kup.push(privremenKnigi.pop());
                    brojacKup.push(privremenBrojaci.pop());
                }

                kup.push(ispolagana);
                brojacKup.push(tekovenBroj);
            }
        }

        while(!originalenRedosled.isEmpty()) {
            String tekovnaKniga = originalenRedosled.pop();

            Stack<String> tempK = new Stack<>();
            Stack<Integer> tempB = new Stack<>();
            int finalenBroj = 0;

            while(!kup.isEmpty()) {
                String k = kup.pop();
                int b = brojacKup.pop();
                if(k.equals(tekovnaKniga)) {
                    finalenBroj = b;
                }
                tempK.push(k);
                tempB.push(b);
            }

            while(!tempK.isEmpty()) {
                kup.push(tempK.pop());
                brojacKup.push(tempB.pop());
            }

            System.out.println(tekovnaKniga + " " + finalenBroj);
        }
    }
}