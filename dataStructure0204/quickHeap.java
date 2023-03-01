package com.zhangyu.datastructure.dataStructure0204;

/**
 * 大根堆,提供isEmpty( ),pop( ),size( ),push( )
 */
public class quickHeap {
    private int[] arr=new int[1000000];
    private int heapSize;
    
    public boolean isEmpty(){
        return heapSize==0;
    }
    public void push(int num){
        heapSize++;
        arr[heapSize-1]=num;
        heap1();
    }

    public int pop(){
        if(heapSize==0){
            return -1;
        }
        int res=arr[0];
        swap(0,heapSize-1);
        heapSize--;
        heap2();
        return res;
    }
    
    /**
     * 从下到顶heapify
     * 在当前位置找自己的父,直到父比自己大,如果比自己小则交换
     */
    public void heap1(){
        int index=heapSize-1;
        while (arr[index]>arr[(index-1)/2]){
            swap(index,(index-1)/2);
            index=(index-1)/2;
        }
    }

    /**
     * 从头到下heapify
     */
    public void heap2(){
        int index=0;
        int left=2*index+1;
        while (left<heapSize){
            left=left+1<heapSize && arr[left]<arr[left+1] ? left+1:left;
            if(arr[index]>arr[left]){
                break;
            }
            swap(index,left);
            index=left;
            left=2*index+1;
        }
    }
    
    public void swap(int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public void printHeap(){
        for (int i = 0; i < heapSize; i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
