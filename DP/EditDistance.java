
/**
 * EditDistance
 */

import java.util.*;

public class EditDistance {

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], -1);

        return solve(word1, word2, n, m, dp);
    }

    private int solve(String word1, String word2, int i, int j, int[][] dp) {
        if (i == 0 || j == 0) {
            return i == 0 ? j : i;
        }

        if (dp[i][j] != -1)
            return dp[i][j];

        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            dp[i][j] = solve(word1, word2, i - 1, j - 1, dp);
        } else {
            int insert = solve(word1, word2, i, j - 1, dp);
            int delete = solve(word1, word2, i - 1, j, dp);
            int replace = solve(word1, word2, i - 1, j - 1, dp);

            dp[i][j] = Math.min(insert, Math.min(delete, replace)) + 1;
        }

        return dp[i][j];
    }

    // iterative
    public int minDistance_2(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][0] = i;
        }

        for (int j = 1; j <= m; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }

        return dp[n][m];
    }
}