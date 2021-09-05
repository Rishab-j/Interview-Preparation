public class CherryPickup2 {
    public int cherryPickup(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;

        Integer[][][] dp = new Integer[N][M][M];

        return Math.max(0, pick(grid, N, M, 0, 0, M - 1, dp));
    }

    private int pick(int[][] grid, int n, int m, int r1, int c1, int c2, Integer[][][] dp) {

        if (r1 >= n || c1 < 0 || c2 < 0 || c1 >= m || c2 >= m)
            return Integer.MIN_VALUE;

        if (dp[r1][c1][c2] != null)
            return dp[r1][c1][c2];

        if (r1 == n - 1) {
            if (c1 == c2) {
                dp[r1][c1][c2] = grid[r1][c1];
            } else {
                dp[r1][c1][c2] = grid[r1][c1] + grid[r1][c2];
            }

            return dp[r1][c1][c2];
        }

        int cherries = 0;

        if (c1 == c2) {
            cherries = grid[r1][c1];
        } else {
            cherries = grid[r1][c1] + grid[r1][c2];
        }

        int temp1 = pick(grid, n, m, r1 + 1, c1, c2, dp);
        int temp2 = pick(grid, n, m, r1 + 1, c1, c2 - 1, dp);
        int temp3 = pick(grid, n, m, r1 + 1, c1, c2 + 1, dp);

        int temp4 = pick(grid, n, m, r1 + 1, c1 + 1, c2, dp);
        int temp5 = pick(grid, n, m, r1 + 1, c1 + 1, c2 - 1, dp);
        int temp6 = pick(grid, n, m, r1 + 1, c1 + 1, c2 + 1, dp);

        int temp7 = pick(grid, n, m, r1 + 1, c1 - 1, c2, dp);
        int temp8 = pick(grid, n, m, r1 + 1, c1 - 1, c2 - 1, dp);
        int temp9 = pick(grid, n, m, r1 + 1, c1 - 1, c2 + 1, dp);

        cherries += Math.max(temp1, Math.max(temp2, Math.max(temp3,
                Math.max(temp4, Math.max(temp5, Math.max(temp6, Math.max(temp7, Math.max(temp8, temp9))))))));

        dp[r1][c1][c2] = cherries;

        return dp[r1][c1][c2];

    }
}
