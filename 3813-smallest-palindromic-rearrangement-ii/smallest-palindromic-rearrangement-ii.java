import java.util.*;

class Solution {
    private static final long LIMIT = 1_000_001L;

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];

        for (char c : s.toCharArray())
            freq[c - 'a']++;

        int[] half = new int[26];
        StringBuilder mid = new StringBuilder();

        int m = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            m += half[i];

            if ((freq[i] & 1) == 1)
                mid.append((char) ('a' + i));
        }

        long[][] C = buildComb(m);

        long total = countWays(half, m, C);

        if (total < k)
            return "";

        StringBuilder left = new StringBuilder();

        while (m > 0) {

            for (int c = 0; c < 26; c++) {

                if (half[c] == 0)
                    continue;

                half[c]--;

                long ways = countWays(half, m - 1, C);

                if (ways >= k) {
                    left.append((char) ('a' + c));
                    m--;
                    break;
                } else {
                    k -= ways;
                    half[c]++;
                }
            }
        }

        StringBuilder right = new StringBuilder(left).reverse();

        return left.toString() + mid + right.toString();
    }

    private long[][] buildComb(int n) {

        long[][] C = new long[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {

            C[i][0] = C[i][i] = 1;

            for (int j = 1; j < i; j++) {

                C[i][j] = Math.min(LIMIT, C[i - 1][j - 1] + C[i - 1][j]);
            }
        }

        return C;
    }

    private long countWays(int[] cnt, int total, long[][] C) {

        long ans = 1;

        int rem = total;

        for (int x : cnt) {

            if (x == 0)
                continue;

            ans *= C[rem][x];

            if (ans > LIMIT)
                ans = LIMIT;

            rem -= x;
        }

        return ans;
    }
}