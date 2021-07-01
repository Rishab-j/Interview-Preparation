import java.util.*;

public class BricksFallingWhenHit {
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    class UnionFind{
        int[] par;
        int[] size;
        
        UnionFind(int n){
            par = new int[n];
            for(int i = 0; i < n; i++){
                par[i] = i;   
            }
            
            size = new int[n];
            Arrays.fill(size,1);
        }
        
        int find(int x){
            if(par[x] == x) return x;
            return par[x] = find(par[x]);
        }
        
        void union(int x,int y){
            int par1 = find(x);
            int par2 = find(y);
            
            if(par1 != par2){
                par[par1] = par2;
                size[par2] += size[par1];
            }
        }
    }
    
    public int linearise(int r, int c, int col){
        return (r * col) + c + 1; 
    }
 
    public void group(int x, int y, int rows, int cols, int[][] grid, UnionFind uf){
        int index = linearise(x,y,cols);
        
        for(int[] dir: dirs){
            int nx = x + dir[0];
            int ny = y + dir[1];
            
            if(nx >= 0 && nx < rows && ny >= 0 && ny < cols && grid[nx][ny] == 1){
                int index1 = linearise(nx,ny,cols);
                
                uf.union(index, index1);
            }
        }
        
        if(x == 0){
            uf.union(0,index);
        }
    }
    
    
    public int[] hitBricks(int[][] grid, int[][] hits) {
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        UnionFind uf = new UnionFind((rows * cols) + 1);
        
        for(int[] hit: hits){
            int x = hit[0];
            int y = hit[1];
            
            if(grid[x][y] == 1) grid[x][y] = 2;
        }
        
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j] == 1){
                    group(i,j,rows,cols,grid,uf);
                }
            }
        }
        
        int n = hits.length;
        int[] bricksFall = new int[n];
        int i = n - 1;
        
        int numBricksLeft = uf.size[uf.find(0)];
        
        while(i >= 0){
            int[] hit = hits[i];
            int x = hit[0];
            int y = hit[1];
            
            if(grid[x][y] == 2){
                grid[x][y] = 1;
                group(x,y,rows,cols,grid,uf);
                
                int bricksNew = uf.size[uf.find(0)];
                bricksFall[i] = bricksNew - numBricksLeft - 1;
                
                if(bricksFall[i] == -1) bricksFall[i] = 0;
                
                numBricksLeft = bricksNew;
                
            }
            
            i--;
        }
        return bricksFall;
    }
}
