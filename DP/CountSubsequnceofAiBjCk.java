public class CountSubsequnceofAiBjCk {
    public int fun(String s) {
        // Write your code here
        int aCount = 0;
        int bCount = 0;
        int cCount = 0;
        int mod = 1000000007;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                aCount = ((2 * aCount) + 1) % mod;
            } else if (s.charAt(i) == 'b') {
                bCount = ((2 * bCount) % mod + aCount % mod) % mod;
            } else {
                cCount = ((2 * cCount) % mod + bCount % mod) % mod;
            }
        }

        return cCount;
    }
}
