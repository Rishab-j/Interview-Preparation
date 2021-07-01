import java.util.*;

public class MaxBipartiteMatching {
    int[][] rgraph;
    
    boolean bfs(int src, int sink, int[] flow, int[] parent){
        boolean[] visited = new boolean[sink+1];
        
        Queue<int[]> que = new LinkedList<int[]>();
        que.add(new int[]{src,0});
        visited[src] = true;
        parent[src] = -1;
        
        while(que.size() != 0){
            int[] r = que.remove();
            
            int cur = r[0];
            int cap = r[1];
            
            
            if(cur != 0)
                flow[0] = Math.min(flow[0],cap);
            
            // System.out.println(cur + " -> " + cap + " -> " + flow[0]);
            
            if(cur == sink){
                return true;
            }
            
            for(int i = 0; i < sink + 1; i++){
                if(rgraph[cur][i] != 0 && !visited[i]){
                    visited[i] = true;
                    parent[i] = cur;
                    que.add(new int[]{i,rgraph[cur][i]});
                }
            }
        }
        
        return false;
    }
    
    public int maximumMatch(int[][] G){
        // Code here
        
        int n = G.length;
        int m = G[0].length;
        
        rgraph = new int[n+m+2][n+m+2];
        
        for(int i=0;i<n;i++){
            rgraph[0][i+1] = 1;
        }
        for(int i=0;i<m;i++){
            rgraph[n+1+i][n+m+1] = 1;
        }
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++) rgraph[i+1][n+1+j] = G[i][j];
        }
        
        int[] parent = new int[n+m+2];
        int[] flow = new int[1];
        
        flow[0] = Integer.MAX_VALUE;
        
        int ans = 0;
        
        while(bfs(0,n+m+1,flow,parent)){
            // System.out.println('y');
            ans += flow[0];
            int i = n+m+1;
            while(i != 0){
                int par = parent[i];
                rgraph[par][i] -= flow[0];
                rgraph[i][par] += flow[0];
                i =  par;
            }
            
            
            flow[0] = Integer.MAX_VALUE;
            parent = new int[m+n+2];
        }
        
        return ans;
    }
    
}
