class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int maxDiff = 0;
        int[] count = new int[100001];
        long totalK = (long) k1 + k2;

        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            count[diff]++;
            if (diff > maxDiff) {
                maxDiff = diff;
            }
        }

        for (int i = maxDiff; i > 0 && totalK > 0; i--) {
            if (count[i] == 0) continue;
            long reduce = Math.min((long) count[i], totalK);
            count[i] -= reduce;
            count[i - 1] += (int) reduce;
            totalK -= reduce;
        }

        long result = 0;
        for (int i = 1; i <= maxDiff; i++) {
            if (count[i] > 0) {
                result += (long) count[i] * i * i;
            }
        }

        return result;
    }
}