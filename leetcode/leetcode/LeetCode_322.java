package leetcode;

public class LeetCode_322 {
    public int coinChange(int[] coins, int amount) {
        // 自底向上的动态规划
        if(coins.length == 0){
            return -1;
        }

        // memo[n]的值： 表示的凑成总金额为n所需的最少的硬币个数
        int[] memo = new int[amount+1];
        memo[0] = 0;
        for(int i = 1; i <= amount;i++){
            int min = Integer.MAX_VALUE;
            for(int j = 0;j < coins.length;j++){
                if(i - coins[j] >= 0 && memo[i-coins[j]] < min){
                    min = memo[i-coins[j]] + 1;
                }
            }
            // memo[i] = (min == Integer.MAX_VALUE ? Integer.MAX_VALUE : min);
            memo[i] = min;
        }

        return memo[amount] == Integer.MAX_VALUE ? -1 : memo[amount];
    }
}
