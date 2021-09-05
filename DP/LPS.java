public class LPS {

    // iterative

    public int longestPalindromeSubseq(String s) {
        int n = s.length();

        int[][] dp = new int[n][n];

        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (i == j)
                    dp[i][j] = 1;
                else {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = 2 + dp[i + 1][j - 1];
                    } else {
                        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    }
                }
            }
        }

        return dp[0][n - 1];
    }

    // recursive
    public int lps(String s, int i, int j, int[][] dp) {
        if (i > j) {
            return 0;
        }

        if (i == j)
            return 1;

        if (dp[i][j] != 0)
            return dp[i][j];

        int ans = 0;

        if (s.charAt(i) == s.charAt(j)) {
            ans = 2 + lps(s, i + 1, j - 1, dp);
        } else {
            ans = Math.max(lps(s, i + 1, j, dp), lps(s, i, j - 1, dp));
        }

        dp[i][j] = ans;
        return ans;
    }
}
