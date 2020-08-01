package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//层序遍历
public class LeetCode_199 {
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        TreeNode now,next;
        List<Integer> ans=new ArrayList<>();
        if(root==null) return ans;
        now=next=root;
        while (!queue.isEmpty()){
            TreeNode node=queue.poll();
            if (node.left!=null) {
                queue.add(node.left);
                next=node.left;
            }
            if (node.right!=null){
                queue.add(node.right);
                next=node.right;
            }

            if (node==now){
                now=next;
                ans.add(node.val);
            }
        }
        return ans;
    }
}
