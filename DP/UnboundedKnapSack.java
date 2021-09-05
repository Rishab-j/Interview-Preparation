public class UnboundedKnapSack {
    
    // O(n^2) space
    static int knapSack(int N, int W, int val[], int wt[]){
        // code here
        
        int[][] dp = new int[N + 1][W + 1];
        
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= W; j++){
                if(j - wt[i - 1] >= 0){
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - wt[i - 1]] + val[i - 1]);    
                }
                
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
            }
        }
        return dp[N][W];
    }
    

    // O(n) space
    static int knapSack_2(int N, int W, int val[], int wt[]){
        // code here
        
        int[] dp = new int[W + 1];
        
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= W; j++){
                if(j - wt[i - 1] >= 0){
                    dp[j] = Math.max(dp[j], dp[j - wt[i - 1]] + val[i - 1]);    
                }
            }
        }
        
        return dp[W];
    }
}
