class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCounts = new int[30];
        for (int num : nums) {
            for (int i = 0; i < 30; i++) {
                if ((num & (1 << i)) != 0) {
                    bitCounts[i]++;
                }
            }
        }

        long ans = 0;
        long mod = 1000000007;

        for (int step = 0; step < k; step++) {
            long currentVal = 0;
            for (int i = 0; i < 30; i++) {
                if (bitCounts[i] > 0) {
                    currentVal |= (1L << i);
                    bitCounts[i]--;
                }
            }
            ans = (ans + (currentVal * currentVal) % mod) % mod;
        }

        return (int) ans;
    }
}