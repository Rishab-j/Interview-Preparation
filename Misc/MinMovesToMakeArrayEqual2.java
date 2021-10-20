import java.util.*;

public class MinMovesToMakeArrayEqual2 {
    public int minMoves2(int[] nums) {
         
        Arrays.sort(nums);
        
        int count = 0;
        
        int median = nums[nums.length / 2];
        
        for(int a: nums){
            count += Math.abs(a - median);
        }
       
        return count;
   }
}
