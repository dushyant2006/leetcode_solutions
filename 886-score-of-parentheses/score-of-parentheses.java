class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> result=new Stack<>();
        int st=0;
        for(char ch : s.toCharArray()){
            if(ch=='('){
                result.push(st);
                st=0;
            }

            else{
                st=result.pop()+Math.max(st*2,1);
            }
        }
        return st;
    }
}