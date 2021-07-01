public class FlyodWarshall {
    public void shortest_distance(int[][] matrix){
        // Code here 
        int n = matrix.length;
        int INF = 999999;
        
        // int[][] graph = new int[n][n];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == -1){
                    matrix[i][j] = INF;
                }
            }
        }
        
        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(matrix[i][k] + matrix[k][j] < matrix[i][j]){
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                    }
                }
            }   
        }
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == INF){
                    matrix[i][j] = -1;
                }
            }
        }
    }
}
