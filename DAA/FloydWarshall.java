public class FloydWarshall {

    final static int INF = 99999; // Infinity value

    // Function to implement Floyd-Warshall Algorithm
    public static void floydWarshall(int graph[][], int V) {
        int dist[][] = new int[V][V];

        // Initialize distance with input graph matrix
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        // Floyd-Warshall algorithm
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Print shortest distance matrix
        printSolution(dist, V);
    }

    // Utility function to print the matrix
    public static void printSolution(int dist[][], int V) {
        System.out.println("Shortest distances between every pair of vertices:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Main method
    public static void main(String[] args) {
        int V = 4;
        int graph[][] = {
                { 0, 5, INF, 10 },
                { INF, 0, 3, INF },
                { INF, INF, 0, 1 },
                { INF, INF, INF, 0 }
        };

        floydWarshall(graph, V);
    }
}
