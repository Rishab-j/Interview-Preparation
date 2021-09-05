import java.util.*;

public class EggDrop {


    // O(n * k ^2)

    // tabulation
    public int superEggDrop(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
	    
	    for(int[] d: dp)
	        Arrays.fill(d, -1);
	    
	    // return solve(n, k, dp);
        
        for(int i = 1; i <= n; i++){
            dp[i][0] = 0; 
            dp[i][1] = 1;
        }
        
        for(int j = 1; j <= k; j++){
            dp[1][j] = j;
        }
        
        for(int i = 2; i <= n; i++){
            for(int j = 2; j <= k; j++){
                int ans = Integer.MAX_VALUE;
                for(int p = 1; p <= j; p++){
                    ans = Math.min(ans, Math.max(dp[i - 1][p - 1], dp[i][j - p]));
                }
                dp[i][j] = ans + 1;
            } 
        }
        
        return dp[n][k];
    }
    

    // Memoization
    
    int solve(int n, int k, int[][] dp){
	   // if(n == 0) return 0;
	    if(k == 1 || k == 0)
          return k;

        if(n == 1)
          return k;
	    
	    if(dp[n][k] != -1) return dp[n][k];
	    
	    int ans = Integer.MAX_VALUE;
	    
	    for(int i = 1; i <= k; i++){
	        int breaks = solve(n-1, i - 1, dp);
	        int safe = solve(n, k - i, dp);
	        
	       // System.out.println(n + " and " + k);
	       // System.out.println(breaks);
	       // System.out.println(safe);
	       // System.out.println("***************");
	        ans = Math.min(ans, Math.max(breaks, safe));
	    }
	    
	    dp[n][k] = ans + 1;
	    return dp[n][k];
	}




    // O(n * k)               // eggs , floors
    public int superEggDrop_2(int k, int n) {
        int[][] dp = new int[n + 1][k + 1];
        int m = 0;
        
        while(dp[m][k] < n){
            m++;
            
            for(int i = 1; i <= k; i++){
                dp[m][i] = dp[m - 1][i - 1] + dp[m - 1][i] + 1;
            }
        }
        
        return m;
    }

}
