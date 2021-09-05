public class LongestSumSubArrayAtleastK_Elements {
    public long maxSumWithK(long a[], long n, long k) {

        int size = a.length;
        // int K = (int) k;

        long[] kadanes = new long[size];

        kadanes[0] = a[0];

        long currSum = 0;

        for (int i = 0; i < size; i++) {
            if (currSum >= 0) {
                kadanes[i] = a[i] + currSum;
                currSum += a[i];
            } else {
                kadanes[i] = a[i];
                currSum = a[i];
            }
        }

        int window = (int) k;

        long curr = 0;
        long osum = 0;

        for (int i = 0; i < window; i++) {
            curr += a[i];
        }

        osum = curr;

        for (int i = window; i < size; i++) {
            curr = curr - a[i - window] + a[i];
            if (curr > osum) {
                osum = curr;
            }

            if (curr + kadanes[i - window] > osum) {
                osum = curr + kadanes[i - window];
            }
        }

        return osum;
    }
}
