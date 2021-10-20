import java.util.*;

public class MinArrowsToBurstBalloons {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> {
            
            return a[1] < b[1] ? -1 : 1;
        });
        
        int end = points[0][1];
        int count = 1;
        
        for(int i = 1; i < points.length; i++){
            if(points[i][0] > end){
                count++;
                end = points[i][1];
            }
        }
        
        return count;
    }
}
