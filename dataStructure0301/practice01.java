package com.zhangyu.datastructure.dataStructure0301;

import java.util.TreeMap;

public class practice01 {

    //荷兰国旗问题,应用中间值进行划分
    public static int[] partition(int[] arr,int pivot){
        int left=-1;
        int right=arr.length;
        //交换的规则,如果当前值比pivot小,则与小于区前面的一个数进行交换,
        // 如果相等直接跳过,如果大则与大于区的前一个数进行交换,返回值是小于区的后一个和大于区的前一个以防止越界
        int index=0;
        while (index<arr.length){
            if(arr[index]<pivot){
                swap(arr,index,++left);
            }else if(arr[index]==pivot){
                index++;
            }else{
                swap(arr,index,--right);
            }
        }
        return new int[]{left+1,right-1};
    }

    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public static void treemap(){
        TreeMap<Integer,Integer> map=new TreeMap<>();
    }
}
