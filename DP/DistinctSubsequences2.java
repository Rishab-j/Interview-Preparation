import java.util.*;

public class DistinctSubsequences2 {
    public int distinctSubseqII(String str) {
        int mod = (int) 1e9 + 7;
        
        String s = '$' + str;
        int n = s.length();
        
        int[] dp = new int[n];
        
        int[] pos = new int[26];
        Arrays.fill(pos, -1);
        
        dp[0] = 1;
        
        for(int i = 1; i < n; i++){
            dp[i] = (2 * dp[i - 1]) % mod;
            
            if(pos[s.charAt(i) - 'a'] != -1){
                dp[i] = (dp[i] % mod - dp[pos[(s.charAt(i) - 'a')] - 1] % mod + mod) % mod;
            }
            
            pos[s.charAt(i) - 'a'] = i;
        }
        
        dp[n - 1]--;
        
        return dp[n - 1];
    }
}
