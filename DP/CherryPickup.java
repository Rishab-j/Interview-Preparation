public class CherryPickup {
    

    // 4 states

    public int cherryPickup(int[][] grid) {
        int N = grid.length;
        
        Integer[][][][] dp = new Integer[N][N][N][N];
        
        return Math.max(0,pick(grid, N, 0, 0, 0, 0, dp));
    }
    
    
    private int pick(int[][] grid, int n, int r1, int c1, int r2, int c2, Integer[][][][] dp){
        
        if(r1 >= n || c1 >= n || r2 >= n || c2 >= n || grid[r1][c1] == -1 || grid[r2][c2] == -1)
            return Integer.MIN_VALUE;
        
        if(dp[r1][c1][r2][c2] != null) return dp[r1][c1][r2][c2];
        
        if(r1 == n-1 && c1 == n-1){
            dp[r1][c1][r2][c2] = grid[r1][c1];
            return dp[r1][c1][r2][c2];
        }
        
        int cherries = 0;
        
        if(r1 == r2 && c1 == c2){
            cherries = grid[r1][c1];
        }else{
            cherries = grid[r1][c1] + grid[r2][c2];
        }
        
        int temp1 = pick(grid, n, r1 + 1, c1, r2 + 1, c2, dp);
        int temp2 = pick(grid, n, r1, c1 + 1, r2 + 1, c2, dp);
        int temp3 = pick(grid, n, r1 + 1, c1, r2, c2 + 1, dp);
        int temp4 = pick(grid, n, r1, c1 + 1, r2, c2 + 1, dp);
        
        cherries += Math.max(temp1, Math.max(temp2, Math.max(temp3, temp4)));
        
        dp[r1][c1][r2][c2] = cherries;
        
        
        return dp[r1][c1][r2][c2];
        
    }

    // 3 states
    public int cherryPickup_2(int[][] grid) {
        int N = grid.length;
        
        Integer[][][] dp = new Integer[N][N][N];
        
        return Math.max(0,pick_2(grid, N, 0, 0, 0, dp));
    }
    
    
    private int pick_2(int[][] grid, int n, int r1, int c1, int r2, Integer[][][] dp){
        
        int c2 = r1 + c1 - r2;
        
        if(r1 >= n || c1 >= n || r2 >= n || c2 >= n || grid[r1][c1] == -1 || grid[r2][c2] == -1)
            return Integer.MIN_VALUE;
        
        if(dp[r1][c1][r2] != null) return dp[r1][c1][r2];
        
        if(r1 == n-1 && c1 == n-1){
            dp[r1][c1][r2] = grid[r1][c1];
            return dp[r1][c1][r2];
        }
        
        int cherries = 0;
        
        if(r1 == r2 && c1 == c2){
            cherries = grid[r1][c1];
        }else{
            cherries = grid[r1][c1] + grid[r2][c2];
        }
        
        int temp1 = pick_2(grid, n, r1 + 1, c1, r2 + 1, dp);
        int temp2 = pick_2(grid, n, r1, c1 + 1, r2 + 1, dp);
        int temp3 = pick_2(grid, n, r1 + 1, c1, r2, dp);
        int temp4 = pick_2(grid, n, r1, c1 + 1, r2, dp);
        
        cherries += Math.max(temp1, Math.max(temp2, Math.max(temp3, temp4)));
        
        dp[r1][c1][r2] = cherries;
        
        
        return dp[r1][c1][r2];
        
    }
}
