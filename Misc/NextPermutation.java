public class NextPermutation {
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public void nextPermutation(int[] nums) {
        if (nums.length <= 1)
            return;

        int j = nums.length - 1;

        while (j >= 0) {

            if (j > 0 && nums[j - 1] < nums[j]) {
                j--;
                break;
            }

            j--;
        }

        if (j == -1)
            reverse(nums, 0, nums.length - 1);
        else {

            int i = j + 1;
            int idx = j;
            int max = nums[j];

            while (i < nums.length) {
                if (nums[i] > nums[j]) {
                    max = Math.min(max, nums[i]);
                    idx = i;
                }
                i++;
            }

            swap(nums, idx, j);
            reverse(nums, j + 1, nums.length - 1);

        }

    }
}
