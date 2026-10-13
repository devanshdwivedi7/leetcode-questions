class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] absNums = new long[n];
        for (int i = 0; i < n; i++) {
            absNums[i] = Math.abs((long) nums[i]);
        }
        Arrays.sort(absNums);
        long score = 0;
        int left = 0, right = n - 1;
        boolean positive = true;
        while (left <= right) {
            if (positive) {
                score += absNums[right] * absNums[right];
                right--;
            } else {
                score -= absNums[left] * absNums[left];
                left++;
            }
            positive = !positive;
        }
        return score;
    }
}