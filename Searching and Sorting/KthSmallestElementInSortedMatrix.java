public class KthSmallestElementInSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        
        int low = matrix[0][0];
        int high = matrix[n - 1][n - 1];
        
        // int ans = 
        while(low < high){
            int mid = low + (high - low) / 2;
            
            // int i = 0;
            // int j = n - 1;
            
            // int max = Integer.MIN_VALUE;
            int count = 0;
            
            // while(i < n){
            //     while(j >= 0){
            //         if(matrix[i][j] <= mid){
            //             max = Math.max(max, matrix[i][j]);
            //             // System.out.println(max);
            //             count += (j + 1);
            //             i++;
            //             // break;
            //         }else{
            //             j--;
            //             // break;
            //         }
            //     }
            //     // i++;
            // }
            
            int j = n - 1; 
            for(int i = 0; i < n; i++)  {
                while (j >= 0 && matrix[i][j] > mid) j--;  // the pointer j will only go in one direction
                count += (j + 1);
            }
            
            // System.out.println("out");
            
            if(count < k){
                low = mid + 1;
            }else{
                high = mid;
            }
        }
        
        return low;
    }
}
