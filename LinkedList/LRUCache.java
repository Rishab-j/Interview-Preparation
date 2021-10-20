import java.util.*;

class LRUCache {

    class Node{
        int key;
        int val;
        Node next;
        Node prev;
        
        Node(int k, int v){
            key = k;
            val = v;
        }
    }
    
    HashMap<Integer, Node> map;
    
    Node head = null;
    Node tail = null;
    
    int size = 0;
    int maxSize;
    
    
    private void addLast(Node node){
        if(this.size == 0){
            this.head = node;
            this.tail = node;
        }else{
            this.tail.next = node;
            node.prev = this.tail;
            this.tail = node;
        }
        
        this.size++;
    }
    
    private void removeNode(Node node){
        if(this.size == 1){
            this.head = null;
            this.tail = null;
        }else if(node.next == null){
            this.tail = node.prev;
            this.tail.next = null;
            node.prev = null;
        }else if(node.prev == null){
            this.head = node.next;
            this.head.prev = null;
            node.next = null;
        }else{
            
            Node next = node.next;
            Node prev = node.prev;
            
            next.prev = prev;
            prev.next = next;
            
            node.next = null;
            node.prev = null;
        }
        
        this.size--;
    }
    
    public LRUCache(int capacity) {
        this.maxSize = capacity;
        this.map = new HashMap<>();
    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }else{
            
            Node node = map.get(key);
            
            removeNode(node);
            addLast(node);
            
            return node.val;
        }
    }
    
    public void put(int key, int value) {
        if(!map.containsKey(key)){
            
            Node node = new Node(key, value);
            map.put(key, node);
            
            addLast(node);
            
            if(this.size > this.maxSize){
                
                node = this.head;
                
                map.remove(node.key);
                
                removeNode(node);
                
            }
        }else{
            int val = get(key);
            if(val != value){
                map.get(key).val = value;
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */