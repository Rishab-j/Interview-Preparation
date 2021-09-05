import java.util.*;

public class PalindromePartioning {
    List<List<String>> ans = new ArrayList<>();
    
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
    
    
    private void partition(String s, int start, int end, ArrayList<String> list, boolean[][] palindrome){
        
        if(palindrome[start][end]){
            ArrayList<String> base = new ArrayList<>(list);
            base.add(s.substring(start, end + 1));
            ans.add(base);
        }
        
        for(int i = start; i < end; i++){
            if(palindrome[start][i]){
                list.add(s.substring(start, i + 1));
                partition(s, i + 1, end, list, palindrome);
                list.remove(list.size() - 1);
            }
        }
    }
    
    
    public List<List<String>> partition(String s) {
        int n = s.length();
        
        boolean[][] palindrome = new boolean[n][n];
        
        palindromicSubstring(palindrome, s);
        
        partition(s, 0, n - 1, new ArrayList<String>(), palindrome);
        
        return ans;
    }
}
