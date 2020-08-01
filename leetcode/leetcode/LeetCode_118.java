package leetcode;

public class LeetCode_118 {
    public static int maxProfit(int k, int[] prices) {
        if(prices.length==0||prices.length==1) return 0;
        //动态规划
        if(k> prices.length/2)// 与交易无数次等价
        {
            int max=0;
            for(int i=1;i<prices.length;i++){
                if(prices[i]>prices[i-1]){
                    max+=prices[i]-prices[i-1];
                }
            }
            return max;
        }
        int max_k=k;
        int dp[][][]=new int[prices.length][max_k+1][2];


        for (int i=0;i<prices.length;i++){
            for (int j=max_k;j>0;j--){
                if (i==0){
                    dp[0][j][0]=0;
                    dp[0][j][1]=-prices[i];
                }else {
                    dp[i][j][0]=Math.max(dp[i-1][j][0],dp[i-1][j][1]+prices[i]);
                    dp[i][j][1]=Math.max(dp[i-1][j][1],dp[i-1][j-1][0]-prices[i]);
                }

            }

        }
        return dp[prices.length-1][max_k][0];

    }

    public static void main(String[] args) {
        System.out.println(maxProfit(2,new int[]{3,2,6,5,0,3}));
    }
}
