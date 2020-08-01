package leetcode;

public class LeetCode_337 {
    public int rob(TreeNode root) {
       int [] ints=dfs(root);
       return Math.max(ints[0],ints[1]);
    }

    private int[] dfs(TreeNode root) {

        if (root==null) return new int[]{0,0};
        int left[]=dfs(root.left);
        int right[]=dfs(root.right);
        int dp[]=new int[2];

        //当前要
        dp[0]=root.val+left[1]+right[1];
        dp[1]=Math.max(left[0],left[1])+Math.max(right[0],right[1]);//不要
        return dp;
    }
}
