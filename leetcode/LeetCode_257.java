package leetcode;

import java.util.ArrayList;
import java.util.List;

// 二叉树的所有路径
public class LeetCode_257 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        dfs(root, ans, new StringBuilder());
        return ans;
    }

    private void dfs(TreeNode root, List<String> ans, StringBuilder stringBuilder) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
            stringBuilder1.append(root.val);
            ans.add(stringBuilder1.toString());
            return;
        }
        StringBuilder stringBuilders = new StringBuilder(stringBuilder);
        stringBuilders.append(root.val + "->");
        dfs(root.left, ans, stringBuilders);
        dfs(root.right, ans, stringBuilders);
    }
}