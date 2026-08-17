class Solution {
    private int[][] memo;
    private int[] prefixSum;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        memo = new int[n][n];
        prefixSum = new int[n + 1];
        
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + stoneValue[i];
        }
        
        return dfs(stoneValue, 0, n - 1);
    }

    private int dfs(int[] stoneValue, int left, int right) {
        if (left == right) {
            return 0;
        }
        if (memo[left][right] != 0) {
            return memo[left][right];
        }
        int maxScore = 0;
        for (int i = left; i < right; i++) {
            int leftSum = prefixSum[i + 1] - prefixSum[left];
            int rightSum = prefixSum[right + 1] - prefixSum[i + 1];
            
            if (leftSum < rightSum) {
                maxScore = Math.max(maxScore, leftSum + dfs(stoneValue, left, i));
            } else if (leftSum > rightSum) {
                maxScore = Math.max(maxScore, rightSum + dfs(stoneValue, i + 1, right));
            } else {
                maxScore = Math.max(maxScore, Math.max(
                    leftSum + dfs(stoneValue, left, i), 
                    rightSum + dfs(stoneValue, i + 1, right)
                ));
            }
        }
        memo[left][right] = maxScore;
        return maxScore;
    }
}