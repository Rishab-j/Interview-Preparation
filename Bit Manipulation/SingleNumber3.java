public class SingleNumber3 {
    public int[] singleNumber(int[] nums) {
        
        int xor = 0;
        for(int a: nums){
            xor ^= a;
        }
        
        int rmsb = ((xor) & (-xor));
        
        int x = 0;
        int y = 0;
        
        for(int a: nums){
            if((a & rmsb) != 0){
                x ^= a;
            }else{
                y ^= a;
            }
        }
        
        return new int[]{x, y};
    }
}
