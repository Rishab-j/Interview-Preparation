import java.util.*;

public class PacificAtlanticWaterFlow {

    // BFS
    private void fill(Queue<int[]> ocean, boolean[][] visited, int[][] heights){
        
        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        
        while(ocean.size() != 0){
            int[] coord = ocean.remove();
            
            int r = coord[0];
            int c = coord[1];
            
            for(int d = 0; d < 4; d++){
                int x = r + dir[d][0];
                int y = c + dir[d][1];
                
                if(x >= 0 && x < heights.length && y >= 0 && y < heights[0].length)
                    if(!visited[x][y] && heights[x][y] >= heights[r][c]){
                        visited[x][y] = true;
                        ocean.add(new int[]{x, y});
                    }
            }
            
        }
        
    }
    
    public List<List<Integer>> pacificAtlantic_2(int[][] heights) {
        
        Queue<int[]> pacific = new LinkedList<>();
        Queue<int[]> atlantic = new LinkedList<>();
        
        boolean[][] pacVisited = new boolean[heights.length][heights[0].length];
        boolean[][] atVisited = new boolean[heights.length][heights[0].length];
        
        for(int i = 0; i < heights[0].length; i++){
            pacific.add(new int[]{0, i});
            pacVisited[0][i] = true;
            
            atlantic.add(new int[]{heights.length - 1, i});
            atVisited[heights.length - 1][i] = true;
        }
        
        for(int i = 1; i < heights.length - 1; i++){
            pacific.add(new int[]{i, 0});
            pacVisited[i][0] = true;
            
            atlantic.add(new int[]{i, heights[0].length - 1});
            atVisited[i][heights[0].length - 1] = true;
        }
        
        pacific.add(new int[]{heights.length - 1, 0});
        pacVisited[heights.length - 1][0] = true;
        
        atlantic.add(new int[]{0, heights[0].length - 1});
        atVisited[0][heights[0].length - 1] = true;
        
        
        fill(pacific, pacVisited, heights);
        fill(atlantic, atVisited, heights);
        
        List<List<Integer>> list = new ArrayList<>();
        
        for(int i = 0; i < heights.length; i++){
            for(int j = 0; j < heights[0].length; j++){
                if(pacVisited[i][j] && atVisited[i][j]){
                    ArrayList<Integer> a = new ArrayList<>();
                    a.add(i);
                    a.add(j);
                    
                    list.add(a);
                }
            }
        }
        
        return list;
    }


    // DFS
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new LinkedList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        }
        int n = matrix.length, m = matrix[0].length;
        boolean[][]pacific = new boolean[n][m];
        boolean[][]atlantic = new boolean[n][m];
        for(int i=0; i<n; i++){
            dfs(matrix, pacific, Integer.MIN_VALUE, i, 0);
            dfs(matrix, atlantic, Integer.MIN_VALUE, i, m-1);
        }
        for(int i=0; i<m; i++){
            dfs(matrix, pacific, Integer.MIN_VALUE, 0, i);
            dfs(matrix, atlantic, Integer.MIN_VALUE, n-1, i);
        }
        for (int i = 0; i < n; i++) 
            for (int j = 0; j < m; j++) 
                if (pacific[i][j] && atlantic[i][j]) 
                    res.add(new int[] {i, j});
        return res;
    }
    
    int[][]dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    
    public void dfs(int[][]matrix, boolean[][]visited, int height, int x, int y){
        int n = matrix.length, m = matrix[0].length;
        if(x<0 || x>=n || y<0 || y>=m || visited[x][y] || matrix[x][y] < height)
            return;
        visited[x][y] = true;
        for(int[]d:dir){
            dfs(matrix, visited, matrix[x][y], x+d[0], y+d[1]);
        }
    }


}
