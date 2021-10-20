public class SpiralMatrix2 {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
         
        int left = 0;
        int up = 0;
        int right = n - 1;
        int down = n - 1;
        
        n = n * n;
        
        int i = 1;
        
        while(i <= n){
            
            for(int col = left; col <= right; col++){
                matrix[up][col] = i;
                i++;
            }
            
            for(int row = up + 1; row <= down; row++){
                matrix[row][right] = i;
                i++;
            }
            
            if (up != down) {
                // Traverse from right to left.
                for (int col = right - 1; col >= left; col--) {
                    matrix[down][col] = i;
                    i++;
                }
            }
            // Make sure we are now on a different column.
            if (left != right) {
                // Traverse upwards.
                for (int row = down - 1; row > up; row--) {
                    matrix[row][left] = i;
                    i++;
                }
            }
            
            left++;
            right--;
            up++;
            down--;
        }
        
        return matrix;
    }
}
