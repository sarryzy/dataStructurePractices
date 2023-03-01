package com.zhangyu.datastructure.dataStructure0205;

import java.util.PriorityQueue;

public class practice02 {
    public static void main(String[] args){
        t1();
    }

    /**
     * 对一个数组进行排序,每个数字移动的距离不超过k
     *
     * 用一个堆,每次放进k个数字,取出第一个,然后放一个,再取
     */
    public static void sortLessThanK(int[] arr,int k){
        PriorityQueue<Integer> heap=new PriorityQueue<>();
        int i=0;//用于记录数组的索引
        int j=0;
        for (; j <Math.min(arr.length,k+1); j++) {
            heap.add(arr[j]);
        }
        for (; j < arr.length; j++) {
            arr[i++]=heap.poll();
            heap.add(arr[j]);
        }
        while (!heap.isEmpty()){
            arr[i++]=heap.poll();
        }
    }

    public static int[] getArr(){
        //生成位数不超过k的数组
        //每k段分一个
        int maxVal=1000000;
        int maxLen=10000;
        int len=(int)(Math.random()*maxLen);
        int[] arr=new int[len];
        int k=5;
        int i=0;
        int val=(int)(Math.random()*maxVal);
        for (; i <len; i++) {
            int r=(int)(Math.random()*(val*k/len));
            arr[i]=r+(val*k/len*(i/k));
        }
        return arr;
    }

    public static boolean judge(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            if(arr[i]>arr[i+1]){
                return false;
            }
        }
        return true;
    }

    public static void t1(){
        for (int i = 0; i < 1000000; i++) {
            int[] arr = getArr();
            sortLessThanK(arr,5);
            if(!judge(arr)){
                System.out.println("出错了");
            }
            System.out.println(i);
        }
        System.out.println("Nice!");
    }
}
