package labExercises.lab8.fromHome;

import java.util.*;

public class Main {

    public static class AdjacencyMatrixGraph<T> {
        private int numVertices;
        private int[][] matrix;
        private T[] vertices;
        private Map<String, Long> memo = new HashMap<>();

        @SuppressWarnings("unchecked")
        public AdjacencyMatrixGraph(int numVertices) {
            this.numVertices = numVertices;
            matrix = new int[numVertices][numVertices];
            vertices = (T[]) new Object[numVertices];
        }

        public void addVertex(int index, T data) {
            vertices[index]=data;
        }

        public void addEdge(int source, int destination) {
            matrix[source][destination]=1;
            matrix[destination][source]=1;
        }

        public long countPathsWithSum(int startIndex, int N) {
            memo.clear();
            int startValue = (Integer) vertices[startIndex];
            return dfsSum(startIndex, N-startValue);
        }

        private long dfsSum(int vertexIndex, int remainingSum) {
            if (remainingSum==0) return 1;
            if (remainingSum<0) return 0;

            String key = vertexIndex + "," + remainingSum;
            if (memo.containsKey(key)) return memo.get(key);

            long count = 0;
            for (int i=0; i<numVertices; i++) {
                if (matrix[vertexIndex][i]==1) {
                    int value = (Integer) vertices[i];
                    count += dfsSum(i, remainingSum-value);
                }
            }

            memo.put(key, count);
            return count;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int M = sc.nextInt();
        List<Integer> verts = new ArrayList<>();
        List<int[]> edges = new ArrayList<>();

        for (int i=0; i<M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            edges.add(new int[]{a, b});
            if (!verts.contains(a)) verts.add(a);
            if (!verts.contains(b)) verts.add(b);
        }

        int V = sc.nextInt();
        int N = sc.nextInt();
        if (!verts.contains(V)) verts.add(V);

        AdjacencyMatrixGraph<Integer> graph = new AdjacencyMatrixGraph<>(verts.size());

        for (int i =0; i<verts.size(); i++)
            graph.addVertex(i, verts.get(i));

        for (int[] e : edges) {
            int u = verts.indexOf(e[0]);
            int v = verts.indexOf(e[1]);
            graph.addEdge(u, v);
        }

        int startIndex = verts.indexOf(V);
        long result = graph.countPathsWithSum(startIndex, N);
        System.out.println(result);
    }
}

