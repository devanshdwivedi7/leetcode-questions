class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][][] dp = new boolean[m][n][1024];

        dp[0][0][grid[0][0]] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int val = 0; val < 1024; val++) {
                    if (dp[i][j][val]) {
                        if (i + 1 < m) {
                            dp[i + 1][j][val ^ grid[i + 1][j]] = true;
                        }

                        if (j + 1 < n) {
                            dp[i][j + 1][val ^ grid[i][j + 1]] = true;
                        }
                    }
                }
            }
        }

        for (int val = 0; val < 1024; val++) {
            if (dp[m - 1][n - 1][val]) {
                return val;
            }
        }

        return -1;
    }
}