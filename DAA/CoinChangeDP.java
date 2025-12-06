import java.util.*;

public class CoinChangeDP {

    // Function to find minimum coins required
    public static int coinChange(int[] coins, int amount) {
        int MAX = amount + 1; // large number for initialization
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, MAX);
        dp[0] = 0; // base case

        // Bottom-up DP
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] == MAX ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = { 1, 3, 4 }; // coin denominations
        int amount = 9; // target amount

        int result = coinChange(coins, amount);

        if (result == -1) {
            System.out.println("It is not possible to make the given amount.");
        } else {
            System.out.println("Minimum coins required = " + result);
        }
    }
}
