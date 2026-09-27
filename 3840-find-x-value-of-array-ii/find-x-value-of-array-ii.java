class Solution {
    static class Node {
        int[] cnt = new int[5];
        int prod = 1;
    }

    private int[] nums;
    private int k;
    private Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.nums = nums;
        this.k = k;
        int n = nums.length;
        tree = new Node[4 * n];
        
        build(1, 0, n - 1);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, idx, val);
            
            Node res = query(1, 0, n - 1, start, n - 1);
            ans[i] = res.cnt[x];
        }

        return ans;
    }

    private void build(int node, int l, int r) {
        tree[node] = new Node();
        if (l == r) {
            int rem = (int) (nums[l] % k);
            tree[node].cnt[rem] = 1;
            tree[node].prod = rem;
            return;
        }
        int mid = (l + r) / 2;
        build(2 * node, l, mid);
        build(2 * node + 1, mid + 1, r);
        merge(tree[node], tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            nums[idx] = val;
            int rem = (int) (val % k);
            for (int i = 0; i < k; i++) {
                tree[node].cnt[i] = 0;
            }
            tree[node].cnt[rem] = 1;
            tree[node].prod = rem;
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) {
            update(2 * node, l, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, r, idx, val);
        }
        merge(tree[node], tree[2 * node], tree[2 * node + 1]);
    }

    private Node query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[node];
        }
        int mid = (l + r) / 2;
        if (qr <= mid) {
            return query(2 * node, l, mid, ql, qr);
        }
        if (ql > mid) {
            return query(2 * node + 1, mid + 1, r, ql, qr);
        }
        Node left = query(2 * node, l, mid, ql, qr);
        Node right = query(2 * node + 1, mid + 1, r, ql, qr);
        Node res = new Node();
        merge(res, left, right);
        return res;
    }

    private void merge(Node parent, Node left, Node right) {
        for (int i = 0; i < k; i++) {
            parent.cnt[i] = left.cnt[i];
        }
        int p = left.prod;
        for (int i = 0; i < k; i++) {
            int newRem = (p * i) % k;
            parent.cnt[newRem] += right.cnt[i];
        }
        parent.prod = (left.prod * right.prod) % k;
    }
}