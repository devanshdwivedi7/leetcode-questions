class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int n = s.length();
        int prefixLen = 0;
        for (int i = 0; i < n; i++) {
            if (freq[target.charAt(i) - 'a'] > 0) {
                freq[target.charAt(i) - 'a']--;
                prefixLen++;
            } else {
                break;
            }
        }
        if (prefixLen == n) {
            prefixLen--;
            freq[target.charAt(prefixLen) - 'a']++;
        }
        for (int i = prefixLen; i >= 0; i--) {
            char t = target.charAt(i);
            for (int c = t - 'a' + 1; c < 26; c++) {
                if (freq[c] > 0) {
                    freq[c]--; 
                    
                    StringBuilder sb = new StringBuilder();
                    sb.append(target.substring(0, i)); 
                    sb.append((char) (c + 'a'));       
                    
             
                    for (int j = 0; j < 26; j++) {
                        while (freq[j] > 0) {
                            sb.append((char) (j + 'a'));
                            freq[j]--;
                        }
                    }
                    
                    return sb.toString();
                }
            }
            if (i > 0) {
                freq[target.charAt(i - 1) - 'a']++;
            }
        }

        return "";
    }
}