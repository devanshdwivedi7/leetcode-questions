class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long limit = 1000;
        
        while (limit <= n) {
            totalCommas += (n - limit + 1);
            limit *= 1000;
        }
        
        return totalCommas;
    }
}