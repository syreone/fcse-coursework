package labExercises.lab4.fromHome;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<String> stack = new Stack<>();

        while (true){
            String line = br.readLine();
            if (line.equals("x")) break;

            if (!line.startsWith("end")){
                stack.push(line);
            }else{
                String expected = line.substring(3);
                if (stack.isEmpty() || !stack.peek().equals(expected)){
                    System.out.println("Invalid");
                    return;
                }
                stack.pop();
            }
        }
        if (stack.isEmpty()) System.out.println("Valid");
        else System.out.println("Invalid");
    }
}
