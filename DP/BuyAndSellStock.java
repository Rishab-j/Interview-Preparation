public class BuyAndSellStock {
    public int maxProfit(int[] prices) {
        int buy = Integer.MAX_VALUE;
        int sell = 0;
        
        for(int i = 0; i < prices.length; i++){
            int temp = buy;
            buy = Math.min(buy, prices[i]);
            sell = Math.max(sell, prices[i] - temp);
        }
        
        return sell;
    }
}
