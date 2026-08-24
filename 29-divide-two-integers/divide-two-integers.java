class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean isNegative = (dividend < 0) ^ (divisor < 0);
        int negDividend = dividend < 0 ? dividend : -dividend;
        int negDivisor = divisor < 0 ? divisor : -divisor;
        int quotient = 0;
        while (negDividend <= negDivisor) {
            int currentDivisor = negDivisor;
            int multiple = 1; 
            while (currentDivisor >= (Integer.MIN_VALUE >> 1) && negDividend <= (currentDivisor << 1)) {
                currentDivisor <<= 1;
                multiple <<= 1;
            }
            negDividend -= currentDivisor;
            quotient += multiple;
        }
        return isNegative ? -quotient : quotient;
    }
}