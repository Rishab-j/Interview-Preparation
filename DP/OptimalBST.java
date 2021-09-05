/**
 * OptimalBST
 */

import java.util.*;

public class OptimalBST {

    private static int solve(int[] keys, int[] freq, int[] pre){
        int n = keys.length;

        int[][] dp = new int[n][n];

        for(int[] d: dp) Arrays.fill(d, Integer.MAX_VALUE);

        for(int gap = 0; gap < n; gap++){
            for(int left = 0, right = gap; right < n; left++, right++){
                if(gap == 0){
                    dp[left][right] = freq[left];
                }else{
                    for(int cut = left; cut <= right; cut++){
                        int leftAns = cut == left ? 0: dp[left][cut - 1];
                        int rightAns = cut == right ? 0: dp[cut + 1][right];

                        int cost = pre[right] - (left == 0 ? 0 : pre[left - 1]);

                        dp[left][right] = Math.min(dp[left][right],leftAns + cost + rightAns);
                    }
                }
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) {

        int[] keys = {10, 12, 14};
        int[] freq = {34, 8, 50};
        
        int n = freq.length;

        int[] preFreq = new int[n];

        preFreq[0] = freq[0];
        for(int i = 1; i < n; i++){
            preFreq[i] = preFreq[i - 1] + freq[i];
        }
        
        System.out.println(solve(keys, freq, preFreq));
    }
}