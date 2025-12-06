public class MatrixChainMultiplication {

    // Function to find minimum multiplication cost
    public static int matrixChainOrder(int p[], int n) {
        int[][] m = new int[n][n]; // DP table

        // cost of one matrix is zero
        for (int i = 1; i < n; i++) {
            m[i][i] = 0;
        }

        // L = chain length
        for (int L = 2; L < n; L++) {
            for (int i = 1; i < n - L + 1; i++) {
                int j = i + L - 1;
                m[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (cost < m[i][j]) {
                        m[i][j] = cost;
                    }
                }
            }
        }

        return m[1][n - 1]; // minimum cost for full chain
    }

    public static void main(String[] args) {
        // Example: A1(10x30), A2(30x5), A3(5x60)
        int arr[] = { 10, 30, 5, 60 }; // dimensions
        int n = arr.length;

        System.out.println("Minimum number of multiplications is " + matrixChainOrder(arr, n));
    }
}
