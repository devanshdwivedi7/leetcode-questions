
class Solution {
    public String smallestNumber(String num, long t) {
        long temp = t;
        int c2 = 0, c3 = 0, c5 = 0, c7 = 0;
        
        while (temp % 2 == 0) { c2++; temp /= 2; }
        while (temp % 3 == 0) { c3++; temp /= 3; }
        while (temp % 5 == 0) { c5++; temp /= 5; }
        while (temp % 7 == 0) { c7++; temp /= 7; }
        if (temp > 1) {
            return "-1";
        }

        int n = num.length();
        int[] p2 = new int[n + 1];
        int[] p3 = new int[n + 1];
        int[] p5 = new int[n + 1];
        int[] p7 = new int[n + 1];

        int firstZero = -1;
        for (int i = 0; i < n; i++) {
            char ch = num.charAt(i);
            if (ch == '0') {
                firstZero = i;
                break;
            }
            int digit = ch - '0';
            p2[i + 1] = p2[i] + getFactorCount(digit, 2);
            p3[i + 1] = p3[i] + getFactorCount(digit, 3);
            p5[i + 1] = p5[i] + getFactorCount(digit, 5);
            p7[i + 1] = p7[i] + getFactorCount(digit, 7);
        }
        if (firstZero == -1) {
            if (p2[n] >= c2 && p3[n] >= c3 && p5[n] >= c5 && p7[n] >= c7) {
                return num;
            }
        }
        int maxI = (firstZero != -1) ? firstZero : n - 1;

        for (int i = maxI; i >= 0; i--) {
            int req2 = Math.max(0, c2 - p2[i]);
            int req3 = Math.max(0, c3 - p3[i]);
            int req5 = Math.max(0, c5 - p5[i]);
            int req7 = Math.max(0, c7 - p7[i]);

            int startDigit = (i < n) ? (num.charAt(i) - '0' + 1) : 1;

            for (int d = startDigit; d <= 9; d++) {
                int rem2 = Math.max(0, req2 - getFactorCount(d, 2));
                int rem3 = Math.max(0, req3 - getFactorCount(d, 3));
                int rem5 = Math.max(0, req5 - getFactorCount(d, 5));
                int rem7 = Math.max(0, req7 - getFactorCount(d, 7));

                String suffix = getMinSuffix(rem2, rem3, rem5, rem7);
                int lenNeeded = suffix.length();

                if (lenNeeded <= n - 1 - i) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i);
                    sb.append(d);
                    
                    int onesToPad = (n - 1 - i) - lenNeeded;
                    for (int k = 0; k < onesToPad; k++) {
                        sb.append('1');
                    }
                    sb.append(suffix);
                    return sb.toString();
                }
            }
        }
        String suffix = getMinSuffix(c2, c3, c5, c7);
        int targetLen = Math.max(n + 1, suffix.length());
        
        StringBuilder sb = new StringBuilder();
        int onesToPad = targetLen - suffix.length();
        for (int k = 0; k < onesToPad; k++) {
            sb.append('1');
        }
        sb.append(suffix);
        return sb.toString();
    }
    private int getFactorCount(int digit, int prime) {
        int count = 0;
        while (digit > 0 && digit % prime == 0) {
            count++;
            digit /= prime;
        }
        return count;
    }
    private String getMinSuffix(int r2, int r3, int r5, int r7) {
        int count9 = r3 / 2;
        r3 %= 2;
        int count8 = r2 / 3;
        r2 %= 3;
        int count7 = r7;
        int count5 = r5;
        int count6 = 0;
        if (r3 == 1 && r2 == 1) {
            count6 = 1;
            r3 = 0;
            r2 = 0;
        }
        int count4 = r2 / 2;
        r2 %= 2;
        int count3 = r3;
        int count2 = r2;
        if (count3 == 1 && count4 == 1) {
            count3 = 0;
            count4 = 0;
            count6 = 1;
            count2 = 1;
        }
        StringBuilder sb = new StringBuilder();
        appendDigits(sb, '2', count2);
        appendDigits(sb, '3', count3);
        appendDigits(sb, '4', count4);
        appendDigits(sb, '5', count5);
        appendDigits(sb, '6', count6);
        appendDigits(sb, '7', count7);
        appendDigits(sb, '8', count8);
        appendDigits(sb, '9', count9);
        return sb.toString();
    }
    private void appendDigits(StringBuilder sb, char digit, int count) {
        for (int i = 0; i < count; i++) {
            sb.append(digit);
        }
    }
}