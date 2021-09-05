public class CountAllPalindromicSubseq {
    long mod = 1000000007;

    long countPS(String str) {
        // Your code here

        int n = str.length();

        long[][] dp = new long[n][n];

        return cps(str, 0, n - 1, dp);

    }

    long cps(String str, int i, int j, long[][] dp) {

        if (i > j)
            return 0;
        if (i == j)
            return 1;

        if (dp[i][j] != 0)
            return dp[i][j];

        long ans = 0;

        long a = cps(str, i + 1, j - 1, dp) % mod;
        long b = cps(str, i + 1, j, dp) % mod;
        long c = cps(str, i, j - 1, dp) % mod;

        if (str.charAt(i) == str.charAt(j)) {
            ans = (b + c + 1) % mod;
        } else {
            ans = ((((b + c) - a) % mod) + mod) % mod;
        }

        dp[i][j] = ans;

        return ans;
    }
}
