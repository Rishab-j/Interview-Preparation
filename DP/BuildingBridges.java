import java.util.*;
 
class CityPairs // Create user-defined class
{
    int north, south;
    CityPairs(int north, int south) // Constructor
    {
        this.north = north;
        this.south = south;
    }
}
// Use Comparator for manual sorting
class MyCmp implements Comparator<CityPairs>
{
    public int compare(CityPairs cp1, CityPairs cp2)
    {
        // If 2 cities have same north coordinates
        // then sort them in increasing order
        // according to south coordinates.
        if (cp1.north == cp2.north)
            return cp1.south - cp2.south;
 
        // Sort in increasing order of
        // north coordinates.
        return cp1.north - cp2.north;
    }
}
public class BuildingBridges {
    // function to find the max. number of bridges
    // that can be built
    public static int maxBridges(CityPairs[] pairs, int n)
    {
        int[] LIS = new int[n];
        // By default single city has LIS = 1.
        Arrays.fill(LIS, 1);
 
        Arrays.sort(pairs, new MyCmp()); // Sorting->
                                         // calling
        // our self made comparator
 
        // Logic for Longest increasing subsequence
        // applied on south coordinates.
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[i].south >= pairs[j].south)
                    LIS[i] = Math.max(LIS[i], LIS[j] + 1);
            }
        }
        int max = LIS[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, LIS[i]);
        }
 
        // Return the max number of bridges that can be
        // built.
        return max;
    }
 

    // O(NlogN)
    private int findCeil(int n, int[] dp, int l, int h){
        
        int ci = -1;
        
        while(l <= h){
            int mid = l + (h - l) / 2;
            if(dp[mid] > n){  // only increasing i.e non decreasing
                ci = mid;
                h = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        
        return ci;
    }
    
    public int lengthOfLIS_2(int[] nums) {
        int n = nums.length;
        
        int[] dp = new int[n];
        
        dp[0] = nums[0];
        int lis = 1;
        
        for(int i = 1; i < n; i++){
            int ci = findCeil(nums[i], dp, 0, lis - 1);
            if(ci == -1){
                dp[lis] = nums[i];
                lis++;
            }else{
                dp[ci] = nums[i];
            }
        }
        
        return lis;
    }
}