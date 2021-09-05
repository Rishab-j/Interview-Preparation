/**
 * SplitLargestSum
 */
public class SplitLargestSum {

    private boolean isValid(int[] nums, int sum, int m){
        int sub = 0;
            
        int i = 0;
        while(i < nums.length){
            int s = 0;

            while(i < nums.length && s + nums[i] <= sum){
                s += nums[i];
                i++;
            }

            sub++;
        }
        
        return sub <= m;
    }
    
    public int splitArray(int[] nums, int m) {
        int l = Integer.MIN_VALUE;
        int h = 0;
        for(int n: nums){
            l = Math.max(l, n);
            h += n;
        }
        
        int ans = Integer.MAX_VALUE;
        while(l <= h){
            int sum = l + (h - l) / 2;
            
            if(isValid(nums, sum, m)){
                ans = sum;
                h = sum - 1;
            }else{
                l = sum + 1;
            }
        }
        
        return ans;
    }
}