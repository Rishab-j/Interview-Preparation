public class CountTotalSetBits {
    private static int largestPowerOf2(int n){
        int x = 0;
        
        while((1 << x) <= n){
            x++;
        }
        
        return x - 1;
    }
    
    //Function to return sum of count of set bits in the integers from 1 to n.
    public static int countSetBits(int n){
    
        // Your code here
        if(n == 0) return 0;
        
        int x = largestPowerOf2(n);
        
        int setBitsUpto2x = x * (1 << (x - 1));
        int msbRest = n - (1 << x) + 1;
        
        int rest = n - (1 << x);
        
        int ans = setBitsUpto2x + msbRest + countSetBits(rest);
        
        return ans;
    }
}
