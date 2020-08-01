package leetcode;

public class LeetCode_740 {
    public static int deleteAndEarn(int[] nums) {
        if (nums.length==0) return 0;
        if (nums.length==1) return nums[0];
        int max=0;
      for (int i=0;i<nums.length;i++){
          max=Math.max(max,nums[i]);
      }
      int[] all=new int[max+1];
      for (int i=0;i<nums.length;i++){
          all[nums[i]]++;
      }
      int dp[]=new int[max+2];
      dp[0]=0;
      dp[1]=all[0];
      for (int i=1;i<all.length;i++){
          dp[i+1]=Math.max(dp[i],dp[i-1]+all[i]*i);
      }
      return dp[max+1];
    }

    public static void main(String[] args) {
        System.out.println(deleteAndEarn(new int[]{ 2, 2, 3, 3, 3, 4}));
    }
}
