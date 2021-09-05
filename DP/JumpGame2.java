import java.util.*;

public class JumpGame2 {
    
    //O(n^2)
    public int jump(int[] nums) {
        int n = nums.length;
        
        int[] jumps = new int[n];
        Arrays.fill(jumps, Integer.MAX_VALUE);
        jumps[0] = 0;
        
        for(int i = 0; i < n; i++){
            int jump = nums[i];
            for(int j = 1; j <= jump; j++){
                if(i + j < n){
                    jumps[i + j] = Math.min(jumps[i + j], jumps[i] + 1);
                }
            }
        }
        
        return jumps[n - 1];
    }
    
    
    // O(n)
    public int jump_2(int[] A) {
        
        int jumps = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < A.length - 1; i++) {
            curFarthest = Math.max(curFarthest, i + A[i]);
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;
            }
        }
        return jumps;
    }
}
