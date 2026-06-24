import java.util.*;

public class KnapsackCargoLoading {

    public static void main(String[] args) {

        int[] weights = {4, 6, 8, 5, 3};
        int[] values = {20, 30, 40, 25, 15};

        int capacity = 24;
        int n = weights.length;

        int[][] dp = new int[n + 1][capacity + 1];

        // Build DP Table
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {

                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            dp[i - 1][w],
                            values[i - 1] + dp[i - 1][w - weights[i - 1]]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("Maximum Revenue = " + dp[n][capacity]);

        // Backtracking to find selected items
        int w = capacity;

        System.out.println("\nSelected Consignments:");

        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {

                System.out.println(
                        "Item " + i +
                        " Weight = " + weights[i - 1] +
                        " Value = " + values[i - 1]);

                w -= weights[i - 1];
            }
        }
    }
}