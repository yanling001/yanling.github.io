package leetcode;


//将有序数组转换成为二叉搜索树
public class LeetCode_108 {
    public TreeNode sortedArrayToBST(int[] nums) {
         return  TobeTree(nums,0,nums.length-1);
    }

    private TreeNode TobeTree(int[] nums, int i, int i1) {
        if(i1<i) return null;
        int mid=(i+i1)/2;
        TreeNode treeNode=new TreeNode(nums[mid]);
        treeNode.left=TobeTree(nums,i,mid-1);
        treeNode.right=TobeTree(nums,mid+1,i1);
        return  treeNode;
    }
}
