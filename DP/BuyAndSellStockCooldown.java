public class BuyAndSellStockCooldown {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 1) {
            return 0;
        }
        
        int length = prices.length;
        // buy[i]: max profit if the first "i" days end with a "buy" day
        int[] buy = new int[length + 1];
        // buy[i]: max profit if the first "i" days end with a "sell" day
        int[] sell = new int[length + 1];
        
        buy[1] = -prices[0];
        
        for (int i = 2; i <= length; i++) {
            int price = prices[i - 1];
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - price);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + price);
        }
        
        // sell[length] >= buy[length]
        return sell[length];
    }

    //O(1)
    public int maxProfit_2(int[] prices) {
        int buy = -prices[0];
        int sell = 0;
        int cooldown = 0;
        
        for(int i = 1; i < prices.length; i++){
            int lastBuy = buy;
            int lastSell = sell;
            
            buy = Math.max(buy, cooldown - prices[i]);
            sell = Math.max(sell, lastBuy + prices[i]);
            
            cooldown = lastSell;
        }
        
        return sell;
    }
}
