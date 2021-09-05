class MedianofTwoSortedArrays{
    private int getMax(int[] nums, int mid){
        if(mid <= 0 || mid > nums.length) return Integer.MIN_VALUE;
        return nums[mid - 1];
    }
    
    private int getMin(int[] nums, int mid){
        if(mid < 0 || mid >= nums.length) return Integer.MAX_VALUE;
        return nums[mid];
    }
    
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums2.length < nums1.length){
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        
        int n = nums1.length;
        int m = nums2.length;
        
        int totalLength = n + m;
        int half = (totalLength + 1) / 2;
        
        int l = 0;
        int h = n;
        
        while(l <= h){
            int mid1 = l + (h - l) / 2;
            int mid2 = half - mid1;
            
            int leftX = getMax(nums1, mid1);
            int rightX = getMin(nums1, mid1);
            
            int leftY = getMax(nums2, mid2);
            int rightY = getMin(nums2, mid2);
            
            if(leftX <= rightY && leftY <= rightX){
                if(totalLength % 2 == 0){
                    double median = (Math.max(leftX, leftY) + Math.min(rightX, rightY)) / 2.0;
                    return median;
                }else{
                    return (double)Math.max(leftX, leftY);
                }
            }
            
            if(leftX > rightY){
                h = mid1 - 1;
            }else{
                l = mid1 + 1;
            }
        }
        
        return -1;
    }
}