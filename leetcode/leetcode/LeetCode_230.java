package leetcode;

public class LeetCode_230 {
    int now,ans;
    public int kthSmallest(TreeNode root, int k) {
        midcount(root,k);
        return ans;
    }
    public void midcount(TreeNode root,int k){
        if(root!=null) {
            midcount(root.left,k);
            now++;
            if(now==k){
                ans = root.val;
                return;
            }
            midcount(root.right,k);
        }
    }
}
