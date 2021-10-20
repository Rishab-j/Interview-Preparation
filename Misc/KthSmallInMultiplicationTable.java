/**
 * KthSmallInMultiplicationTable
 */
public class KthSmallInMultiplicationTable {

    private boolean valid(int n, int m, int k, int mid) {

        int count = 0;

        for (int i = 1; i <= n; i++) {
            int a = mid / i;
            if (a == 0)
                break;
            count += Math.min(a, m);
        }

        return count >= k;
    }

    public int findKthNumber(int n, int m, int k) {
        int l = 1;
        int r = n * m;

        int ans = n * m;
        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (valid(n, m, k, mid)) {
                ans = Math.min(ans, mid);
                r = mid - 1;
            } else {
                l = mid + 1;
            }

        }

        return ans;
    }
}