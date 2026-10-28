class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> st = new Stack<>();
        int[] arr = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            while (!st.isEmpty() && temperatures[st.peek()] < temperatures[i]) {
                int j = st.pop();
                arr[j] = i - j;
            }
            st.push(i);
        }

        return arr;
    }
}