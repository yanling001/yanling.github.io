package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 二叉树的中序遍历栈 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        Stack<TreeNode> stack=new Stack<TreeNode>();
        TreeNode temp=root;
        while (temp!=null||!stack.isEmpty()){
          while (temp!=null){
              stack.push(temp.left);
              temp=temp.left;
              continue;
          }
          temp=stack.pop();
         list.add(temp.val);
         temp=stack.pop();
        }
        return list;
    }
}
