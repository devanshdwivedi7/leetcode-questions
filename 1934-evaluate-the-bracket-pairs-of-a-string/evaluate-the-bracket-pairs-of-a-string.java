class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                int j = i + 1;
                while (j < n && s.charAt(j) != ')') {
                    j++;
                }
                String key = s.substring(i + 1, j);
                result.append(map.getOrDefault(key, "?"));
                i = j;
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}