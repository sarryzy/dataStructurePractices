package com.zhangyu.datastructure.dataStructure0204;

public class slowHeap {
    private int[] arr=new int[1000000];

    private int heapSize;

    public boolean isEmpty(){
        return heapSize==0;
    }
    public void push(int num){
        heapSize++;
        arr[heapSize-1]=num;
    }

    public int pop(){
        if(heapSize==0){
            return -1;
        }
        int max=0;
        for (int i = 1; i < heapSize; i++) {
            if(arr[i]>arr[max]){
                max=i;
            }
        }
        int res=arr[max];
        arr[max]=arr[--heapSize];
        return res;
    }

    public void printHeap(){
        for (int i = 0; i < heapSize; i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
