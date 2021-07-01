import java.util.*;

public class MinSwapsRequiredToSort {
    class Node implements Comparable<Node>{
        int val;
        int i;
        
        Node(int val, int i){
            this.val = val;
            this.i = i;
        }
        
        @Override
        public int compareTo(Node o){
            return this.val - o.val;
        }
    }
    
    public int minSwaps(int nums[]){
        // Code here
        
        int n = nums.length;
        Node[] nodes = new Node[n];
        
        for(int i = 0; i < n; i++){
            Node node = new Node(nums[i],i);
            nodes[i] = node;
        }
        
        Arrays.sort(nodes);
        // for(Node node: nodes){
        //     System.out.print(node.val +"-"+ node.i + ", ");
        // }
        
        int ans = 0;
        boolean[] inCycle = new boolean[n];
        for(int i = 0; i < n; i++){
            if(inCycle[i] || nodes[i].i == i){
                inCycle[i] = true;
            }else{
                int j = i;
                int count = 0;
                while(!inCycle[j]){
                    inCycle[j] = true;
                    count++;
                    j = nodes[j].i;
                }
                
                ans += count - 1;
            }
            
        }
        return ans;
    }
}
