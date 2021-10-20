/**
 * DecodeWays2
 */
public class DecodeWays2 {

    public class Solution {
        int M = 1000000007;

        public int numDecodings(String s) {
            Long[] memo = new Long[s.length()];
            return (int) ways(s, s.length() - 1, memo);
        }

        public long ways(String s, int i, Long[] memo) {
            if (i < 0)
                return 1;
            if (memo[i] != null)
                return memo[i];
            if (s.charAt(i) == '*') {
                long res = 9 * ways(s, i - 1, memo) % M;
                if (i > 0 && s.charAt(i - 1) == '1')
                    res = (res + 9 * ways(s, i - 2, memo)) % M;
                else if (i > 0 && s.charAt(i - 1) == '2')
                    res = (res + 6 * ways(s, i - 2, memo)) % M;
                else if (i > 0 && s.charAt(i - 1) == '*')
                    res = (res + 15 * ways(s, i - 2, memo)) % M;
                memo[i] = res;
                return memo[i];
            }
            long res = s.charAt(i) != '0' ? ways(s, i - 1, memo) : 0;
            if (i > 0 && s.charAt(i - 1) == '1')
                res = (res + ways(s, i - 2, memo)) % M;
            else if (i > 0 && s.charAt(i - 1) == '2' && s.charAt(i) <= '6')
                res = (res + ways(s, i - 2, memo)) % M;
            else if (i > 0 && s.charAt(i - 1) == '*')
                res = (res + (s.charAt(i) <= '6' ? 2 : 1) * ways(s, i - 2, memo)) % M;
            memo[i] = res;
            return memo[i];
        }
    }

    // Tabulation

    public class Solution_2 {
        int M = 1000000007;

        public int numDecodings(String s) {
            long[] dp = new long[s.length() + 1];
            dp[0] = 1;
            dp[1] = s.charAt(0) == '*' ? 9 : s.charAt(0) == '0' ? 0 : 1;
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == '*') {
                    dp[i + 1] = 9 * dp[i] % M;
                    if (s.charAt(i - 1) == '1')
                        dp[i + 1] = (dp[i + 1] + 9 * dp[i - 1]) % M;
                    else if (s.charAt(i - 1) == '2')
                        dp[i + 1] = (dp[i + 1] + 6 * dp[i - 1]) % M;
                    else if (s.charAt(i - 1) == '*')
                        dp[i + 1] = (dp[i + 1] + 15 * dp[i - 1]) % M;
                } else {
                    dp[i + 1] = s.charAt(i) != '0' ? dp[i] : 0;
                    if (s.charAt(i - 1) == '1')
                        dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;
                    else if (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')
                        dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;
                    else if (s.charAt(i - 1) == '*')
                        dp[i + 1] = (dp[i + 1] + (s.charAt(i) <= '6' ? 2 : 1) * dp[i - 1]) % M;
                }
            }
            return (int) dp[s.length()];
        }
    }
}