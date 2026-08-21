class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        long[] lcms = new long[1 << n];
        int[] setBits = new int[1 << n];
        for (int i = 1; i < (1 << n); i++) {
            long currentLcm = 1;
            int bits = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    currentLcm = lcm(currentLcm, coins[j]);
                    bits++;
                }
            }
            lcms[i] = currentLcm;
            setBits[i] = bits;
        }
        
        long left = 1;
        long minCoin = coins[0];
        for (int c : coins) {
            minCoin = Math.min(minCoin, c);
        }
        long right = (long) k * minCoin; 
        long ans = right;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            
            if (countValidAmounts(mid, lcms, setBits, n) >= k) {
                ans = mid;
                right = mid - 1; 
            } else {
                left = mid + 1; 
            }
        }
        
        return ans;
    }
    private long countValidAmounts(long mid, long[] lcms, int[] setBits, int n) {
        long count = 0;
        for (int i = 1; i < (1 << n); i++) {
            if (setBits[i] % 2 == 1) {
                count += mid / lcms[i]; 
            } else {
                count -= mid / lcms[i]; 
            }
        }
        return count;
    }
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}