public class MinSumPartition {
    public int minDifference(int arr[], int n) {
        // Your code goes here

        if (arr.length == 1)
            return arr[0];

        int totalSum = 0;

        for (int a : arr)
            totalSum += a;

        int target = (totalSum + 1) / 2;

        boolean[] dp = new boolean[target + 1];

        dp[0] = true;
        int min = Integer.MAX_VALUE;

        for (int a : arr) {
            for (int i = target; i >= 0; i--) {
                if (i - a >= 0) {
                    dp[i] = dp[i] || dp[i - a];

                    if (dp[i])
                        min = Math.min(min, Math.abs((totalSum - i) - i));
                }
            }
        }

        return min;
    }
}
