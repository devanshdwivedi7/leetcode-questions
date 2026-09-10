/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode Lnode = lowestCommonAncestor(root.left, p, q);
        TreeNode Rnode = lowestCommonAncestor(root.right, p, q);
        if (Lnode != null && Rnode != null) {
            return root;
        }
        return Lnode != null ? Lnode : Rnode;
    }
}