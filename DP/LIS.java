import java.util.*;

class LIS{

    // O(n^2)
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
                
        int ans = dp[0];
        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            
            ans = Math.max(ans, dp[i]);
        }
        
        return ans;
    }


    // O(NlogN)
    private int findCeil(int n, int[] dp, int l, int h){
        
        int ci = -1;
        
        while(l <= h){
            int mid = l + (h - l) / 2;
            if(dp[mid] >= n){
                ci = mid;
                h = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        
        return ci;
    }
    
    public int lengthOfLIS_2(int[] nums) {
        int n = nums.length;
        
        int[] dp = new int[n];
        
        dp[0] = nums[0];
        int lis = 1;
        
        for(int i = 1; i < n; i++){
            int ci = findCeil(nums[i], dp, 0, lis - 1);
            if(ci == -1){
                dp[lis] = nums[i];
                lis++;
            }else{
                dp[ci] = nums[i];
            }
        }
        
        return lis;
    }
}