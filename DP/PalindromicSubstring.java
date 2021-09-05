public class PalindromicSubstring {
    public int countSubstrings(String s) {
        int n = s.length();

        boolean[][] dp = new boolean[n][n];

        int count = 0;

        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (i == j) {
                    dp[i][j] = true;
                    count++;
                } else if (i + 1 == j) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = true;
                        count++;
                    }
                } else {
                    if (s.charAt(i) == s.charAt(j)) {
                        if (dp[i + 1][j - 1] == true) {
                            dp[i][j] = true;
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }
}
