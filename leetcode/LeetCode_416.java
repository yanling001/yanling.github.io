package leetcode;
//给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
public class LeetCode_416 {
    public boolean canPartition(int[] nums) {
        int len=nums.length;
        if (len<=1) return false;
        int sum=0;
        for (int i:nums){
            sum+=i;
        }
        if (sum%2==1) return false;
        int target=sum/2;
        boolean dp[][]=new boolean[len][target+1];
        dp[0][0]=true;
        for (int i=1;i<len;i++){
            for(int j=0;j<target+1;j++){
                dp[i][j]=dp[i-1][j];
                if (j-nums[i]==0) dp[i][j]=true;
                //能要就是要或者不要
                if (j-nums[i]>0) dp[i][j]=  dp[i - 1][j] || dp[i - 1][j - nums[i]];
            }

        }
        return dp[len-1][target];
    }
}
