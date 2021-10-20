import java.util.*;

public class KClosestElements {
    class Solution {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            int n = arr.length;
            
            int l = 0;
            int r = n - 1;
            
            while(l < r){
                int mid = l + (r - l) / 2;
                
                if(arr[mid] > x){
                    r = mid;
                }else{
                    l = mid + 1;
                }
            }
            
            int L = l - 1;
            int R = l;
            
            while(k-- > 0){
                if(R >= n || L >= 0 && x - arr[L] <= arr[R] - x) L--;
                else R++;
            }
            
            ArrayList<Integer> ans = new ArrayList<>();
            for(int i = L + 1; i < R; i++){
                ans.add(arr[i]);
            }
            
            return ans;
        }
    }
}
