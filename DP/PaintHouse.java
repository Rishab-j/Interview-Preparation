public class PaintHouse {
    public int minCost(int[][] cost) {
        // write your code here

        if(cost.length == 0) return 0;

        int ans = Integer.MAX_VALUE;

        for(int i = 1; i < cost.length; i++){
            for(int j = 0; j < cost[0].length; j++){
                if(j == 0){
                    cost[i][j] += Math.min(cost[i - 1][j + 1],cost[i - 1][j + 2]);
                }else if(j == 1){
                    cost[i][j] += Math.min(cost[i - 1][j + 1],cost[i - 1][j - 1]);
                }else{
                    cost[i][j] += Math.min(cost[i - 1][j - 1],cost[i - 1][j - 2]);
                }
            }
        }

        for(int i = 0; i < 3; i++){
            ans = Math.min(ans, cost[cost.length - 1][i]);
        }

        return ans;
    }    
}
