class Solution {
    public int trap(int[] height) {
        int n=height.length;
        int l=0,r=n-1,lm=0,rm=0;
        int trap=0;

        while(l<r){
            if(height[l] <= height[r]){
                if(lm<=height[l]){
                    lm=height[l];
                }
                else{
                    trap +=lm-height[l];
                }
                l++;
            }
            else{
                 if(rm <= height[r]){
                    rm=height[r];
                 }
                 else{
                    trap += rm-height[r];
                 }
                 r--;
            }
        }
        return trap;
    }
}