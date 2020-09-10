package leetcode;

public class LeetCode_617 {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

     return dfs(t1,t2);

    }

    private TreeNode dfs(TreeNode t1, TreeNode t2) {
        if (t1==null&&t2==null) return null;
        TreeNode treeNode=new TreeNode(0);
        if (t1!=null&&t2!=null){
            treeNode.val=t1.val+t2.val;
            treeNode.left=dfs(t1.left,t2.left);
            treeNode.right=dfs(t1.left,t2.left);
        }
        else if (t1==null){
            treeNode.val=t2.val;
            treeNode.left=dfs(null,t2.left);
            treeNode.right=dfs(null,t2.right);
        }
        else {
            treeNode.val=t1.val;
            treeNode.left=dfs(t1.left,null);
            treeNode.right=dfs(t1.right,null);
        }
      return treeNode;
    }
}
