import java.util.Arrays;

public class MinimumCostToCutStick {
    public int minCost(int n, int[] cuts) {
        
        Arrays.sort(cuts);
        int[][] dp = new int[cuts.length][cuts.length];
        
        for(int i = 0; i < cuts.length; i++){
            for(int j = 0; j < cuts.length; j++){
                    dp[i][j] = -1; 
            }
        }
        
        solve(cuts, 0, cuts.length - 1, 0, n, dp);
        
        return dp[0][cuts.length - 1];
    }
    
    private int solve(int[] cuts, int start, int end, int i, int j, int[][] dp){
        
        if(start > end) return 0;
        
        if(dp[start][end] != -1) return dp[start][end];
        
        dp[start][end] = Integer.MAX_VALUE;
        
        for(int c = start; c <= end; c++){
            int left = solve(cuts, start, c - 1, i, cuts[c], dp);
            int right = solve(cuts, c + 1, end, cuts[c], j, dp);
            
            dp[start][end] = Math.min(dp[start][end], left + right + j - i);
        }
        
        return dp[start][end];
    }
}
