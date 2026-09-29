class Solution {
    public int makeConnected(int n, int[][] connections) {
        if(connections.length < n-1){
            return -1;
        }
        int[] par = new int[n];
        for(int i=0;i<n;i++){
            par[i]=i;
        }
        int components = n;

        for (int[] conn : connections) {
            int root1 = find(par, conn[0]);
            int root2 = find(par, conn[1]);

            if (root1 != root2) {
                par[root1] = root2;
                components--;
            }
        }

        return components - 1;
    }

    public int find(int[] par, int i) {
        if (par[i] == i) {
            return i;
        }
        return par[i] = find(par, par[i]);
    }
}