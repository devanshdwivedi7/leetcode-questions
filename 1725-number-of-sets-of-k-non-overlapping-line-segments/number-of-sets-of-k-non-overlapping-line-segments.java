class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007;
        long n_val = n + k - 1;
        long r = 2 * k;

        if (r > n_val) {
            return 0;
        }

        long num = 1;
        long den = 1;

        for (int i = 0; i < r; i++) {
            num = (num * (n_val - i)) % MOD;
            den = (den * (i + 1)) % MOD;
        }

        return (int) ((num * modInverse(den, MOD)) % MOD);
    }

    private long modInverse(long a, long m) {
        return power(a, m - 2, m);
    }

    private long power(long x, long y, long m) {
        long res = 1;
        x = x % m;
        while (y > 0) {
            if ((y & 1) == 1) {
                res = (res * x) % m;
            }
            y >>= 1;
            x = (x * x) % m;
        }
        return res;
    }
}