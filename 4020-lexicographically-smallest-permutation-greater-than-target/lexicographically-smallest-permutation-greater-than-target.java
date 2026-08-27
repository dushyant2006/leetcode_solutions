class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();

        // Frequency of characters in s
        int[] total = new int[26];

        for (char ch : s.toCharArray()) {
            total[ch - 'a']++;
        }

        // Try each position from right to left
        for (int i = n - 1; i >= 0; i--) {

            // Characters needed for target[0 ... i-1]
            int[] remaining = total.clone();

            boolean possible = true;

            for (int j = 0; j < i; j++) {
                int x = target.charAt(j) - 'a';

                remaining[x]--;

                if (remaining[x] < 0) {
                    possible = false;
                    break;
                }
            }

            if (!possible) {
                continue;
            }

            // At position i, choose the smallest character
            // that is greater than target[i]
            int current = target.charAt(i) - 'a';

            for (int c = current + 1; c < 26; c++) {

                if (remaining[c] > 0) {

                    StringBuilder ans = new StringBuilder();

                    // Keep prefix same as target
                    ans.append(target.substring(0, i));

                    // Make it strictly greater here
                    ans.append((char) ('a' + c));

                    remaining[c]--;

                    // Put all remaining characters in sorted order
                    for (int j = 0; j < 26; j++) {
                        while (remaining[j] > 0) {
                            ans.append((char) ('a' + j));
                            remaining[j]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}