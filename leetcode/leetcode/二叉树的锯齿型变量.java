package leetcode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public  class 二叉树的锯齿型变量 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lists=new ArrayList<>();
        if(root==null)
            return lists;
        Queue<TreeNode> queue=new LinkedList();
        queue.add(root);
        TreeNode next,pre;
        next=root;
        pre=root;
        int i=1;
        List<Integer> list=new ArrayList<>();
        while (!queue.isEmpty()){
            TreeNode node=queue.poll();
            if (node!=null){
                if(getchild(node)!=null)
                    pre=getchild(node);
            }
            if (node!=null)
                list.add(node.val);
            if (node==next){
                if (i%2==1)
                    list=  fuanzhuan(list);
                lists.add(list);
                i++;
                list=new ArrayList<>();
                next=pre;
            }
            if (node!=null){
                queue.add(node.right);
                queue.add(node.left);
            }


        }
        return lists;
    }

    private List<Integer> fuanzhuan(List<Integer> list) {
        List<Integer> ans=new ArrayList<>();
        for (int i=list.size()-1;i>=0;i--){
            ans.add(list.get(i));
        }
        return ans;
    }

    private TreeNode getchild(TreeNode left) {
        if (left==null){
            return null;
        }else if (left.left!=null)
            return left.left;
        return left.right;
    }
}