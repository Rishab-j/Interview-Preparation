import java.util.*;

public class ReconstructItinerary {
    HashMap<String,PriorityQueue<String>> graph;
    LinkedList<String> ans; 
    
    public void dfs(String src){
        
        PriorityQueue<String> edges = graph.get(src);
        while(edges != null && !edges.isEmpty()){
            String edge = edges.remove();
            dfs(edge);
        }
        
        ans.addFirst(src);
        
    }
    
    public List<String> findItinerary(List<List<String>> tickets) {
        
        graph = new HashMap<>();
        for(List<String> ticket : tickets){
            graph.putIfAbsent(ticket.get(0),new PriorityQueue<String>());
            graph.get(ticket.get(0)).add(ticket.get(1));
        }
        
        ans = new LinkedList<String>();
        
        dfs("JFK");
        
        return ans;
    }
}
