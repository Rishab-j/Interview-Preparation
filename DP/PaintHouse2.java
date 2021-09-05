public class PaintHouse2 {
    public int minCostII(int[][] costs) {
        // write your code here

        if(costs.length == 0) return 0;

        int min = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;

        for(int j = 0; j < costs[0].length; j++){
            if(costs[0][j] < min){
                min2 = min;
                min = costs[0][j];
            }else if(costs[0][j] >= min && costs[0][j] < min2){
                min2 = costs[0][j];
            }
        }

        // System.out.println(min + ", " + min2);

        for(int i = 1; i < costs.length; i++){
            
            for(int j = 0; j < costs[0].length; j++){
                if(costs[i - 1][j] == min){
                    costs[i][j] += min2;
                }else{
                    costs[i][j] += min;
                }
            }


            if(i != costs.length - 1){
                min = Integer.MAX_VALUE;
                min2 = Integer.MAX_VALUE;
                for(int j = 0; j < costs[0].length; j++){
                    if(costs[i][j] < min){
                        min2 = min;
                        min = costs[i][j];
                    }else if(costs[i][j] >= min && costs[i][j] < min2){
                        min2 = costs[i][j];
                    }
                }
                // System.out.println(min + ", " + min2);
            }
            

        }

        for(int j = 0; j < costs[0].length; j++)
                ans = Math.min(ans, costs[costs.length - 1][j]);

        return ans;
    }
}
