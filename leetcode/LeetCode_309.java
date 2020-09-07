package leetcode;


//最佳买卖股票时机含冷冻期
public class LeetCode_309 {
    public int maxProfit(int[] prices) {

        int dp[][]=new int[prices.length][3];
        dp[0][0]=-prices[0];
        dp[0][1]=dp[0][2]=0;
        //dp[i][0] 持股的最大收益
        //dp[i][1] 不持股不冷东的最大收益
        //dp[i][2] 不持股冷东的最大收益
        for (int i=1;i<prices.length;i++){
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]-prices[i]);
            dp[i][1]=Math.max(dp[i-1][2],dp[i-1][1]);
            dp[i][2]=dp[i-1][0]+prices[i];
        }
        return Math.max(dp[prices.length-1][2],dp[prices.length-1][1]);
    }

    public static void main(String[] args) {
        LeetCode_309 leetCode_309=new LeetCode_309();
        int i = leetCode_309.maxProfit(new int[]{1,2,3,0,2});
        System.out.println(i);
    }
}
