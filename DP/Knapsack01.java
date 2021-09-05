/**
 * 01Knapsack
 */
public class Knapsack01 {

    // Memoized

    static int solve(int[] wt, int[] val, int W, int n, int[][] dp){
        
        if(n == 0 || W == 0){
            dp[n][W] = 0;
            return 0;
        }
        
        if(dp[n][W] != -1) return dp[n][W];
        
        int profit = 0;
        
        if(W - wt[n - 1] >= 0){
            profit = Math.max(profit, solve(wt, val, W - wt[n - 1], n - 1, dp) + val[n - 1]);
        }
        
        profit = Math.max(profit, solve(wt, val, W, n - 1, dp));
        
        dp[n][W] = profit;
        
        return profit;
    }
    

    // Tabulation

    static int knapSack(int W, int wt[], int val[], int n) { 
         // your code here 
         int[][] dp = new int[n + 1][W + 1];
         
        //  for(int[] d: dp) Arrays.fill(d, -1);
        
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= W; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                }else{
                    if(j - wt[i - 1] >= 0){
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - wt[i - 1]]
                                                        + val[i - 1]);
                    }
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
            }
        }
         
        //  solve(wt, val, W, n, dp); // memoization
         
         return dp[n][W];
    }
    
}