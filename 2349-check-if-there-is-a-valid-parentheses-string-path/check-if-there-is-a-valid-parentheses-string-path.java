class Solution {
    private Boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        if ((m + n - 1) % 2 != 0) {
            return false;
        }
        
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        
        memo = new Boolean[m][n][(m + n) / 2 + 1];
        return dfs(grid, 0, 0, 0, m, n);
    }

    private boolean dfs(char[][] grid, int r, int c, int open, int m, int n) {
        if (grid[r][c] == '(') {
            open++;
        } else {
            open--;
        }

        if (open < 0 || open > (m + n) / 2) {
            return false;
        }

        if (r == m - 1 && c == n - 1) {
            return open == 0;
        }

        if (memo[r][c][open] != null) {
            return memo[r][c][open];
        }

        boolean res = false;
        if (r + 1 < m) {
            res = res || dfs(grid, r + 1, c, open, m, n);
        }
        if (c + 1 < n) {
            res = res || dfs(grid, r, c + 1, open, m, n);
        }
        return memo[r][c][open] = res;
    }
}