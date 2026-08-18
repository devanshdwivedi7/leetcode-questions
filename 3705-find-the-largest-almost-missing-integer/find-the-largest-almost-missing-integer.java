class Solution {
    public int largestInteger(int[] nums, int k) {
        int[] subArrayCount = new int[51];
        for (int i = 0; i <= nums.length - k; i++) {
            boolean[] seenInCurrentSubarray = new boolean[51];
            for (int j = i; j < i + k; j++) {
                seenInCurrentSubarray[nums[j]] = true;
            }
            for (int val = 0; val <= 50; val++) {
                if (seenInCurrentSubarray[val]) {
                    subArrayCount[val]++;
                }
            }
        }
        int largestAlmostMissing = -1;
        for (int val = 0; val <= 50; val++) {
            if (subArrayCount[val] == 1) {
                largestAlmostMissing = Math.max(largestAlmostMissing, val);
            }
        }
        
        return largestAlmostMissing;
    }
}