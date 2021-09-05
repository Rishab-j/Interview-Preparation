import java.util.*;

public class BitonicSubsequence {
    public int LongestBitonicSequence(int[] nums){
        // Code here
        int n = nums.length;
        
        int[] lis = new int[n];
        int[] lds = new int[n];
        
        Arrays.fill(lis, 1);
        
        for(int i = 1; i < n; i++){
            for(int j = i - 1; j >= 0; j--){
                if(nums[j] < nums[i]){
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }
        
        Arrays.fill(lds, 1);
        
        for(int i = n - 2; i >= 0; i--){
            for(int j = n - 1; j >= i; j--){
                if(nums[j] < nums[i]){
                    lds[i] = Math.max(lds[i], lds[j] + 1);
                }
            }
        }
        
        // for(int l: lis) System.out.print(l + " ");
        // System.out.println();
        // for(int l: lds) System.out.print(l + " ");
        // System.out.println();
        
        int ans = 0;
        
        for(int i = 0; i < n; i++){
            ans = Math.max(ans, lis[i] + lds[i]);
        }
        
        return ans - 1;
    }
}
