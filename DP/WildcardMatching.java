public class WildcardMatching {
    private String reduceString(String p){
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < p.length(); i++){
            if(p.charAt(i) == '*'){
                while(i < p.length() && p.charAt(i) == '*'){
                    i++;
                }
                i--;
            }
            
            sb.append(p.charAt(i));
            
        }
        return sb.toString();
    }
    
    public boolean isMatch(String s, String p) {
        p = reduceString(p);
        
        // System.out.println(p);
        
        int m = s.length();
        int n = p.length();
        
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        
        for(int i = 1; i <= n; i++){
            if(p.charAt(i - 1) == '*'){
                dp[i][0] = dp[i - 1][0];
            }
        }
        
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?'){
                    dp[i][j] = dp[i - 1][j - 1];
                }else if(p.charAt(i - 1) == '*'){
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        
        // for(int i = 0; i <= n; i++){
        //     for(int j = 0; j <= m; j++){
        //         if(dp[i][j]){
        //             System.out.print("O  ");
        //         }else{
        //             System.out.print("X  ");
        //         }
        //     }
        //     System.out.println();
        // }
        
        return dp[n][m];
    }
}
