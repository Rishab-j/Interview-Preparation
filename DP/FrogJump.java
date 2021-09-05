import java.util.*;

public class FrogJump {
    public boolean canCross(int[] stones) {
        
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for(int s: stones){
            map.put(s, new HashSet<Integer>());
        }
        
        int dest = stones[stones.length - 1];
        map.get(stones[0]).add(1);
        
        for(int s: stones){
            HashSet<Integer> set = map.get(s);
            for(int n: set){
                if(s + n == dest) return true;
                if(map.containsKey(s + n)){
                    map.get(s + n).add(n);
                    map.get(s + n).add(n + 1);
                    if(n - 1 > 0)
                        map.get(s + n).add(n - 1);
                }
            }
        }
        
        return false;
    }
}
