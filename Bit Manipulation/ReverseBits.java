public class ReverseBits {
    public int reverseBits(int n) {
        int j = 31;
        int rev = 0;
        
        for(int i = 0; i <= 31; i++){
            int mask = 1 << i;
            
            if((n & mask) != 0){
                rev = rev | (1 << j);
            }
            
            j--;
        }
        
        return rev;
    }
}
