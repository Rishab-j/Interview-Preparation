import java.util.*;

public class MaxFlow_FordFulkerson {
    int[][] graph;
    
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
                if(graph[cur][i] != 0 && !visited[i]){
                    visited[i] = true;
                    parent[i] = cur;
                    que.add(new int[]{i,graph[cur][i]});
                }
            }
        }
        
        return false;
    }
    
    int solve(int N, int M, ArrayList<ArrayList<Integer>> Edges){ 
        // code here
        graph = new int[N][N];
        for(ArrayList<Integer> edge: Edges){
            int u = edge.get(0) - 1;
            int v = edge.get(1) - 1;
            int w = edge.get(2);
            
            graph[u][v] += w;
            graph[v][u] += w;
            // graph[v][u] = w;
        }
        
        int[] parent = new int[N];
        int[] flow = new int[1];
        
        flow[0] = Integer.MAX_VALUE;
        
        int ans = 0;
        
        while(bfs(0,N-1,flow,parent)){
            // System.out.println('y');
            ans += flow[0];
            int i = N - 1;
            while(i != 0){
                int par = parent[i];
                graph[par][i] -= flow[0];
                graph[i][par] += flow[0];
                i =  par;
            }
            
            
            flow[0] = Integer.MAX_VALUE;
            parent = new int[N];
        }
        
        return ans;
    }
}
