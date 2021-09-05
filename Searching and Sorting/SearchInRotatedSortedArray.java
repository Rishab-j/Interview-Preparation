public class SearchInRotatedSortedArray {
    public int findMin(int[] nums) {
        int l = 0;
        int h = nums.length - 1;
        
        while(l < h){
            int mid = l + (h - l) / 2;
            if(nums[mid] > nums[h]){
                l = mid + 1;
            }else{
                h = mid;
            }
        }
        
        return l;
    }
    
    public int search(int[] nums, int l, int h, int target){
        int ans = -1;
        
        while(l <= h){
            int mid = l + (h - l) / 2;
            
            if(nums[mid] == target){
                ans = mid;
                break;
            }else if(nums[mid] < target){
                l = mid + 1;
            }else{
                h = mid - 1;
            }
        }
        
        return ans;
    }
    
    public int search(int[] nums, int target) {
        
        int m = findMin(nums);
        // System.out.println(m);
        int l = 0;
        int h = nums.length - 1;
        
        int left = search(nums, l, m - 1, target);
        // System.out.println(left);
        if(left == -1) return search(nums, m, h, target);
        
        return left;
        
    }
}
