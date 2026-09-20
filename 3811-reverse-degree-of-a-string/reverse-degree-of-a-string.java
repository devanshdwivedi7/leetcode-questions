class Solution {
    public int reverseDegree(String s) {
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int reversedAlphabetPos = 26 - (c - 'a');
            int stringPos = i + 1;
            total += reversedAlphabetPos * stringPos;
        }
        return total;
    }
}