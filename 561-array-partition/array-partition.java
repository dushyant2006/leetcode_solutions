class Solution {
    public int arrayPairSum(int[] nums) {
        int[] count = new int[20001];

        for (int num : nums) {
            count[num + 10000]++;
        }

        int sum = 0;
        boolean take = true;

        for (int i = 0; i < count.length; i++) {
            while (count[i]-- > 0) {
                if (take) {
                    sum += i - 10000;
                }
                take = !take;
            }
        }

        return sum;
    }
}