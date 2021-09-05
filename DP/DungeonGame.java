public class DungeonGame {
    
    public int calculateMinimumHP(int[][] dungeon) {
        
        int n = dungeon.length;
        int m = dungeon[0].length;
        
        int[][] dp = new int[n][m];
        
        if(dungeon[n - 1][m - 1] > 0){
            dp[n - 1][m - 1] = 0;
        }else if(dungeon[n - 1][m - 1] <= 0){
            dp[n - 1][m - 1] = -dungeon[n - 1][m - 1];
        }
        
        for(int i = n - 2; i >= 0; i--){
            if(dungeon[i][m - 1] >= dp[i + 1][m - 1]){
                dp[i][m - 1] = 0;
            }else{
                dp[i][m - 1] = dp[i + 1][m - 1] - dungeon[i][m - 1];
            }
        }
        
        for(int j = m - 2; j >= 0; j--){
            if(dungeon[n - 1][j] >= dp[n - 1][j + 1]){
                dp[n - 1][j] = 0;
            }else{
                dp[n - 1][j] = dp[n - 1][j + 1] - dungeon[n - 1][j];
            }
        }
        
        for(int i = n - 2; i >= 0; i--){
            for(int j = m - 2; j >= 0; j--){    
                if(dungeon[i][j] > dp[i + 1][j] || dungeon[i][j] > dp[i][j + 1]){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
                }
            }
        }

        return dp[0][0] + 1;
    }
}
