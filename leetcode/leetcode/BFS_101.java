package leetcode;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_101 {
    /*
    方法一：递归
    public boolean isSymmetric(TreeNode root) {
         return check(root.left,root.right);
    }

    private boolean check(TreeNode left, TreeNode right) {
        if (left==null&&right==null)
            return true;
        if (left==null||right==null)
            return false;
        if (left.val!=right.val)
            return false;
           else return check(left.left,left.right)&&check(right.left,right.right);
        }
    */
    //方法二 BFS
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode>queue=new LinkedList();
         queue.add(root);
         TreeNode node=queue.poll();
         queue.add(node.left); queue.add(node.right);
         while(!queue.isEmpty()){
           TreeNode node1=queue.poll();
           TreeNode node2=queue.poll();
           if (node1==null&&node2==null) continue;
           if (node1==null||node2==null) return false;
           if (node1.val!=node2.val)  return false;
           queue.add(node1.left);
           queue.add(node2.right);
           queue.add(node1.right);
           queue.add(node2.left);
         }
         return true;
    }
}
