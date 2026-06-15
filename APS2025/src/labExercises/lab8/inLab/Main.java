package labExercises.lab8.inLab;

import java.util.*;

public class Main {
    public static class AdjacencyMatrixGraph<T> {
        private int numVertices;
        private int[][] matrix;
        private T[] vertices;

        @SuppressWarnings("unchecked")
        public AdjacencyMatrixGraph(int numVertices) {
            this.numVertices = numVertices;
            matrix = new int[numVertices][numVertices];
            vertices = (T[]) new Object[numVertices];
        }

        public void addVertex(int index, T data) {
            vertices[index] = data;
        }

        public void addEdge(int source, int destination) {
            matrix[source][destination] = 1;
            matrix[destination][source] = 1;
        }

        public int minStepsToInformAll() {
            int best = Integer.MAX_VALUE;
            for (int start = 0; start < numVertices; start++) {
                int ecc = bfsEccentricity(start);
                if (ecc == -1) return -1;
                if (ecc < best) best = ecc;
            }
            return best;
        }

        private int bfsEccentricity(int start) {
            int[] dist = new int[numVertices];
            Arrays.fill(dist, -1);
            dist[start] = 0;
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(start);
            int maxDist = 0;
            int visited = 1;
            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (int v = 0; v < numVertices; v++) {
                    if (matrix[u][v] == 1 && dist[v] == -1) {
                        dist[v] = dist[u] + 1;
                        if (dist[v] > maxDist) maxDist = dist[v];
                        visited++;
                        queue.offer(v);
                    }
                }
            }
            if (visited < numVertices) return -1;
            return maxDist;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String[] names = new String[N];
        Map<String, Integer> idx = new HashMap<>();
        for (int i = 0; i < N; i++) {
            names[i] = sc.next();
            idx.put(names[i], i);
        }
        int M = sc.nextInt();
        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<>(N);
        for (int i = 0; i < N; i++) graph.addVertex(i, names[i]);
        for (int i = 0; i < M; i++) {
            String a = sc.next();
            String b = sc.next();
            graph.addEdge(idx.get(a), idx.get(b));
        }
        System.out.println(graph.minStepsToInformAll());
    }
}
