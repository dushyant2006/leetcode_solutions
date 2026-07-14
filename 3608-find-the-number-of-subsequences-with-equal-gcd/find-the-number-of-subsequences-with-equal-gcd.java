class Solution {
    static final int MOD = 1_000_000_007;
    int[][][] dp;
    int[] nums;
    int n;

    public int subsequencePairCount(int[] nums) {
        this.nums = nums;
        n = nums.length;
        dp = new int[n + 1][201][201];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= 200; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return solve(0, 0, 0);
    }

    private int solve(int idx, int g1, int g2) {
        if (idx == n) {
            return (g1 != 0 && g1 == g2) ? 1 : 0;
        }

        if (dp[idx][g1][g2] != -1) {
            return dp[idx][g1][g2];
        }

        long ans = 0;

        ans += solve(idx + 1, gcd(g1, nums[idx]), g2);

        ans += solve(idx + 1, g1, gcd(g2, nums[idx]));

        ans += solve(idx + 1, g1, g2);

        return dp[idx][g1][g2] = (int) (ans % MOD);
    }

    private int gcd(int a, int b) {
        return a == 0 ? b : gcd(b % a, a);
    }
}