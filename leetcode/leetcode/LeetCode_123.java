package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_123 {
    public static int maxProfit(int[] prices) {
      if (prices.length==0||prices.length==1) return 0;
      int dp[][][]=new int[prices.length][3][2];
      for (int i=0;i<prices.length;i++){
          for (int k=2;k>0;k--){
              if (i==0){
                  dp[0][k][0]=0;
                  dp[0][k][1]=-prices[0];
              }else {
                  dp[i][k][0]=Math.max(dp[i-1][k][0],dp[i-1][k][1]+prices[i]);
                  dp[i][k][1]=Math.max(dp[i-1][k][1],dp[i-1][k-1][0]-prices[i]);
              }

          }
      }
      return  dp[prices.length-1][2][0];
    }

    public static void main(String[] args) {
        Map<Integer,String> map=new HashMap<>();
        map.put(null,null);
        map.put(null,"q");
        System.out.println(map.get(null));
        System.out.println(maxProfit(new int[]{ 3,3,5,0,0,3,1,4}));
    }
}
