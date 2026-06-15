package labExercises.lab5.fromHome;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        Map<String, Integer> map = new HashMap<>();

        for (int i=0; i<N; i++) {
            String ip = sc.nextLine();
            String[] parts = ip.split("\\.");
            String key = parts[0] + "." + parts[1] + "." + parts[2];
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        int M = sc.nextInt();
        sc.nextLine();
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<M; i++) {
            String net = sc.nextLine();
            String[] parts = net.split("\\.");
            String key = parts[0] + "." + parts[1] + "." + parts[2];
            sb.append(map.getOrDefault(key, 0)).append("\n");
        }
        System.out.print(sb.toString());
    }
}
