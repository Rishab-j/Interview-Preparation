import java.util.*;

public class CoinChange1 {
    public int coinChange(int[] coins, int amount) {
        
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        dp[0] = 0;
        
        for(int c: coins){
            for(int a = c; a <= amount; a++){
                if(dp[a - c] == Integer.MAX_VALUE) continue;
                
                dp[a] = Math.min(dp[a], dp[a - c] + 1);
            }
        }
        
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
