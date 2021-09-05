public class DistinctSubseq {


    // iterative
    public int numDistinct(String S, String T) {
        int[][] mem = new int[T.length() + 1][S.length() + 1];

        // filling the first row: with 1s
        for (int j = 0; j <= S.length(); j++) {
            mem[0][j] = 1;
        }

        // the first column is 0 by default in every other rows but the first, which we
        // need.

        for (int i = 0; i < T.length(); i++) {
            for (int j = 0; j < S.length(); j++) {
                if (T.charAt(i) == S.charAt(j)) {
                    mem[i + 1][j + 1] = mem[i][j] + mem[i + 1][j];
                } else {
                    mem[i + 1][j + 1] = mem[i + 1][j];
                }
            }
        }

        return mem[T.length()][S.length()];
    }


    // memoization
    public int ans(String s, String t, int i, int j, int[][] dp) {
        if (j == -1)
            return 1;

        if (i < 0)
            return 0;

        if (dp[i][j] != 0)
            return dp[i][j];

        int count = 0;
        if (s.charAt(i) == t.charAt(j)) {
            count = ans(s, t, i - 1, j - 1, dp) + ans(s, t, i - 1, j, dp);
        } else {
            count = ans(s, t, i - 1, j, dp);
        }

        dp[i][j] = count;

        return count;
    }

}
