package leetcode;


//上下翻转二叉树
public class LeetCode_156 {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) {
            return root;
        }
        TreeNode leftRoot = upsideDownBinaryTree(root.left);
        TreeNode tmpNode = leftRoot;
//        while(tmpNode.right != null) {
//            tmpNode = tmpNode.right;
//        }
        tmpNode.left = root.right;
        tmpNode.right = root;
        root.right = null;
        root.left = null;
        return leftRoot;
    }
}
/*

 struct TreeNode * DFS(struct TreeNode* root)

        {

        if(!root||(!root->left&&!root->right))return root;

        //root->left,为此层根节点，为左子树的叶节点

        struct TreeNode* tmp=root->left;

        struct TreeNode* ret=DFS(tmp);

        tmp->left=root->right;

        tmp->right=root;

        root->right=NULL;

        root->left=NULL;

        return ret;

        }



        struct TreeNode* upsideDownBinaryTree(struct TreeNode* root){

        return DFS(root);

        }

*/
