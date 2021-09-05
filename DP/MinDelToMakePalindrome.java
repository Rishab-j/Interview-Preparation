public class MinDelToMakePalindrome {
    public int minDeletions(String S) {
        int n = S.length();
        
        int[][] dp = new int[n][n];
        
        for(int gap = 0; gap < n; gap++){
            for(int i = 0, j = gap; j < n; i++, j++){
                if(gap == 0){
                    dp[i][j] = 1;
                }else{
                    if(S.charAt(i) == S.charAt(j)){
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    }else{
                        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    }
                }
            }
        }
        
        return n - dp[0][n - 1];
    }
}
