/**
 * RegularExpressionMatching
 */
public class RegularExpressionMatching {

    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        
        for(int i = 1; i <= m; i++){
            if(p.charAt(i - 1) == '*') dp[i][0] = dp[i - 2][0];
        }
        
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(s.charAt(j - 1) == p.charAt(i - 1) || p.charAt(i - 1) == '.'){
                    dp[i][j] = dp[i - 1][j - 1];
                }else if(p.charAt(i - 1) == '*'){
                    dp[i][j] = dp[i - 2][j];
                    if(!dp[i][j]){
                        if(s.charAt(j - 1) == p.charAt(i - 1 - 1) || p.charAt(i - 1 - 1) == '.'){
                            dp[i][j] = dp[i][j - 1];
                        }
                    }
                }
            }
        }
        
        // for(int i = 0; i <= m; i++){
        //     for(int j = 0; j <= n; j++){
        //         if(dp[i][j]){
        //             System.out.print("O  ");
        //         }else{
        //             System.out.print("X  ");
        //         }
        //     }
        //     System.out.println();
        // }
        
        return dp[m][n];
    }
}