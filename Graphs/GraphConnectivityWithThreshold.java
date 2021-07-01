import java.util.*;

public class GraphConnectivityWithThreshold {
    int[] par;
    
    public int find(int x){
        if(x == par[x]) return x;
        return par[x] = find(par[x]);
    }
    
    public void union(int x, int y){
        par[find(x)] = par[find(y)];
    }
    
    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        
        par = new int[n+1];
        for(int i = 0; i < n+1; i++){
            par[i] = i;
        }
        
        for(int i = threshold + 1; i < n; i++){
            int mul = 1;
            
            while(mul * i > threshold && mul * i <= n){
                union(i,mul*i);
                mul++;
            }
        }
        
        ArrayList<Boolean> ans = new ArrayList<>();
        for(int[] query: queries){
            if(find(query[0]) == find(query[1])){
                ans.add(true);
            }else{
                ans.add(false);
            }
        }
        return ans;
    }
}
