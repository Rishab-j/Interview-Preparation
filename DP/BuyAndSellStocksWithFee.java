public class BuyAndSellStocksWithFee {
    
    // code 1
    public int maxProfit(int[] prices, int fee) {
        int bsp = -prices[0];
        int ssp = 0;
        
        for(int i = 0; i < prices.length; i++){
            int nbsp = 0;
            int nssp = 0;
            
            if(ssp - prices[i] > bsp){
                nbsp = ssp - prices[i];
            }else{
                nbsp = bsp;
            }
            
            if(prices[i] + bsp - fee > ssp){
                nssp = prices[i] + bsp - fee;
            }else{
                nssp = ssp;
            }
            
            bsp = nbsp;
            ssp = nssp;
        }
        
        return ssp;
    }

    // code 2
    public int maxProfit_2(int[] prices, int fee) {
        int cash = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            int prev_cash = cash;
            cash = Math.max(prev_cash, hold + prices[i] - fee);
            hold = Math.max(hold, prev_cash - prices[i]);
        }
        return cash;
    }

}
