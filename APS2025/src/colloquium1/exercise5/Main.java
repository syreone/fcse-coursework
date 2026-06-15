package colloquium1.exercise5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int evaluateExpression(String expression) {
        String[] addParts = expression.split("\\+");
        int sum = 0;

        for (String part : addParts) {
            String[] mulParts = part.split("\\*");
            int product = 1;

            for (String num : mulParts) {
                product *= Integer.parseInt(num);
            }

            sum += product;
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }
}

