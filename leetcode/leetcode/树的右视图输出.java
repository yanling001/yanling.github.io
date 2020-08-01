package leetcode;

import java.util.*;

public class 树的右视图输出 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        Map<Integer,Integer> map=new HashMap<>();
        Stack<TreeNode> nodes=new Stack<>();
        Stack<Integer> depths=new Stack<>();
        nodes.push(root);
        depths.push(0);
        int maxdepth=-1;//记录树的深度
        while (!nodes.isEmpty()){
            TreeNode node=nodes.pop();
            int depth=depths.pop();
            maxdepth=Math.max(depth,maxdepth);
            if (!map.containsKey(depth)){
                map.put(depth,node.val);
            }

            nodes.push(node.left);
            nodes.push(node.right);
            depths.push(depth+1);
            depths.push(depth+1);

        }
        for (int i=0;i<=maxdepth;i++){
            list.add(map.get(i));
        }
       return list;
    }
}
