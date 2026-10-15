class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum < 0 || sum > 9 * num) {
            return "";
        }
        int[] digits = new int[num];
        for (int i = 0; i < num; i++) {
            int d = Math.min(9, sum);
            digits[i] = d;
            sum -= d;
        }
        StringBuilder sb = new StringBuilder();
        for (int d : digits) {
            sb.append(d);
        }
        return sb.toString();
    }
}