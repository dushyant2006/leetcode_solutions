class Solution {
    public void duplicateZeros(int[] arr) {
        int n = arr.length;
        int[] ne = new int[n];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (arr[j] == 0) {
                if (i != n - 1) {
                    ne[i] = 0;
                    ne[i + 1] = 0;
                } else {
                    ne[i] = 0;
                }
                i++;
                j++;
            } else {
                ne[i] = arr[j];
                j++;
            }
        }
        for (int i = 0; i < n; i++) {
            arr[i] = ne[i];
        }
    }
}