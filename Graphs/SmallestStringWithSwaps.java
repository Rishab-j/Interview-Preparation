import java.util.*;

public class SmallestStringWithSwaps {
    int[] par;
    
    public int find(int x){
        if(par[x] == x) return x;
        return par[x] = find(par[x]);
    }
    
    public void union(int x, int y){
        par[find(x)] = par[find(y)];
    }
    
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        par = new int[n];
        for(int i = 0; i < n; i++){
            par[i] = i;
        }
        
        for(List<Integer> pair: pairs){
            union(pair.get(0),pair.get(1));
        }
        
        
        char[] word = s.toCharArray();
        HashMap<Integer,PriorityQueue<Character>> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            int index = find(i);
            map.putIfAbsent(index,new PriorityQueue<>());
            map.get(index).add(word[i]);
        }
        
        // for(int k : map.keySet()){
        //     Collections.sort(map.get(k));
        // }
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < n; i++){
            int index = find(i);
            sb.append(map.get(index).remove());
        }
        
        return sb.toString();
    }
}
