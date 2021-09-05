public class UniqueBSTs {
    public int numTrees(int n) {
        int[] cat = new int[n + 1];
        cat[0] = 1;
        cat[1] = 1;
        
        for(int i = 2; i <= n; i++){
            int ci = 0;
            for(int j = 0; j < i; j++){
                ci += cat[j] * cat[i - 1 - j];
            }
            
            cat[i] = ci;
        }
        
        return cat[n];
    }
}
