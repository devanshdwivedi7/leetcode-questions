
class Solution {
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[][][] dirs = {
            {},
            {{0, -1}, {0, 1}},
            {{-1, 0}, {1, 0}},
            {{0, -1}, {1, 0}},
            {{0, 1}, {1, 0}},
            {{0, -1}, {-1, 0}},
            {{0, 1}, {-1, 0}}
        };

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            if (r == m - 1 && c == n - 1) {
                return true;
            }

            int streetType = grid[r][c];

            for (int[] dir : dirs[streetType]) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
                    int nextStreetType = grid[nr][nc];
                    for (int[] backDir : dirs[nextStreetType]) {
                        if (nr + backDir[0] == r && nc + backDir[1] == c) {
                            visited[nr][nc] = true;
                            queue.offer(new int[]{nr, nc});
                            break;
                        }
                    }
                }
            }
        }

        return false;
    }
}