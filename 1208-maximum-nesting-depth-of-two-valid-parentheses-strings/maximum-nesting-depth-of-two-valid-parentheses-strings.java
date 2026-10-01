class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = (seq.charAt(i) == '(') ? (i % 2) : ((i + 1) % 2);
        }
        return res;
    }
}