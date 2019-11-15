import java.lang.Exception;
import java.util.*;

// Adjacency Matrix

public class GraphImplementation implements Graph {

    private int[][] matrix;
    private int vertices;

    public GraphImplementation(int vertexNum) {
        vertices = vertexNum;
        matrix = new int[vertices][vertices];
        /*
         * for (int i = 0; i < vertices; i++) { list[i] = new LinkedList(); }
         */
    }

    public void addEdge(int source, int target) throws Exception {
        if (matrix.length > source && matrix.length > target) {
            matrix[source][target] = 1;
        } else {
            throw new Exception();
        }
    }

    public List<Integer> neighbors(int vertex) throws Exception {
        List<Integer> neighbors = new ArrayList<>();

        if (vertex < matrix.length && vertex <= 0) {

            for (int i = 0; i < matrix.length; i++) {
                if (matrix[vertex][i] > 0) {
                    neighbors.add(i);
                }
            }
        } else {
            throw new Exception();
        }
        return neighbors;
    }

    public List<Integer> topologicalSort() {
        List<Integer> schedule = new ArrayList<>();

        int[] sum = new int[matrix.length];

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                sum[i] += matrix[j][i];
            }
        }

        for (int k = 0; k < vertices; k++) {
            // Check Sum Matrix & Return First Index With 0
            int n = findZero(sum);
            if (n == -1) {
                System.out.println("We cannot sort this graph!");
                return schedule;
            }

            schedule.add(n);
            sum[n] = -1;

            for (int l = 0; l < vertices; l++) {
                sum[l] -= matrix[n][l];
            }
        }
        return schedule;
    }

    private int findZero(int[] sum) {
        for (int i = 0; i < matrix.length; i++) {
            if (sum[i] == 0) {
                return i;
            }
        }
        return -1;
    }

}
