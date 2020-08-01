package leetcode;

public class 二叉树的最大路径和 {
    int maxsum=Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxgain(root);
        return maxsum;
    }

    private int maxgain(TreeNode root) {
        if (root==null) return 0;
        //如果比零小就取零
        int left_max=Math.max(maxgain(root.left),0);
        int right_max=Math.max(maxgain(root.right),0);
        int now_value=root.val+left_max+right_max;
        maxsum=Math.max(maxsum,now_value);

        //每次只能加左右其中最大的那一支（保证是路径）
        return now_value+Math.max(right_max,left_max);
    }
}
