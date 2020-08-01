package leetcode;

//还原二叉树从中序和后序
public class LeetCode_106 {
   public  static  TreeNode anstest(int in[],int last[]){
       int inlen=in.length; int lasrlen=last.length;
       return dfs(in,0,inlen-1,last,0,lasrlen-1);
   }

    private static TreeNode dfs(int[] in, int inl, int inr, int[] last, int lastl, int lastr) {
       //dfs出口
        if(inl>inr||lastl>lastr) return null;
       TreeNode root=new TreeNode(last[lastr]);
       int index=inl;
       while(in[index]!=last[lastr]){
           index++;
       }
       root.left=dfs(in,inl,index-1,last,lastl,lastr-inr+index-1);
       root.right=dfs(in,index+1,inr,last,lastr-inr+index,lastr-1);
       return root;
    }
}


/*
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Solution {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int inLen = inorder.length;
        int postLen = postorder.length;
        // 特判
        if (inLen != postLen) {
            throw new RuntimeException("输入错误");
        }
        return buildTree(inorder, 0, inLen - 1, postorder, 0, postLen - 1);
    }


private TreeNode buildTree(int[] inorder, int inLeft, int inRight,
                           int[] postorder, int postLeft, int postRight) {
    if (inLeft > inRight || postLeft > postRight) {
        return null;
    }

    int pivot = postorder[postRight];
    int pivotIndex = inLeft;

    // 注意这里如果编写不当，有数组下标越界的风险
    while (inorder[pivotIndex] != pivot) {
        pivotIndex++;
    }
    TreeNode root = new TreeNode(pivot);
    root.left = buildTree(inorder, inLeft, pivotIndex - 1,
            postorder, postLeft, postRight - inRight + pivotIndex - 1);

    root.right = buildTree(inorder, pivotIndex + 1, inRight,
            postorder, postRight - inRight + pivotIndex, postRight - 1);
    return root;
}
}

 */