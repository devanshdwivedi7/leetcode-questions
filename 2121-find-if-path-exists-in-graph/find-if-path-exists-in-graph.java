class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        if (source == destination) {
            return true;
        }
        int[]parent= new int[n];
        for(int i=0;i<n;i++){
            parent[i]=i;
        }
        for(int[]edge:edges){
            union(edge[0],edge[1],parent);
        }
        return func(source,parent)==func(destination ,parent);
    }

    public int func(int i, int[] parent) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = func(parent[i], parent);
    }
    

    public void union(int i, int j, int[] parent) {
        int rootI = func(i, parent);
        int rootJ = func(j, parent);
        if (rootI != rootJ) {
            parent[rootI] = rootJ;
        }
    }
}