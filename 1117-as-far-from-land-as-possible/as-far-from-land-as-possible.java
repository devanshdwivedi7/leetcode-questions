import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        int landCount = 0;
        int waterCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    landCount++;
                    visited[i][j] = true;
                    queue.offer(new int[]{i, j});
                } else {
                    waterCount++;
                }
            }
        }
        if (landCount == 0 || waterCount == 0) {
            return -1;
        }
        int distance = -1;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }
        return distance;
    }
}