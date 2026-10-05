class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
            return -1;
        }
        if (n == 1) {
            return 1;
        }
        int totalNodes = n * n;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < totalNodes; i++) {
            adj.add(new ArrayList<>());
        }

        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
        };

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 0) {
                    int u = r * n + c; 
                    for (int[] dir : directions) {
                        int nr = r + dir[0];
                        int nc = c + dir[1];

                        if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == 0) {
                            int v = nr * n + nc;
                            adj.get(u).add(v);
                        }
                    }
                }
            }
        }
        int src = 0;
        int target = totalNodes - 1;

        Queue<Integer> queue = new LinkedList<>();
        int[] dist = new int[totalNodes];
        Arrays.fill(dist, -1);

        queue.add(src);
        dist[src] = 1; 

        while (!queue.isEmpty()) {
            int u = queue.poll();

            if (u == target) {
                return dist[u];
            }

            for (int v : adj.get(u)) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    queue.add(v);
                }
            }
        }

        return -1;
    }
}