package labExercises.lab6.fromHome;

import java.util.*;

public class Main {
    static Map<String, List<String>> tree = new HashMap<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        String root = sc.nextLine();
        tree.put(root, new ArrayList<>());

        for (int i=0; i<N-1; i++) {
            String parent = sc.next();
            String child = sc.next();
            sc.nextLine();

            tree.putIfAbsent(parent, new ArrayList<>());
            tree.putIfAbsent(child, new ArrayList<>());
            tree.get(parent).add(child);
        }

        int M = sc.nextInt();
        sc.nextLine();
        for (int i=0; i<M; i++) {
            String node = sc.nextLine();
            System.out.println(countLeaves(node));
        }
    }

    static int countLeaves(String node) {
        List<String> children = tree.get(node);
        if (children == null || children.isEmpty()){
            return 1;
        }

        int sum=0;
        for (String child : children)
            sum += countLeaves(child);
        return sum;
    }
}

