package leetcode;
//翻转二叉树
public class LeetCode_226 {
    public TreeNode invertTree(TreeNode root) {
       if(root==null||root.left==null && root.right==null){
           return root;
       }
       TreeNode temp=root.left;
       root.left=root.right;
       root.right=temp;
       invertTree(root.left);
       invertTree(root.right);

       return root;
    }
}
