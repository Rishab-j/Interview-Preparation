public class CapacityToShipPackagesWithinDDays {
    public int shipWithinDays(int[] weights, int days) {
        
        int l = Integer.MIN_VALUE;
        int h = 0;
        
        for(int i = 0; i < weights.length; i++){
            if(weights[i] > l) l = weights[i];
            h += weights[i];
        }
        
        int ans = Integer.MAX_VALUE;
        
        while(l <= h){
            
            int capacity = l + (h - l) / 2;
            
            int day = 0;
            int i = 0;
            
            while(i < weights.length){
                int sum = 0;
                while(i < weights.length && sum + weights[i] <= capacity){
                    sum += weights[i];
                    i++;
                }
                day++;
            }
            
            if(day <= days){
                ans = Math.min(ans, capacity);
                h = capacity - 1;
            }else{
                l = capacity + 1;
            }
            
        }
        
        return ans;
    }
}
