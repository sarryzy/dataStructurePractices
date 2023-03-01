package com.zhangyu.datastructure.dataStructure0224;

import java.util.Arrays;

public class KMP {
    public static void main(String[] args){
        for(int i=0;i<1000000;i++){
            char[] char1 = generateChar(100);
            char[] char2 = generateChar(100);
            kmp(new String(char1),new String(char2));
            System.out.println(i);
        }
    }

    public static char[] generateChar(int n){
        char[] arr=new char[n];
        for (int i = 0; i < n; i++) {
            char c=(char)((int)(Math.random()*26)+'a');
            arr[i]=c;
        }
        return arr;
    }

    /**
      ### 1.KMP算法

      思路:根据next数组得到每个字符的最大的前缀后缀的相等的个数.然后利用这个数组进行跳动,如果跳动到0的时候直接开始下一个字符的比对.

      next数组的设计的思想也是如此.
     */
    public static int kmp(String str,String match){
        if(str==null || match==null){
            return -1;
        }
        int[] next = getNext(match);
        char[] char1 = str.toCharArray();
        char[] char2 = match.toCharArray();
        int x=0;
        int y=0;
        while (x<str.length() && y<match.length()){
            if(char1[x]==char2[y]){
                x++;
                y++;
            }else if(y>0){
                y=next[y];
            }else{
                x++;
            }
        }
        return y== char2.length?x-y:-1;
    }

    public static int[] getNext(String match){
        int[] res=new int[match.length()];
        char[] arr = match.toCharArray();
        res[0]=-1;
        res[1]=0;
        int p=0;
        int i=2;
        while (i<res.length){
            if(arr[i-1]==arr[p]){
                res[i++]=++p;
            }else if(p>0){
                p=res[p];
            }else{
                res[i++]=0;
            }
        }
        return res;
    }

}
