package leetcode;

import java.util.HashSet;
import java.util.Stack;

public class 二叉树的非递归遍历 {
    public static void main(String[] args) {
   TreeNode root=new TreeNode(1);

   root.left=new TreeNode(2);
   root.right=new TreeNode(3);
   root.left.left=new TreeNode(4);
   root.left.right=new TreeNode(5);
   root.right.left=new TreeNode(6);
   root.right.right=new TreeNode(7);
 //  midsort(root);
 // premid(root);
        lastmid(root);
    }
     private static void midsort(TreeNode root){
         Stack<TreeNode> stack=new Stack<>();
         //stack.push(root);
         TreeNode node=root;
         while (!stack.isEmpty()||node!=null){
           if(node!=null){//向左走，压入栈
               stack.push(node);
               node=node.left;
           }else {
               TreeNode temp=stack.pop();
               if(temp!=null) {
                   System.out.println(temp.val);
                   node = temp.right;
                  // stack.push(node);
               }
           }
         }
     }
    private static void lastmid(TreeNode root){
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        TreeNode node=null;
        TreeNode pre=null;
        while (!stack.isEmpty()){
           node=stack.peek();
            if((node.left==null&&node.right==null)||(pre!=null&&(node.left==pre||node.right==pre))){
                pre=stack.pop();
                System.out.println(pre.val);
            }else {
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
        }


    }
    private static void premid(TreeNode root){
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        TreeNode node=root;
        while (!stack.isEmpty()){
            node=stack.pop();
            if(node!=null){
                System.out.println(node.val);
                stack.push(node.right);
                stack.push(node.left);
            }

        }


    }

}
