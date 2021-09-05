public class LCS {

    // recursive

    public static int lcs(String str1, int n, String str2, int m, int[][] dp) {
        if (n == 0 || m == 0)
            return 0;
        
        if (dp[n][m] != 0)
            return dp[n][m];

        if (str1.charAt(n - 1) == str2.charAt(m - 1))
            return dp[n][m] = lcs(str1, n - 1, str2, m - 1, dp) + 1;
        else
            return dp[n][m] = Math.max(lcs(str1, n, str2, m - 1, dp), lcs(str1, n - 1, str2, m, dp));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        return lcs(text1, text1.length(), text2, text2.length(), dp);
    }

    // Iterative

    public int longestCommonSubsequence_2(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][m];
    }
}
