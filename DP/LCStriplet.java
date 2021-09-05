public class LCStriplet {
    int LCSof3(String A, String B, String C, int n1, int n2, int n3) { 
        // code here
        int n = A.length();
        int m = B.length();
        int o = C.length();
        
        int[][][] dp = new int[n + 1][m + 1][o + 1];
        
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                for(int k = 1; k <= o; k++){
                    if(A.charAt(i - 1) == B.charAt(j - 1) && A.charAt(i - 1) == C.charAt(k - 1)){
                        dp[i][j][k] = 1 + dp[i-1][j-1][k-1];
                    }else{
                        dp[i][j][k] = Math.max(dp[i-1][j][k],
                                        Math.max(dp[i][j - 1][k],dp[i][j][k - 1]));
                    } 
                }
            }
        }
        
        return dp[n][m][o];
    }
}
