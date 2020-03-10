package leetcode;

import java.util.*;

//路径总和2（树）
public class LeetCode_113 {
    private List<List<Integer>> listList = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null){
            return listList;
        }
        List<Integer> reslist = new ArrayList<>();
        int singleSum = 0;
        pathHelp(root, sum,reslist,singleSum);
        return listList;
    }
    public void pathHelp(TreeNode root, int sum,List<Integer> list,int singleSum) {
        //记录每条序列的总和
        singleSum+=root.val;

        //记录该条序列的序列值
        list.add(root.val);
        if (root.left!=null){
            //每次退出时删除已经遍历过的节点值
            pathHelp(root.left, sum,list,singleSum);
            list.remove(list.size()-1);
        }
        if (root.right!=null){
            //每次退出时删除已经遍历过的节点值
            pathHelp(root.right, sum,list,singleSum);
            list.remove(list.size()-1);
        }

        //子序列的判断条件，以及是否==sum
        if (root.left == null && root.right == null){
            if (sum == singleSum){
                listList.add(new ArrayList<>(list));
            }
            return;
        }
        return;
    }
}
