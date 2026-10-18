class Solution {
    public boolean lemonadeChange(int[] bills) {
        int fiveChange=0;
        int tenChange=0;
        int twentyChange=0;
        boolean answer=true;
        for(int i=0;i<bills.length;i++){
            if(bills[i]==5){
                fiveChange++;
            }
            if(bills[i]==10){
                if(fiveChange>0){
                    fiveChange--;
                    tenChange++;
                }
                else answer=false;
            }
            if(bills[i]==20){
                if(fiveChange>0 && tenChange>0){
                    fiveChange--;
                    tenChange--;
                    twentyChange++;
                }
                else if(fiveChange>2){
                    fiveChange-=3;
                    twentyChange++;
                }
                else{
                    answer=false;
                }
            }
           
        }
        return answer;
    }
}