package com.zhangyu.datastructure.dataStructure0224;

import java.util.Arrays;

public class Manachre {
    public static void main(String[] args){
        String str="abccba";
        int palindrom1 = getPalindrom1(str);
        System.out.println(palindrom1);
    }

    /**
     * ### 6.最长回文子串
     *
     * 简单方法:从每一个位置向外扩,如果左边右边相等就继续扩张.但是这样只能够得到长度为奇数的回文串.
     *
     * 解决办法:在相邻两个字符之间添加一个新的符号,然后这样就可以包含所有的了,最终结果需要进行除以2.
     *
     * 复杂方法:manacher算法;以最大的回文区域划分一个最大的回形区域,这个区域内的关于中心点的回文区域数目对称.
     *
     * 如果确定了一个较大的区域,这个区域如果i在R内,直接等于i',如果i'区域在外,那么i回文区域直接等于R-i.如果i压线.
     *
     * i在R外,暴力方法
     *
     * i在R内,1.i'在内部.2.i'在外部.3.i'压线.
     */
    public static int getPalindrom1(String strx){
        char[] str = strx(strx);
        int[] arr=new int[str.length];//记录结果
        int center=-1;//表示中心
        int right=-1;//表示右边界
        int max=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            arr[i]=right>i?Math.min(arr[2*center-i],right-i):1;
            while (i+arr[i]<arr.length && i-arr[i]>-1){
                if(str[i+arr[i]]==str[i-arr[i]]){
                    arr[i]++;
                }else{
                    break;
                }
            }
            max=Math.max(max,arr[i]);
            if(i+arr[i]>right){
                right=i+arr[i];
                center=i;
            }
        }
        return max-1;
    }

    public static char[] strx(String str){
        char[] arr=new char[2*str.length()+1];
        arr[0]='#';
        arr[arr.length-1]='#';
        for(int i=0;i<arr.length;i++){
            if(i%2!=0){
                arr[i]=str.charAt(i/2);
            }else{
                arr[i]='#';
            }
        }
        return arr;
    }
}
