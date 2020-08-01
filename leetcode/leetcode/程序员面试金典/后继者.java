package leetcode.程序员面试金典;

import java.util.Stack;

public class 后继者 {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        boolean outFlag = false;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (outFlag) {
                    return cur;
                }
                if (cur == p) {
                    outFlag = true;
                }
                cur = cur.right;
            }
        }
        return null;
    }
}
