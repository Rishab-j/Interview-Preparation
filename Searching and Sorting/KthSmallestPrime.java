public class KthSmallestPrime {
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        double l = 0, r = 1;
        int p = 0, q = 1;
        
        int n = A.length;
        
        while(l < r){
            
            int cnt  = 0;
            p = 0;
            
            double m = (l + r) / 2;

            int j = n - 1;
            for (int i = 0; i < n; i++) {
                while (j >= 0 && A[i] > m * A[n - 1 - j]) j--;
                cnt += (j + 1);

                if (j >= 0 && p * A[n - 1 - j] < q * A[i]) {
                    p = A[i];
                    q = A[n - 1 - j];
                }
            }

            if (cnt < K) {
                l = m;
            } else if (cnt > K) {
                r = m;
            } else {
                return new int[] {p, q};
            }
        }
        
        return new int[]{0, 0};
    }
}
