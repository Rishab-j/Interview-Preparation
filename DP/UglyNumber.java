public class UglyNumber {
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        
        for(int i = 1; i < n; i++){
            int nextUgly = Math.min(2 * ugly[i2], Math.min(3 * ugly[i3], 5 * ugly[i5]));
            
            ugly[i] = nextUgly;
            
            if(nextUgly == 2 * ugly[i2]){
                i2++;
            }
            
            if(nextUgly == 3 * ugly[i3]){
                i3++;
            }
            
            if(nextUgly == 5 * ugly[i5]){
                i5++;
            }
            
        }
        
        return ugly[n - 1];
    }
}
