import java.util.Arrays;
import java.util.Comparator;

public class RussianDoll {
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
    
    public int maxEnvelopes(int[][] envelopes) {
        
        if(envelopes == null || envelopes.length == 0 
           || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
        
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                if(arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];
                else
                    return arr1[0] - arr2[0];
           } 
        });
        
        int n = envelopes.length;
        int[] dp = new int[n];
        dp[0] = envelopes[0][1];
        
        int lis = 1;
        
        for(int i = 1; i < n; i++){
            int ci = findCeil(envelopes[i][1], dp, 0, lis - 1);
            if(ci == -1){
                dp[lis] = envelopes[i][1];
                lis++;
            }else{
                dp[ci] = envelopes[i][1];
            }
        }
        
        
        return lis;
    }
}
