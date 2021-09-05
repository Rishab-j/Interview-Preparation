public class SubsetSum {
    static Boolean isSubsetSum(int N, int arr[], int sum){
        // code here
        boolean[][] dp = new boolean[N + 1][sum + 1];
        
        for(int i = 0; i <= N; i++){
            for(int j = 0; j <= sum; j++){
                if(i == 0 && j == 0) dp[i][j] = true;
                else if(i == 0){
                    dp[i][j] = false;
                }else if(j == 0){
                    dp[i][j] = true;
                }else{
                    if(j - arr[i - 1] >= 0){
                        dp[i][j] = dp[i][j] || dp[i - 1][j - arr[i - 1]];
                    }
                    
                    dp[i][j] = dp[i][j] || dp[i - 1][j];
                }
            }
        }
        
        return dp[N][sum];
    }
}
