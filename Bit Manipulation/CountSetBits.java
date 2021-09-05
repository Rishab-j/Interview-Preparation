public class CountSetBits {
    static int setBits(int N) {
        // code here
        
        int count = 0;
        
        while(N != 0){
            N -= N & -N;
            count++;
        }
        
        return count;
    }
}
