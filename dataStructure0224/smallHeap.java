package com.zhangyu.datastructure.dataStructure0224;

public class smallHeap {
    public static void main(String[] args){

    }

    //显然使用小根堆来完成
    public static void follish(int[] arr){
        int index=0;//记录heap的大小
        for (int i = arr.length - 1; i >= 0; i--) {
            if(arr[i]<=0){
                arr[i]=Integer.MAX_VALUE;
                swap(arr,i,arr.length-1-index);
                index++;
            }
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            heapDown(arr,i,arr.length);
        }
        int positive=1;
        int heapSize=arr.length;
        for (int i = 0; i < arr.length; i++) {
            if(arr[0]!=positive){

            }else{
                arr[0]=Integer.MAX_VALUE;
                System.out.println(arr[0]+" "+arr[heapSize-1]);
                swap(arr,0,--heapSize);
                heapDown(arr,0,heapSize);
                positive++;
            }
        }
    }

    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public static void heapDown(int[] arr,int i,int heapSize){
        int left=2*i+1;
        left=left+1<heapSize && arr[left+1]<arr[left]?left+1:left;//left为两者中的较小值
        while (left<heapSize){
            if(arr[i]>arr[left]){
                swap(arr,i,left);
                i=left;
                left=2*left+1;
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
}
