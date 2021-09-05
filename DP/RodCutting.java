
/**
 * RodCutting
 */

import java.util.*;

public class RodCutting {

    public static int maximizeCuts(int n, int[] cuts, int[] val) {

        int[] dp = new int[n + 1];

        Arrays.fill(dp, Integer.MIN_VALUE);

        dp[0] = 0;

        for (int c = 0; c < cuts.length; c++) {
            for (int i = cuts[c]; i <= n; i++) {

                if (dp[i - cuts[c]] == Integer.MIN_VALUE)
                    continue;

                dp[i] = Math.max(dp[i], dp[i - cuts[c]] + val[c]);
            }
        }

        return dp[n] == Integer.MIN_VALUE ? 0 : dp[n];
    }

    public static void main(String[] args) {
        int[] cuts = { 1, 2, 3, 4, 5, 6, 7, 8 };
        int[] val = { 3, 5, 8, 9, 10, 17, 17, 20 };

        System.out.println(maximizeCuts(8, cuts, val));
    }
}