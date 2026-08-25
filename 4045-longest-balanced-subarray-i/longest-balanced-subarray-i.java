class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int maxLength = 0;
        int[] seenAt = new int[100005]; 
        for (int i = 0; i < n; i++) {
            int distinctEven = 0;
            int distinctOdd = 0;
            for (int j = i; j < n; j++) {
                int val = nums[j];
                if (seenAt[val] != i + 1) { 
                    seenAt[val] = i + 1;
                    
                    if (val % 2 == 0) {
                        distinctEven++;
                    } else {
                        distinctOdd++;
                    }
                }
                if (distinctEven == distinctOdd) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        
        return maxLength;
    }
}