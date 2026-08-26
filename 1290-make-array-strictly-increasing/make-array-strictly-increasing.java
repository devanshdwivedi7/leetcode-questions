import java.util.Arrays;

class Solution {
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {

        Arrays.sort(arr2);
        
        int n = arr1.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = -1;
        
        for (int i = 0; i < n; i++) {
            int[] nextDp = new int[n + 1];
            Arrays.fill(nextDp, Integer.MAX_VALUE);
            for (int j = 0; j <= i + 1; j++) {
                if (dp[j] == Integer.MAX_VALUE) continue;
                if (arr1[i] > dp[j]) {
                    nextDp[j] = Math.min(nextDp[j], arr1[i]);
                }
                int idx = upperBound(arr2, dp[j]);
                if (idx < arr2.length) {
                    nextDp[j + 1] = Math.min(nextDp[j + 1], arr2[idx]);
                }
            }
            dp = nextDp;
        }
        for (int j = 0; j <= n; j++) {
            if (dp[j] != Integer.MAX_VALUE) {
                return j;
            }
        }
        return -1;
    }
    private int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}