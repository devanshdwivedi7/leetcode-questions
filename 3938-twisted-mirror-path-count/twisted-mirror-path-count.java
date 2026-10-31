class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1000000007;

        int[][][] jump = new int[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                jump[i][j][0] = -1;
                jump[i][j][1] = -1;
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (j + 1 < n) {
                    if (grid[i][j + 1] == 0) {
                        jump[i][j][0] = i * n + (j + 1);
                    } else {
                        jump[i][j][0] = jump[i][j + 1][1];
                    }
                }
                if (i + 1 < m) {
                    if (grid[i + 1][j] == 0) {
                        jump[i][j][1] = (i + 1) * n + j;
                    } else {
                        jump[i][j][1] = jump[i + 1][j][0];
                    }
                }
            }
        }

        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) continue;

                int rightTarget = jump[i][j][0];
                if (rightTarget != -1) {
                    int r = rightTarget / n;
                    int c = rightTarget % n;
                    dp[r][c] = (dp[r][c] + dp[i][j]) % MOD;
                }

                int downTarget = jump[i][j][1];
                if (downTarget != -1) {
                    int r = downTarget / n;
                    int c = downTarget % n;
                    dp[r][c] = (dp[r][c] + dp[i][j]) % MOD;
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}