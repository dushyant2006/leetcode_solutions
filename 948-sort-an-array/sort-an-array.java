class Solution {
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    void quickSort(int[] a, int l, int r) {
        while (l < r) {
            int i = l, j = r;
            int pivot = a[l + (r - l) / 2];

            while (i <= j) {
                while (a[i] < pivot) i++;
                while (a[j] > pivot) j--;

                if (i <= j) {
                    int t = a[i];
                    a[i] = a[j];
                    a[j] = t;
                    i++;
                    j--;
                }
            }

            if (j - l < r - i) {
                if (l < j) quickSort(a, l, j);
                l = i;
            } else {
                if (i < r) quickSort(a, i, r);
                r = j;
            }
        }
    }
}