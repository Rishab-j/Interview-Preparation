public class BuyAndSellStock2 {
    public int maxProfit(int[] prices) {
        int sellDay = 0;
        int buyDay = 0;
        int profit = 0;
        
        for(int i = 1; i < prices.length; i++){
            if(prices[i] >= prices[i - 1]){
                sellDay++;
            }else{
                profit += prices[sellDay] - prices[buyDay];
                sellDay = i;
                buyDay = i;
            }
        }
        
        profit += prices[sellDay] - prices[buyDay];
        return profit;
    }
}
