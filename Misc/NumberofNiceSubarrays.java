/**
 * NumberofNiceSubarrays
 */
public class NumberofNiceSubarrays {

    public int numberOfSubarrays(int[] nums, int k) {
        int ans = 0;
        int i = -1;
        int j = moveToKthOdd(nums, k);
        while (j < nums.length) {
            int nextI = moveToNextOdd(nums, i);
            int nextJ = moveToNextOdd(nums, j);
            ans += (nextI - i) * (nextJ - j);
            i = nextI;
            j = nextJ;
        }
        return ans;
    }

    private int moveToNextOdd(int[] nums, int oddPos) {
        for (int i = oddPos + 1; i < nums.length; i++) {
            if (nums[i] % 2 == 1)
                return i;
        }
        return nums.length;
    }

    private int moveToKthOdd(int[] nums, int k) {
        for (int i = 0, count = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1) {
                count++;
                if (count == k)
                    return i;
            }
        }

        return nums.length;
    }
}