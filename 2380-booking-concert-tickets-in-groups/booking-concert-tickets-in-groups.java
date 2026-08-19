class BookMyShow {
    private int[] maxTree;
    private long[] sumTree;
    
    private int n;
    private int m;
    private int minRow; 
    
    public BookMyShow(int n, int m) {
        this.n = n;
        this.m = m;
        this.minRow = 0;
        this.maxTree = new int[4 * n];
        this.sumTree = new long[4 * n];
        build(0, 0, n - 1);
    }
    
    private void build(int node, int start, int end) {
        if (start == end) {
            maxTree[node] = m;
            sumTree[node] = m;
            return;
        }
        int mid = start + (end - start) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;
        
        build(leftChild, start, mid);
        build(rightChild, mid + 1, end);
        
        maxTree[node] = Math.max(maxTree[leftChild], maxTree[rightChild]);
        sumTree[node] = sumTree[leftChild] + sumTree[rightChild];
    }
    
    private void update(int node, int start, int end, int row, int seatsTaken) {
        if (start == end) {
            maxTree[node] -= seatsTaken;
            sumTree[node] -= seatsTaken;
            return;
        }
        int mid = start + (end - start) / 2;
        if (row <= mid) {
            update(2 * node + 1, start, mid, row, seatsTaken);
        } else {
            update(2 * node + 2, mid + 1, end, row, seatsTaken);
        }
        maxTree[node] = Math.max(maxTree[2 * node + 1], maxTree[2 * node + 2]);
        sumTree[node] = sumTree[2 * node + 1] + sumTree[2 * node + 2];
    }
    private int queryMax(int node, int start, int end, int maxRow) {
        if (start > maxRow) return 0;
        if (end <= maxRow) return maxTree[node];
        
        int mid = start + (end - start) / 2;
        return Math.max(
            queryMax(2 * node + 1, start, mid, maxRow),
            queryMax(2 * node + 2, mid + 1, end, maxRow)
        );
    }
    private int queryGather(int node, int start, int end, int maxRow, int k) {
        if (maxTree[node] < k || start > maxRow) return -1;
        if (start == end) return start;
        
        int mid = start + (end - start) / 2;
        int res = queryGather(2 * node + 1, start, mid, maxRow, k);
        if (res != -1) return res;
        
        return queryGather(2 * node + 2, mid + 1, end, maxRow, k);
    }
    private long querySum(int node, int start, int end, int maxRow) {
        if (start > maxRow) return 0;
        if (end <= maxRow) return sumTree[node];
        
        int mid = start + (end - start) / 2;
        return querySum(2 * node + 1, start, mid, maxRow) + 
               querySum(2 * node + 2, mid + 1, end, maxRow);
    }
    private long getSeats(int node, int start, int end, int row) {
        if (start == end) return sumTree[node];
        int mid = start + (end - start) / 2;
        if (row <= mid) return getSeats(2 * node + 1, start, mid, row);
        return getSeats(2 * node + 2, mid + 1, end, row);
    }

    public int[] gather(int k, int maxRow) {
        if (queryMax(0, 0, n - 1, maxRow) < k) {
            return new int[]{};
        }
        int r = queryGather(0, 0, n - 1, maxRow, k);
        if (r == -1) return new int[]{};
        int availableSeatsInR = (int) getSeats(0, 0, n - 1, r);
        int startCol = m - availableSeatsInR;
        update(0, 0, n - 1, r, k);
        
        return new int[]{r, startCol};
    }

    public boolean scatter(int k, int maxRow) {
        if (querySum(0, 0, n - 1, maxRow) < k) {
            return false;
        }
        while (k > 0 && minRow <= maxRow) {
            int available = (int) getSeats(0, 0, n - 1, minRow);
            
            if (available == 0) {
                minRow++; 
                continue;
            }
            
            if (available >= k) {
                update(0, 0, n - 1, minRow, k);
                k = 0;
            } else {
                update(0, 0, n - 1, minRow, available);
                k -= available;
                minRow++; 
            }
        }
        
        return true;
    }
}