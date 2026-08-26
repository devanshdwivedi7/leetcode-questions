class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        String ans = "";
        int left = 0;
        int count1 = 0;
        
        for (int right = 0; right < s.length(); right++) {
            if (s.charAt(right) == '1') {
                count1++;
            }
            while (count1 > k || (left <= right && count1 == k && s.charAt(left) == '0')) {
                if (s.charAt(left) == '1') {
                    count1--;
                }
                left++;
            }

            if (count1 == k) {
                String current = s.substring(left, right + 1);
                if (ans.isEmpty() || current.length() < ans.length() || 
                   (current.length() == ans.length() && current.compareTo(ans) < 0)) {
                    ans = current;
                }
            }
        }
        return ans;
    }
}