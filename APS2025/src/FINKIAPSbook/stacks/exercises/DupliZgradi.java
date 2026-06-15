package FINKIAPSbook.stacks.exercises;

import FINKIAPSbook.stacks.ArrayStack;

import java.util.Scanner;

public class DupliZgradi {
    public static boolean imaDupliZagradi(String izraz){
        ArrayStack<Character> stack = new ArrayStack<>(izraz.length());

        for (int i=0; i<izraz.length(); i++){
            char znak = izraz.charAt(i);

            if (znak != ')'){
                stack.push(znak);
            } else {
                int elementi_izmegju = 0;

                while (!stack.isEmpty() && stack.peek() != '('){
                    stack.pop();
                    elementi_izmegju++;
                }

                if (!stack.isEmpty()){
                    stack.pop();
                }

                if (elementi_izmegju == 0){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Vnesete izraz: ");
        String izraz = sc.nextLine();

        if (imaDupliZagradi(izraz)) {
            System.out.println("Najdeni se dupli zagradi");
        } else{
            System.out.println("Ne se najdeni dupli zagradi");
        }
    }
}
