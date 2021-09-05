import java.util.*;

public class EqualSumPartition {

    public boolean canPartition(int[] nums) {  // Space -> O(n^2)
        
        int n = nums.length;
        int totalSum = 0;
        
        for(int a: nums) totalSum += a;
        
        if(totalSum % 2 != 0){
            return false;
        }
        
        int target = totalSum / 2;
        
        boolean[][] dp = new boolean[n + 1][target + 1];
        
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= target; j++){
                if(i == 0 && j == 0){
                    dp[i][j] = true;
                }else if(i == 0){
                    dp[i][j] = false;
                }else if(j == 0){
                    dp[i][j] = true;
                }else{
                    if(j - nums[i - 1] >= 0){
                        dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i - 1]];
                    }
                    
                    dp[i][j] = dp[i][j] || dp[i - 1][j];
                }
            }
        }
        
        return dp[n][target];
    }


    public boolean canPartition_2(int[] nums) { // Space -> O(n)
        
        int sum = 0;
    
        for (int num : nums) {
            sum += num;
        }

        if ((sum & 1) == 1) {
            return false;
        }
        sum /= 2;

        boolean[] dp = new boolean[sum+1];
        Arrays.fill(dp, false);
        dp[0] = true;

        for (int num : nums){
            for (int i = sum; i >= 0; i--) {
                if(i - num >= 0){
                    dp[i] = dp[i] || dp[i - num];
                } 
            }
            
            if(dp[sum]) return true;
        }
        
        return dp[sum];
    }
}
