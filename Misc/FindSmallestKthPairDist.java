import java.util.*;

public class FindSmallestKthPairDist {
    private boolean enough(int[] nums, int k, int mid){
        
        int count = 0;
        
        for (int i = 0, j = 0; i < nums.length; i++) {
            while (j < nums.length && nums[j] - nums[i] <= mid) j++;
            count += j - i - 1;
        }
        
        return count >= k;
    }
    
    public int smallestDistancePair(int[] nums, int k) {
        
        Arrays.sort(nums);
        
        int l = 0;
        int h = nums[nums.length - 1] - nums[0];
        
        int ans = h;
        while(l <= h){
            int mid = l + (h - l) / 2;
            
            if(enough(nums, k, mid)){
                ans = Math.min(ans, mid);
                h = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        
        return ans;
    }
}
