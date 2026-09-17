import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLen = new int[n];
        Arrays.fill(minLen, Integer.MAX_VALUE);
        
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        
        int sum = 0;
        int result = Integer.MAX_VALUE;
        int currentMinLen = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            
            if (map.containsKey(sum - target)) {
                int start = map.get(sum - target);
                int currLen = i - start;
                
                if (start >= 0 && minLen[start] != Integer.MAX_VALUE) {
                    result = Math.min(result, currLen + minLen[start]);
                }
                
                currentMinLen = Math.min(currentMinLen, currLen);
            }
            
            minLen[i] = currentMinLen;
            map.put(sum, i);
        }
        
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}