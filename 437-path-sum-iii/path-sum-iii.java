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
    int totalPaths=0;
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefixSumMap=new HashMap<>();
        prefixSumMap.put(0L,1);
        dfs(root,0L,targetSum,prefixSumMap);
        return totalPaths;
        
    }
    public void dfs(TreeNode node, long currentSum,int targetSum,Map<Long,Integer> prefixSumMap){
        if(node==null)
        return;
         currentSum+=node.val;
         if(prefixSumMap.containsKey(currentSum-targetSum)){
            totalPaths +=prefixSumMap.get(currentSum-targetSum);
            
         }
         prefixSumMap.put(currentSum,prefixSumMap.getOrDefault(currentSum,0)+1);
         dfs(node.left,currentSum,targetSum,prefixSumMap);
         dfs(node.right,currentSum,targetSum,prefixSumMap);
         prefixSumMap.put(currentSum,prefixSumMap.get(currentSum)-1);
    }
}  