class Solution {
    public int hammingDistance(int x, int y) {
        int xor=x^y;
        int result=Integer.bitCount(xor);
        return result;
    }
}