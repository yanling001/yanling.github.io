package leetcode;


import java.util.HashMap;
import java.util.Map;

public class Tree {

//    int preindex=0;
//    Map<Integer,Integer> map=new HashMap();
//    int[] preorder;
//    int[] inorder;
//    public  TreeNode buildTree(int[] preorder, int[] inorder) {
//        this.inorder=inorder; this.preorder=preorder;
//        int id=0;
//        for (int t:inorder){
//            map.put(t,id++);
//        }
//        return helper(0,inorder.length);
//    }
//
//    private TreeNode helper(int left, int right) {
//         if(left==right)
//             return null;
//        TreeNode root=new TreeNode(preorder[preindex]);
//        int index=map.get(root.val);
//        preindex++;
//        root.left=helper(left,index);
//        root.right=helper(index+1,right);
//        return root;
//    }
//遍历二叉树
    public  static  void main(String args[]){
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(5);
        root.right.left=new TreeNode(6);
        root.right.right=new TreeNode(7);
        //遍历
        bianli(root);
    }

    private static void bianli(TreeNode root) {
        if (root==null)
            return;
        System.out.println(root.val);
        bianli(root.left);
        bianli(root.right);
    }


}



class TreeNode {
      int val;
    TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
