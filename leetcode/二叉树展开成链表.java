package leetcode;

public class 二叉树展开成链表 {
    public void flatten(TreeNode root) {
        if (root==null) return;
        flatten(root.left);
        flatten(root.right);
        TreeNode left=root.left;
        TreeNode right=root.right;
        root.left=null;
        root.right=left;
        while (root.right!=null){
            root=root.right;
        }
        root.right=right;
        return;
    }
}
