package com.zhangyu.datastructure.dataStructure0221;

public class CommonSubsequence {
    public static void main(String[] args){
        String str1="abcdefgh";
        String str2="ghbcd";
        int longestSubsequence1 = getLongestSubsequence1(str1, str2);
        System.out.println(longestSubsequence1);
    }

    public static int getLongestSubsequence1(String str1,String str2){
        return process1(str1,str2,str1.length()-1,str2.length()-1);
    }

    public static int process1(String str1,String str2,int i,int j){
//        if(i==0 && j==0){
//            return str1.charAt(i)==str2.charAt(j)?1:0;
//        }
        if(i==0){
            for (int k = 0; k <=j; k++) {
                if(str2.charAt(k)==str1.charAt(i)){
                    return 1;
                }
            }
            return 0;
        }
        if(j==0){
            for (int k = 0; k <=i; k++) {
                if(str1.charAt(k)==str2.charAt(j)){
                    return 1;
                }
            }
            return 0;
        }
        //
        int max=0;
        max=Math.max(process1(str1,str2,i-1,j),process1(str1,str2,i,j-1));
        //i和j位置刚好相等
        if(str1.charAt(i)==str2.charAt(j)){
            max=Math.max(max,process1(str1,str2,i-1,j-1)+1);

        }
        return max;
    }

    public static int getLongestSubsequence2(String str1,String str2){
        return process2(str1,str2);
    }

    public static int process2(String str1,String str2){
        int[][] dp=new int[str1.length()][str2.length()];
        //初始化
//        for (int i = 0; i < str2.length(); i++) {
//            if(str2.charAt(i)==str1.charAt(0)){
//                for(int j=i;j<str2.length();j++){
//                    dp[0][j]=1;
//                }
//                break;
//            }
//        }
        dp[0][0]=str1.charAt(0)==str2.charAt(0)?1:0;
        for(int i=1;i<str1.length();i++){
            dp[i][0]=Math.max(dp[i-1][0],str1.charAt(i)==str2.charAt(0)?1:0);
        }
        for(int i=1;i<str2.length();i++){
            dp[0][i]=Math.max(dp[0][i-1],str1.charAt(0)==str2.charAt(i)?1:0);
        }
        for (int i = 0; i < str1.length(); i++) {
            if(str1.charAt(i)==str2.charAt(0)){
                for(int j=i;j<str1.length();j++){
                    dp[j][0]=1;
                }
                break;
            }
        }
        for(int i=1;i<str1.length();i++){
            for(int j=1;j<str2.length();j++){
                dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                if(str1.charAt(i)==str2.charAt(j)){
                    dp[i][j]=Math.max(dp[i][j],dp[i-1][j-1]+1);
                }
            }
        }
        return dp[str1.length()-1][str2.length()-1];
    }
}
