/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
     Integer prev = null;
     int count = 0;
     int max = 0;
     List<Integer> modes = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        inorder(root);
        int[] result = new int[modes.size()];
        for (int i = 0; i < modes.size(); i++) {
            result[i] = modes.get(i);
        }
        return result;
    }

     void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        if (prev != null && prev == node.val) {
            count++;
        } else {
            count = 1;
        }
        if (count > max) {
            max = count;
            modes.clear();
            modes.add(node.val);
        } else if (count == max) {
            modes.add(node.val);
        }

        prev = node.val;
        inorder(node.right);

    }
}