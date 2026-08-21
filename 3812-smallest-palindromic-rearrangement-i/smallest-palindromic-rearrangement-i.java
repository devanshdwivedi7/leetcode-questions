class Solution {
    public String smallestPalindrome(String s) {
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }  
        StringBuilder firstHalf = new StringBuilder();
        String middle = "";
        for (int i = 0; i < 26; i++) {
            char c = (char) (i + 'a');
            if (counts[i] % 2 != 0) {
                middle = String.valueOf(c);
            }
            int halfCount = counts[i] / 2;
            for (int j = 0; j < halfCount; j++) {
                firstHalf.append(c);
            }
        }
        StringBuilder secondHalf = new StringBuilder(firstHalf).reverse();
        return firstHalf.toString() + middle + secondHalf.toString();
    }
}