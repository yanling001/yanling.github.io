package leetcode;

import java.util.Stack;
// 二叉搜索树的迭代器
public class LeetCode_173 {
    static  Stack<TreeNode> stack=new Stack<>();
    public LeetCode_173(TreeNode root) {
          make(root);
    }

    private void make(TreeNode root) {
      while (root!=null){
          stack.push(root);
          root=root.left;
      }
    }


    /** @return the next smallest number */
    public int next() {
     TreeNode temp=stack.pop();
     if (temp.right!=null){
         make(temp.right);
     }
     return  temp.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
      return !stack.isEmpty();
    }
}
