import java.util.*;

class MapSum {

    class Node{
        Node[] nodes;
        boolean isEnd;
        int val;
        
        Node(){
            nodes = new Node[26];
        }
    }
    
    private Node root;
    private HashMap<String, Integer> map;
    
    /** Initialize your data structure here. */
    public MapSum() {
        root = new Node();
        map = new HashMap<>();
    }
    
    public void insert(String key, int v) {
        Node curr = root;
        
        int toAdd = v;
        
        if(map.containsKey(key)){
            toAdd -= map.get(key);
        }
        
        map.put(key, v);
        
        for(int i = 0; i < key.length(); i++){
            
            if(curr.nodes[key.charAt(i) - 'a'] == null){
                curr.nodes[key.charAt(i) - 'a'] = new Node();
            }
            
            curr.nodes[key.charAt(i) - 'a'].val += toAdd;
            
            // System.out.println(curr.nodes[key.charAt(i) - 'a'].val);
            
            curr = curr.nodes[key.charAt(i) - 'a'];
        }
        
        curr.isEnd = true;
    }
    
    public int sum(String prefix) {
        Node curr = root;
        
        for(int i = 0; i < prefix.length(); i++){
            if(curr.nodes[prefix.charAt(i) - 'a'] == null){
                return 0;
            }
            
            curr = curr.nodes[prefix.charAt(i) - 'a'];
        }
        
        // System.out.println(curr.val);
        
        return curr.val;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */