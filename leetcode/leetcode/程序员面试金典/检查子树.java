package leetcode.程序员面试金典;



public class 检查子树 {
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t1==null&&t2==null) return true;
        if (t1==null) return false;
           if (t1.val==t2.val){
               return checkSubTree(t1.left,t2.left)&&checkSubTree(t1.right,t1.right);
           }else  return checkSubTree(t1.left,t2.left)||checkSubTree(t1.right,t1.right);
    }
}
