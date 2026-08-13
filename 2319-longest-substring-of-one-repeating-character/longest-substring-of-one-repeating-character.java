class Solution {
    static class Node {
        int maxLen;
        int prefLen;
        int suffLen;
        char leftChar;
        char rightChar;

        Node() {}

        Node(char c) {
            this.maxLen = 1;
            this.prefLen = 1;
            this.suffLen = 1;
            this.leftChar = c;
            this.rightChar = c;
        }
    }
    private Node[] tree;
    private char[] chars;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        this.chars = s.toCharArray();
        this.tree = new Node[4 * n];

        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char c = queryCharacters.charAt(i);

            chars[idx] = c;
            update(1, 0, n - 1, idx, c);
            
            ans[i] = tree[1].maxLen;
        }

        return ans;
    }

    private Node merge(Node left, Node right, int leftLen, int rightLen) {
        Node res = new Node();
        res.leftChar = left.leftChar;
        res.rightChar = right.rightChar;

        res.maxLen = Math.max(left.maxLen, right.maxLen);
        res.prefLen = left.prefLen;
        if (left.prefLen == leftLen && left.rightChar == right.leftChar) {
            res.prefLen = left.prefLen + right.prefLen;
        }
        res.suffLen = right.suffLen;
        if (right.suffLen == rightLen && left.rightChar == right.leftChar) {
            res.suffLen = right.suffLen + left.suffLen;
        }

        if (left.rightChar == right.leftChar) {
            res.maxLen = Math.max(res.maxLen, left.suffLen + right.prefLen);
        }

        return res;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(chars[start]);
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);

        tree[node] = merge(tree[2 * node], tree[2 * node + 1], mid - start + 1, end - mid);
    }
    private void update(int node, int start, int end, int idx, char c) {
        if (start == end) {
            tree[node] = new Node(c);
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, c);
        } else {
            update(2 * node + 1, mid + 1, end, idx, c);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1], mid - start + 1, end - mid);
    }
}