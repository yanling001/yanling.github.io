package leetcode;

public class 相同树 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p==null||q==null){
            if (p==null||q==null)
            return true;
            else return false;
        }
           if (isSameTree(p.left,q.left)&&isSameTree(p.right,q.right))
               return true;
           else return false;
    }
}
