public class DifferentPalindromicSubsequences {
    public int countPalindromicSubsequences(String s) {
        int n = s.length();

        int[][] dp = new int[n][n];

        char[] chars = s.toCharArray();

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int gap = 1; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (chars[i] == chars[j]) {
                    int left = i + 1;
                    int right = j - 1;

                    while (left <= right && chars[i] != chars[left]) {
                        left++;
                    }

                    while (left <= right && chars[i] != chars[right]) {
                        right--;
                    }

                    if (left > right) {
                        dp[i][j] = 2 * dp[i + 1][j - 1] + 2;
                    } else if (left == right) {
                        dp[i][j] = 2 * dp[i + 1][j - 1] + 1;
                    } else {
                        dp[i][j] = 2 * dp[i + 1][j - 1] - dp[left + 1][right - 1];
                    }
                } else {
                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                }

                dp[i][j] = dp[i][j] < 0 ? dp[i][j] + 1000000007 : dp[i][j] % 1000000007;
            }
        }

        return dp[0][n - 1];
    }

}