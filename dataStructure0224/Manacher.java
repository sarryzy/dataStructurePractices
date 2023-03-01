package com.zhangyu.datastructure.dataStructure0224;

import java.util.Arrays;

public class Manacher {
    public static void main(String[] args){
        getPalind("abcde123321");
    }

    //### 7.将一个字符串变为回文子串的最小代价.
    //
    //思路:从左向右找到哪一个位置的回文子串可以包含到最后一个位置.然后找到其左边界,将其逆序即为最后的答案.
    public static String getPalind(String strx){
        char[] str = getString(strx);
        int[] arr=new int[str.length];
        int center=-1;
        int right=-1;
        int index=0;
        for (int i = 0; i < str.length; i++) {
            arr[i]=right>i?Math.min(arr[2*center-i],right-i):1;
            while (i+arr[i]<arr.length && i-arr[i]>-1){
                if(str[i+arr[i]]==str[i-arr[i]]){
                    arr[i]++;
                }else{
                    break;
                }
            }
            if(i+arr[i]>right){
                right=arr[i]+i;
                center=i;
            }
            if(right==str.length){
                index=arr[i]-1;
                break;
            }
        }
        char[] res=new char[strx.length()-index];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i]=strx.charAt(res.length-1-i);
        }
        return String.valueOf(res);
    }

    //将字符串中间穿插#
    public static char[] getString(String str){
        char[] arr=new char[str.length()*2+1];
        for (int i = 0; i < arr.length; i++) {
            if(i%2!=0){
                arr[i]=str.charAt(i/2);
            }else{
                arr[i]='#';
            }
        }
        return arr;
    }
}
