import java.util.*;

public class PolygonTriangulation {
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        
        for(int[] d: dp) Arrays.fill(d, Integer.MAX_VALUE);
        
        return calculate(values, 0, n - 1, dp);
    }
    
    private int calculate(int[] values, int l, int r, int[][] dp){
        
        if(l + 1 == r){
            return 0;
        }
        
        if(dp[l][r] != Integer.MAX_VALUE) return dp[l][r];
        
        for(int i = l + 1; i < r; i++){
            
            int left = calculate(values, l, i, dp);
            int right = calculate(values, i, r, dp);
            
            dp[l][r] = Math.min(dp[l][r], left + values[l] * values[i] * values[r] + right);
        }
        
        return dp[l][r];
    }


    // iterative
    public int minScoreTriangulation_2(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        
        for(int[] d: dp) Arrays.fill(d, Integer.MAX_VALUE);
        
        for(int gap = 1; gap < n; gap++){
            for(int l = 0, r = gap; r < n; l++, r++){
                
                if(gap == 1) dp[l][r] = 0;
                else
                    for(int cut = l + 1; cut < r; cut++){
                        dp[l][r] = Math.min(dp[l][r], dp[l][cut] + values[l] * values[cut] * values[r] + dp[cut][r]);
                    }
            }
        }
        
        return dp[0][n-1];
    }
}
