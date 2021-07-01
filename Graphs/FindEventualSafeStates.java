import java.util.*;

public class FindEventualSafeStates {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        
        List<Integer> ans = new ArrayList<>();
        
        for(int i = 0; i < n; i++){
            if(dfs(graph,color,i)){
                ans.add(i);
            }
        }
        
        return ans;
    }
    
    public boolean dfs(int[][] graph, int[] color, int src){
        if(color[src] != 0) return color[src] == 2;
        
        color[src] = 1;
        for(int e: graph[src]){
            if(color[e] == 1 || !dfs(graph,color,e))
                return false;
        }
        color[src] = 2;
        
        return true;
    }
}
