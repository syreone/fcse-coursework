package FINKIAPSbook.stacks.simpleProblems;

import FINKIAPSbook.stacks.LinkedStack;

public class Postfiks {
    public static Double evaluiraj_postfix(String izraz) {
        LinkedStack<Double> stack = new LinkedStack<>();
        Double r = null;
        for (int i = 0; i< izraz.length(); i++) {
            char c = izraz.charAt(i);
            if (c == ' ') continue;
            else if (Character.isDigit(c)) {
                stack.push((double) c - '0');
            } else {
                if (stack.size() >= 2) {
                    Double posleden_broj = stack.pop();
                    Double pretposleden_broj = stack.pop();
                    switch (c) {
                        case '+':
                            stack.push(pretposleden_broj + posleden_broj);
                            break;
                        case '*':
                            stack.push(pretposleden_broj * posleden_broj);
                            break;
                        case 'x':
                            stack.push(pretposleden_broj * posleden_broj);
                            break;
                        case '-':
                            stack.push(pretposleden_broj - posleden_broj);
                            break;
                        case '/':
                            stack.push(pretposleden_broj / posleden_broj);
                            break;
                    }
                } else {
                    System.out.println("Nevaliden vlez - nedostasuva operand na pozicija:" + i);
                    return r;
                }
            }
        }
        if (stack.size() != 1) {
            System.out.println("Nevaliden vlez - nedostasuva operator");
        } else {
            r = stack.pop();
        }
        return r;
    }

    public static void main(String[] args) {
        String primer = "5 9 + 2 * 6 5 * +";
        System.out.println("Rezultatot e " + evaluiraj_postfix(primer));
    }
}
