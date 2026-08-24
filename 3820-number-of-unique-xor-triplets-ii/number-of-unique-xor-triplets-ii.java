class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        int MAX_XOR = 2048;
        boolean[] uniqueTriplets = new boolean[MAX_XOR];
        boolean[] pairXors = new boolean[MAX_XOR];
        for (int i = n - 1; i >= 0; i--) {

            for (int k = i; k < n; k++) {
                pairXors[nums[i] ^ nums[k]] = true;
            }
            for (int v = 0; v < MAX_XOR; v++) {
                if (pairXors[v]) {
                    uniqueTriplets[nums[i] ^ v] = true;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < MAX_XOR; i++) {
            if (uniqueTriplets[i]) {
                count++;
            }
        }       
        return count;
    }
}