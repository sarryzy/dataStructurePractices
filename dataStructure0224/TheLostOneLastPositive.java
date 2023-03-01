package com.zhangyu.datastructure.dataStructure0224;

import java.util.Arrays;

public class TheLostOneLastPositive {
    public static void main(String[] args){
        int[] arr={1,2,33,3,3,3,3,3,3,3,3,3,33,3,3,5};
        int num = findNum(arr);
        System.out.println(num);
    }
    /**
     * 缺失的第一个正数,思路,利用heapsort进行排序,然后找出是哪一个正数
     * 二叉树按层遍历感觉能找出这个数来
     */
    public static int findNum(int[] arr){
        int index=0;//记录heap的大小
        for (int i = arr.length - 1; i >= 0; i--) {
            if(arr[i]<=0){
                arr[i]=Integer.MAX_VALUE;
                swap(arr,i,arr.length-1-index);
                index++;
            }
        }
        int positive=1;
        int heapSize=arr.length - index;
        for(int i=heapSize-1;i>=0;i--){
            heapDown(arr,i,heapSize);
        }
        //每次将一个最小值放到第一个,看这个值是不是应该的值
        for (int i = 0; i < arr.length; i++) {
            if(arr[0]==positive){
                arr[0]=Integer.MAX_VALUE;
                swap(arr,0,--heapSize);
                heapDown(arr,0,heapSize);
                positive++;
            }else if(arr[0]==positive-1){
                arr[0]=Integer.MAX_VALUE;
                swap(arr,0,--heapSize);
                heapDown(arr,0,heapSize);
            }else{
                break;
            }
        }
        return positive;
    }

    public static void heapDown(int[] arr,int i,int heapSize){
        int left=2*i+1;
        while (left<heapSize){
            left=left+1<heapSize && arr[left+1]<arr[left]?left+1:left;//left为两者中的较小值
            if(arr[i]>arr[left]){
                swap(arr,i,left);
                i=left;
                left=2*i+1;
            }else{
                break;
            }
        }
    }

    public static void heapUp(int[] arr,int i){
        while (arr[i]<arr[(i-1)/2]){
            swap(arr,i,(i-1)/2);
            i=(i-1)/2;
        }
    }

    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
