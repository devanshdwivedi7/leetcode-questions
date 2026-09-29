class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int[] par = new int[n];
        for (int i = 0; i < n; i++) {
            par[i] = i;
        }

        int components = n;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    int root1 = find(par, i);
                    int root2 = find(par, j);
                    if (root1 != root2) {
                        par[root1] = root2;
                        components--;
                    }
                }
            }
        }
        return components;
    }
    public int find(int[] par, int i) {
        if (par[i] == i) {
            return i;
        }
        return par[i] = find(par, par[i]);
    }
}