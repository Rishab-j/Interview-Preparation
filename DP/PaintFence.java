public class PaintFence {
    public int numWays(int n, int k) {
        // write your code here

        if(n == 1) return k;

        int same = 0;
        int diff = k;

        for(int i = 2; i <= n; i++){
            int total = same + diff;
            same = diff;
            diff = total * (k - 1);
        }

        return same + diff;
    }
}
