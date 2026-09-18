class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';
            if (first[ch] == -1) {
                first[ch] = i;
            }
            last[ch] = i;
        }

        List<String> result = new ArrayList<>();
        int lastEnd = -1;

        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';
            if (i == first[ch]) {
                int newEnd = checkSubstring(s, i, first, last);
                if (newEnd != -1) {
                    if (i > lastEnd) {
                        result.add("");
                    }
                    lastEnd = newEnd;
                    result.set(result.size() - 1, s.substring(i, lastEnd + 1));
                }
            }
        }

        return result;
    }

    private int checkSubstring(String s, int start, int[] first, int[] last) {
        int right = last[s.charAt(start) - 'a'];
        for (int i = start; i <= right; i++) {
            int ch = s.charAt(i) - 'a';
            if (first[ch] < start) {
                return -1;
            }
            right = Math.max(right, last[ch]);
        }
        return right;
    }
}