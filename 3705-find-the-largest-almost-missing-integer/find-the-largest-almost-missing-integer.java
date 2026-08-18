class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

       
        if (k == n) {
            int max = -1;
            for (int x : nums) {
                max = Math.max(max, x);
            }
            return max;
        }

       
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }

    
        if (k == 1) {
            int ans = -1;
            for (int x : nums) {
                if (freq.get(x) == 1) {
                    ans = Math.max(ans, x);
                }
            }
            return ans;
        }

    
        int ans = -1;
        if (freq.get(nums[0]) == 1) {
            ans = Math.max(ans, nums[0]);
        }
        if (freq.get(nums[n - 1]) == 1) {
            ans = Math.max(ans, nums[n - 1]);
        }

        return ans;
    }
}