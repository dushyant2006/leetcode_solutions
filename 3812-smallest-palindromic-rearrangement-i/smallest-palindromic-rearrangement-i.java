class Solution {
    public String smallestPalindrome(String s) {
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        StringBuilder first = new StringBuilder();
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < cnt[i] / 2; j++) {
                first.append((char) ('a' + i));
            }
            if (cnt[i] % 2 == 1) {
                mid = (char) ('a' + i);
            }
        }

        StringBuilder ans = new StringBuilder();
        ans.append(first);

        if (mid != 0) {
            ans.append(mid);
        }

        ans.append(new StringBuilder(first).reverse());

        return ans.toString();
    }
}