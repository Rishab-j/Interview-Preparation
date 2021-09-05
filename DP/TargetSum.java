import java.util.*;

public class TargetSum {

    // Method 1

    public int findTargetSumWays_memo(int[] nums, int n, int sum, int tar, int[][] dp) {

        if (n == 0)
            return dp[n][sum] = ((tar == (0 + sum)) ? 1 : 0);

        if (dp[n][sum] != -1)
            return dp[n][sum];

        int include = findTargetSumWays_memo(nums, n - 1, sum - nums[n - 1], tar, dp); // positive call
        int exclude = findTargetSumWays_memo(nums, n - 1, sum + nums[n - 1], tar, dp); // nrgative call

        return dp[n][sum] = include + exclude;
    }

    public int findTargetSumWays(int[] nums, int s) {
        if (nums.length == 0)
            return 0;

        int n = nums.length;
        int sum = 0;

        for (int i : nums)
            sum += i;
        if (s > sum || s < -sum)
            return 0;

        int[][] dp = new int[n + 1][2 * sum + 1];

        for (int[] a : dp)
            Arrays.fill(a, -1);

        return findTargetSumWays_memo(nums, n, sum, s + sum, dp);
    }

    // Method 2 (Best method)

    private int subsetSumEqualToX(int[] nums, int s) {

        // O(n^2) space

        // int[][] dp = new int[nums.length + 1][s + 1];

        // dp[0][0] = 1;

        // for(int i = 1; i <= nums.length; i++){
        // for(int j = 0; j <= s; j++){
        // if(j - nums[i - 1] >= 0){
        // dp[i][j] += dp[i - 1][j - nums[i - 1]];
        // }

        // dp[i][j] += dp[i - 1][j];
        // }
        // }

        // return dp[nums.length][s];



        // O(n) space

        int[] dp = new int[s + 1];
        dp[0] = 1;
        for (int n : nums) {
            for (int j = s; j >= 0; j--) {
                if (j - n >= 0) {
                    dp[j] += dp[j - n];
                }
            }
        }

        return dp[s];
    }

    public int findTargetSumWays_2(int[] nums, int target) {
        int total = 0;
        for (int a : nums)
            total += a;

        if (target > total)
            return 0;

        if ((total - target) % 2 != 0)
            return 0;

        int s = (total - target) / 2;

        return subsetSumEqualToX(nums, s);
    }
}
