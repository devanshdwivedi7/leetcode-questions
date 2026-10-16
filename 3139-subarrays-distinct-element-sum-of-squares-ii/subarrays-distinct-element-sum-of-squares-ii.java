class Solution {
    private static final int MOD = 1000000007;

    private long[] tree;
    private long[] sumTree;
    private long[] lazy;

    private void push(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = (l + r) / 2;
            long val = lazy[node];

            lazy[2 * node] = (lazy[2 * node] + val) % MOD;
            sumTree[2 * node] = (sumTree[2 * node] + val * (mid - l + 1)) % MOD;
            tree[2 * node] = (tree[2 * node] + 2 * val * sumTree[2 * node] - val * val * (mid - l + 1)) % MOD;
            tree[2 * node] = (tree[2 * node] % MOD + MOD) % MOD;

            lazy[2 * node + 1] = (lazy[2 * node + 1] + val) % MOD;
            sumTree[2 * node + 1] = (sumTree[2 * node + 1] + val * (r - mid)) % MOD;
            tree[2 * node + 1] = (tree[2 * node + 1] + 2 * val * sumTree[2 * node + 1] - val * val * (r - mid)) % MOD;
            tree[2 * node + 1] = (tree[2 * node + 1] % MOD + MOD) % MOD;

            lazy[node] = 0;
        }
    }

    private void update(int node, int l, int r, int ql, int qr, long val) {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            tree[node] = (tree[node] + 2 * val * sumTree[node] + val * val * (r - l + 1)) % MOD;
            sumTree[node] = (sumTree[node] + val * (r - l + 1)) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
            return;
        }
        push(node, l, r);
        int mid = (l + r) / 2;
        update(2 * node, l, mid, ql, qr, val);
        update(2 * node + 1, mid + 1, r, ql, qr, val);
        tree[node] = (tree[2 * node] + tree[2 * node + 1]) % MOD;
        sumTree[node] = (sumTree[2 * node] + sumTree[2 * node + 1]) % MOD;
    }

    private long query(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) return tree[node];
        push(node, l, r);
        int mid = (l + r) / 2;
        long leftVal = query(2 * node, l, mid, ql, qr);
        long rightVal = query(2 * node + 1, mid + 1, r, ql, qr);
        return (leftVal + rightVal) % MOD;
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        tree = new long[4 * n];
        sumTree = new long[4 * n];
        lazy = new long[4 * n];

        Map<Integer, Integer> last = new HashMap<>();
        long totalSum = 0;

        for (int i = 0; i < n; i++) {
            int prevIdx = last.getOrDefault(nums[i], -1);
            int l = prevIdx + 1;
            int r = i;

            update(1, 0, n - 1, l, r, 1);
            long currentTotal = query(1, 0, n - 1, 0, i);
            totalSum = (totalSum + currentTotal) % MOD;

            last.put(nums[i], i);
        }

        return (int) totalSum;
    }
}