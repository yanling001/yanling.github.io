package leetcode;

import java.util.*;

public class 不同的二叉搜索树 {
    public List<TreeNode> generateTrees(int n) {
       if (n==0) {

           return new ArrayList<TreeNode>();


       }
       return buildtree(1,n);
}

    private List<TreeNode> buildtree(int start, int end) {
        List<TreeNode> ans=new ArrayList<>();
        if (start>end){
            ans.add(null);
          return ans;
        }
        for (int i=start;i<=end;i++){
            List<TreeNode> left=buildtree(start,i);
            List<TreeNode> right=buildtree(i,end);
            for (TreeNode l:left)
                for (TreeNode r:right){
                    TreeNode node=new TreeNode(i);
                    node.left=l;
                    node.right=r;
                    ans.add(node);
                }
        }
        return ans;
    }
    }
