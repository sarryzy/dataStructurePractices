package com.zhangyu.datastructure.dataStructure0301;

import java.util.Arrays;

public class GetLessThanK {
    public static void main(String[] args){
        t1();
    }
    /**
     * ### 4.数组中可能出现任意数,求小于等于k的最长子数组.
     *
     * 思路:准备两个数组,其中一个记录数组中每个位置开始的最小值,另一个记录最小值位置处的索引.
     * 如果遇到0可以选择扩张或者不扩张.
     *
     * 然后开始从头遍历数组,来看从每一个位置开始的最小的累加和.如果这个位置的值都比要求的值大的话,则肯定不能以这个位置开头.如果开始的位置比要求的值小,则可以继续向后扩张.
     * 最终得到以第一个位置开头的最长的答案.然后从第二个位置开始看,并且右边界也开始右扩.因为在窗口内的答案肯定不能成立.
     */

    //暴力方法
    public static int getLessThanK1(int[] arr,int k){
        if(arr==null){
            return 0;
        }
        int len=0;
        int sum=0;
        for (int i = 0; i < arr.length; i++) {
            sum=0;
            for (int j = i; j < arr.length; j++) {
                sum+=arr[j];
                if(sum<=k){
                    len=Math.max(len,j-i+1);
                }
            }
        }
        return len;
    }

    public static int getLessThanK2(int[] arr,int k){
        //首先构造两个当前位置最小值以及最小值索引的数组
        if(arr==null){
            return 0;
        }
        int[] minSum=new int[arr.length];
        int[] minSumIndex=new int[arr.length];
        minSum[minSum.length-1]=arr[arr.length-1];
        minSumIndex[minSum.length-1]=arr.length-1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if(minSum[i+1]<=0){
                minSum[i]=arr[i]+minSum[i+1];
                minSumIndex[i]=minSumIndex[i+1];
            }else{
                minSum[i]=arr[i];
                minSumIndex[i]=i;
            }
        }
        int end=0;//表示当前右扩的范围的下一个
        int len=0;
        int sum=0;
        for (int i = 0; i < arr.length; i++) {
            while (end<arr.length && sum+minSum[end]<=k){
                sum+=minSum[end];
                end=minSumIndex[end]+1;
            }
            //出来之后说明当前已经不能够右扩了,当前是以i开头最大的答案
            len=Math.max(len,end-i);
            if(end-i>0){
                //end右扩了
                sum-=arr[i];
            }else{
                end=i+1;
            }
        }
        return len;
    }

    public static int[] generateArr(int max,int len){
        int[] arr=new int[(int)(Math.random()*len+1)];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int)(Math.random()*max)-(int)(Math.random()*max);
        }
        return arr;
    }

    public static void t1(){
        int testTimes=1000000;
        int max=10;
        int len=10;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateArr(max, len);
            int k=(int)(Math.random()*max-Math.random()*max);
            if(getLessThanK1(arr,k)!=getLessThanK2(arr,k)){
                System.out.println("错戳了");
                System.out.println(Arrays.toString(arr));
                System.out.println("k:"+k);
                break;
            }
            System.out.println(i);
        }
    }
}
