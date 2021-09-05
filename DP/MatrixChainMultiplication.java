import java.util.*;

public class MatrixChainMultiplication {
    int matrixMultiplication(int N, int arr[]){
        // code here
        int[][] dp = new int[N][N];
        
        for(int[] d: dp)
            Arrays.fill(d, Integer.MAX_VALUE);
        
        return mcm(arr, 0, N - 1, dp);
    }
    
    int mcm(int[] arr, int l, int r, int[][] dp){
        
        for(int gap = 1; gap < arr.length; gap++){
            for(int left = 0, right = gap; right < arr.length; left++, right++){
                
                if(gap == 1) dp[left][right] = 0;
                else
                    for(int cut = left + 1; cut < right; cut++){
                        dp[left][right] = Math.min(dp[left][right], dp[left][cut] + arr[left]*arr[cut]*arr[right] + dp[cut][right]);
                    }   
            }
        }
        
        return dp[l][r];
    }
}
