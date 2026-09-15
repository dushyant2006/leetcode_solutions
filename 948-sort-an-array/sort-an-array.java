class Solution {
    public int[] sortArray(int[] nums) {
        int[] count = new int[100001];

        for (int num : nums) {
            count[num + 50000]++;
        }

        int index = 0;

        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                nums[index++] = i - 50000;
                count[i]--;
            }
        }

        return nums;
    }
}