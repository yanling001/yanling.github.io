package leetcode;

import java.nio.ByteBuffer;

public class 平衡二叉树<psvm> {
    public boolean isBalanced(TreeNode root) {
        if (root==null)
            return true;
        int temp=height(root.right)-height(root.left);
        if (temp>1||temp<-1) return false;
            if (isBalanced(root.left)&&isBalanced(root.right)){
                return true;
            }else return false;
    }

    private int height(TreeNode root) {
        if (root==null)
            return 0;
        return Integer.max(height(root.right)+1,height(root.left)+1);
    }

}
