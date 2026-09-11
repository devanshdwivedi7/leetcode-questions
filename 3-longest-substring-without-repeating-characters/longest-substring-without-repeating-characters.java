class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] index = new int[128];
        int maxLength = 0;
        
        for (int left = 0, right = 0; right < s.length(); right++) {
            left = Math.max(index[s.charAt(right)], left);
            maxLength = Math.max(maxLength, right - left + 1);
            index[s.charAt(right)] = right + 1;
        }
        return maxLength;
    }
}