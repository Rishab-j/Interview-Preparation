public class FindSmallestDivisorGivenThreshold {
    public int smallestDivisor(int[] nums, int threshold) {
        int l = 1;
        int r = Integer.MIN_VALUE;
        
        for(int i = 0; i < nums.length; i++){
            r =  Math.max(r, nums[i]);
        }
        
        // int ans = r;
        while(l <= r){
            
            int div = l + (r - l) / 2;
            
            int sum = 0;
            int i = 0;
            
            while(i < nums.length){
                // hours += piles[i] / rate;
                // if (piles[i] % rate != 0)
                //     hours++;
                // i++;
                
                // another way
                sum += (nums[i] - 1)/ div + 1; // method to find ceil
                i++;
            }
            
            // System.out.println( rate + " -> " + hours);
            
            if(sum <= threshold){
                // ans = Math.min(ans, rate);
                r = div - 1;
            }else{
                l = div + 1;
            }
            
        }
        
        // return ans;  // also gives correct answer but takes more time
        return l;
    }
}
