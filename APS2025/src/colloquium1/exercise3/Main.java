package colloquium1.exercise3;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        br.close();

        int minCost;
        int maxCost;

        if (M == 0) {
            minCost = N * 100;
            maxCost = N * 100;
        } else {
            if (M <= N) {
                minCost = N * 100;
            } else {
                minCost = M * 100;
            }

            maxCost = (N + M - 1) * 100;
        }

        System.out.println(minCost);
        System.out.println(maxCost);
    }
}