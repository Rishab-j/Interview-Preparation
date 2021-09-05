public class BinaryStringWithoutConsOnes {
    long countStrings(int n) {
        // code here
        long zero = 1;
        long one = 1;
        int mod = 1000000007;
        
        for(int i = 2; i <= n; i++){
            long total = (one % mod + zero % mod) % mod;
            one = zero;
            zero = total;
        }
        
        return (one % mod + zero % mod) % mod;
    }
}
