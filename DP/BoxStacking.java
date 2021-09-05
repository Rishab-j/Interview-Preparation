import java.util.*;

public class BoxStacking {
    class Box implements Comparable<Box>{
     
        // h --> height, w --> width,
        // d --> depth
        int h, w, l;
         
        // for simplicity of solution,
        // always keep w <= d
 
        /*Constructor to initialise object*/
        public Box(int h, int l, int w) {
            this.h = h;
            this.w = w;
            this.l = l;
        }
         
        /*To sort the box array on the basis
        of area in decreasing order of area */
        @Override
        public int compareTo(Box o) {
            if(this.l == o.l) return o.w - this.w;
            else return this.l - o.l;
        }
    }
 
    
    
    public int maxHeight(int[] height, int[] width, int[] length, int n){
        // Code here
        
        Box[] boxes = new Box[3*n];
        
        for(int i = 0; i < n; i++){
            boxes[3*i] = new Box(height[i], Math.min(width[i],length[i]), Math.max(width[i],length[i]));
            boxes[3*i + 1] = new Box(length[i], Math.min(width[i],height[i]), Math.max(width[i],height[i]));
            boxes[3*i + 2] = new Box(width[i], Math.min(height[i],length[i]), Math.max(height[i],length[i]));
        }
        
        Arrays.sort(boxes);
        
        // for(int i = 0; i < 3 * n; i++){
        //     System.out.println(boxes[i].h + ", " + boxes[i].l + ", " + boxes[i].w + ", ");
        // }
        
        int[] dp = new int[3*n];
        
        for(int i = 0; i < 3 * n; i++){
            dp[i] = boxes[i].h;
        }
        
        
        dp[0] = boxes[0].h;
        int max = 0;
        
        for(int i = 1; i < 3 * n; i++){
            for(int j = 0; j < i; j++){
                // int h = boxes[i].h
                if(boxes[j].w < boxes[i].w){
                    dp[i] = Math.max(dp[i], dp[j] + boxes[i].h);
                }
            }
            
            max = Math.max(max, dp[i]);
        }
        
        
        
        return max;
    }
}
