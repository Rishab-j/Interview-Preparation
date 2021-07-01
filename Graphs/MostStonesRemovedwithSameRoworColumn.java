import java.util.*;

public class MostStonesRemovedwithSameRoworColumn {
    HashMap<Integer,Integer> set = new HashMap<>();
    int count = 0;
    
    public int find(int x){
        
//         if(!set.containsKey(x)){
//             set.put(x,x);
//             count++;
//         } 
        
        if(set.putIfAbsent(x,x) == null){
            count++;
        }
        
        if(x == set.get(x)) return set.get(x);
        set.put(x,find(set.get(x)));
        return set.get(x);
        
    }
    
    public void union(int x,int y){
        x= find(x);
        y= find(y);
        
        if(x != y){
            set.put(x,y);
            count--;
        }
    }
    
    public int removeStones(int[][] stones) {
        
        if(stones.length == 0) return 0;
        
        for(int[] stone: stones){
            union(stone[0],(-stone[1]-1));
        }
        
        return stones.length - count;
    }
}
