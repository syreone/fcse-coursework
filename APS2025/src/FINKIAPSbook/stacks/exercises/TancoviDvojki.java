package FINKIAPSbook.stacks.exercises;

import java.util.Scanner;
import java.util.Stack;

public class TancoviDvojki {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) return;

        String[] input = sc.nextLine().split(" ");

        Stack<String> oMen = new Stack<>();
        Stack<String> oWomen = new Stack<>();
        Stack<String> sMen = new Stack<>();
        Stack<String> sWomen = new Stack<>();
        Stack<String> lMen = new Stack<>();
        Stack<String> lWomen = new Stack<>();

        StringBuilder pairs = new StringBuilder();
        int pairCount = 0;

        for (String candidate : input) {
            char group = candidate.charAt(0);
            char gender = candidate.charAt(1);

            if (group == 'O') {
                if (gender == 'M') {
                    if (!oWomen.isEmpty()) {
                        pairs.append("(").append(candidate).append(", ").append(oWomen.pop()).append("); ");
                        pairCount++;
                    } else {
                        oMen.push(candidate);
                    }
                } else {
                    if (!oMen.isEmpty()) {
                        pairs.append("(").append(oMen.pop()).append(", ").append(candidate).append("); ");
                        pairCount++;
                    } else {
                        oWomen.push(candidate);
                    }
                }
            } else if (group == 'S') {
                if (gender == 'M') {
                    if (!sWomen.isEmpty()) {
                        pairs.append("(").append(candidate).append(", ").append(sWomen.pop()).append("); ");
                        pairCount++;
                    } else {
                        sMen.push(candidate);
                    }
                } else {
                    if (!sMen.isEmpty()) {
                        pairs.append("(").append(sMen.pop()).append(", ").append(candidate).append("); ");
                        pairCount++;
                    } else {
                        sWomen.push(candidate);
                    }
                }
            } else if (group == 'L') {
                if (gender == 'M') {
                    if (!lWomen.isEmpty()) {
                        pairs.append("(").append(candidate).append(", ").append(lWomen.pop()).append("); ");
                        pairCount++;
                    } else {
                        lMen.push(candidate);
                    }
                } else {
                    if (!lMen.isEmpty()) {
                        pairs.append("(").append(lMen.pop()).append(", ").append(candidate).append("); ");
                        pairCount++;
                    } else {
                        lWomen.push(candidate);
                    }
                }
            }
        }

        System.out.println("Broj na formirani dvojki: " + pairCount);
        if (pairCount > 0) {
            System.out.println("Dvojki: " + pairs.toString().substring(0, pairs.length() - 2));
        }

        System.out.print("Ostanuvaat bez partner: ");
        boolean first = true;

        Stack[] leftOver = {oMen, oWomen, sMen, sWomen, lMen, lWomen};
        for (Stack<String> st : leftOver) {
            for (String s : st) {
                if (!first) System.out.print(", ");
                System.out.print(s);
                first = false;
            }
        }
        System.out.println();
    }
}