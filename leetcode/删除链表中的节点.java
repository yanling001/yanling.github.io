package leetcode;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.ArrayList;
import java.util.LinkedList;

public class 删除链表中的节点 {
    public int maxDepth(TreeNode root) {
        int a [][]=new int[2][3];
        LinkedList linkedList=new LinkedList();

        ArrayList list = new ArrayList();
        new ArrayList<>(list).add(1);
        return (root==null) ?  0 :   Math.max(maxDepth(root.left)+1,maxDepth(root.right)+1);
    }
}
