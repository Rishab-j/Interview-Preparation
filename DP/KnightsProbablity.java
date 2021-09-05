public class KnightsProbablity {
    int[][] dir = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
    
    public double knightProbability(int n, int k, int row, int col) {
        
        if(k == 0) return (double)1;
        
        double[][] one = new double[n][n];
        double[][] two = new double[n][n];
        
        one[row][col] = 1.0;
        
        while(k-- > 0){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(one[i][j] != 0.0){
                        for(int[] d: dir){
                            int r = i + d[0];
                            int c = j + d[1];
                            
                            if(r >= 0 && r < n && c >= 0 && c < n){
                                two[r][c] += 0.125 * one[i][j];
                            }
                        }
                    }
                }
            }
            
            one = two;
            two = new double[n][n];
        }
        
        double ans = 0.0;
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                ans += one[i][j];
            }
        }
        
        return ans;
    }
}
