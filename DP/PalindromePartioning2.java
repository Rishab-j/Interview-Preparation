import java.util.*;

public class PalindromePartioning2 {

    private void palindromicSubstring(boolean[][] dp, String s){
        int n = s.length();
        
        for(int gap = 0; gap < n; gap++){
            for(int i = 0, j = gap; j < n; i++, j++){
                if(i == j){
                    dp[i][j] = true;
                }else if(i + 1 == j){
                    if(s.charAt(i) == s.charAt(j))
                        dp[i][j] = true;
                }else{
                    if(s.charAt(i) == s.charAt(j)){
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
            }
        }
    }

    public int minCut_(String s, boolean[][] pal){ // (O(n^3))
        
        int n = s.length();
        
        int[][] dp = new int[n][n];
        for(int[] d: dp){
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        
        for(int gap = 0; gap < n; gap++){
            for(int i = 0, j = gap; j < n; i++, j++){
                if(gap == 0){
                    dp[i][j] = 0;   
                }else if(gap == 1){
                    if(pal[i][j]){
                        dp[i][j] = 0;
                    }else{
                        dp[i][j] = 1;
                    }
                }
                else{
                    if(!pal[i][j]){
                       for(int cut = i; cut < j; cut++){
                            dp[i][j] = Math.min(dp[i][j], dp[i][cut] + dp[cut + 1][j] + 1);
                        } 
                    }else{
                        dp[i][j] = 0;
                    }      
                } 
            }
        }
        
        return dp[0][n - 1];
    }
    
    private int minCut(String s, boolean[][] pal){  // (O(n^2))
        
        int n = s.length();
        
        int[] dp = new int[n];
        
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        dp[0] = 0;
        
        for(int i = 1; i < n; i++){
            if(pal[0][i]){
                dp[i] = 0;
            }else{
                for(int cut = 0; cut < i; cut++){
                    if(pal[cut + 1][i]){
                        dp[i] = Math.min(dp[i], dp[cut] + 1);
                    }
                }    
            }
        }
        
        return dp[n - 1];
    }
    
    public int minCut(String s) {
        int n = s.length();
        
        boolean[][] palindrome = new boolean[n][n];
        
        palindromicSubstring(palindrome, s);
        
        return minCut(s, palindrome);
    }
}