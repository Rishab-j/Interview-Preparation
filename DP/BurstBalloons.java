public class BurstBalloons {
    public int maxCoins(int[] nums) {

        int n = nums.length;

        int[] arr = new int[n + 2];

        arr[0] = 1;
        arr[arr.length - 1] = 1;

        for (int i = 1; i < arr.length - 1; i++) {
            arr[i] = nums[i - 1];
        }

        return burst(arr, 0, arr.length - 1, new int[arr.length][arr.length]);
    }

    private int burst(int[] arr, int l, int r, int[][] dp) {

        if (l + 1 == r)
            return 0;

        if (dp[l][r] != 0)
            return dp[l][r];

        int max = Integer.MIN_VALUE;

        for (int i = l + 1; i < r; i++) {
            int left = burst(arr, l, i, dp);
            int right = burst(arr, i, r, dp);

            int cost = left + arr[l] * arr[i] * arr[r] + right;

            max = Math.max(max, cost);
        }

        dp[l][r] = max;
        return dp[l][r];
    }

    // Iterative
    public int burst_2(int[] arr, int l, int r, int[][] dp) {

        for (int gap = 2; gap < arr.length; gap++) {
            for (int left = 0, right = gap; right < arr.length; left++, right++) {
                for (int cut = left + 1; cut < right; cut++) {
                    dp[left][right] = Math.max(dp[left][right],
                            dp[left][cut] + arr[left] * arr[cut] * arr[right] + dp[cut][right]);
                }
            }
        }

        return dp[0][r];
    }
}
