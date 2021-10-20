import java.util.*;

public class _4Sum2 {
    class Solution {
        public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
            HashMap<Integer, Integer> map = new HashMap<>();
            
            int n = nums1.length;
            
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    int A = nums1[i];
                    int B = nums2[j];
                    
                    map.put(A + B, map.getOrDefault(A + B, 0) + 1);
                }
            }
            
            int ans = 0;
            
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    int C = nums3[i];
                    int D = nums4[j];
                    
                    ans += map.getOrDefault(-1 * (C + D), 0);
                }
            }
            
            return ans;
        }
    }
}
