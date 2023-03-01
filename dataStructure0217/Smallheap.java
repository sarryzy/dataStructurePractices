package com.zhangyu.datastructure.dataStructure0217;

public class Smallheap {
    /**
     * 练习小根堆,提供方法,向上heapify和向下heapify,弹出,加入
     */
    int[] arr;
    int heapSize;

    public Smallheap(){
        arr=new int[200];
        heapSize=0;
    }

    public boolean isEmpty(){
        return heapSize==0;
    }

    public void add(int num){
        //加入一个数
        arr[heapSize]=num;
        heapUp(heapSize++);
    }

    public int pop(){
        if(heapSize==0){
            throw new RuntimeException("空栈");
        }
        int res=arr[0];
        swap(arr,0,--heapSize);
        heapDown(0);
        return res;
    }

    /**
     * 从一个位置向上heapify
     * @param i
     */
    public void heapUp(int i){
        while (arr[i]<arr[(i-1)/2]){
            //当这个位置比他的父位置的点要小的时候,向上进行调整
            swap(arr,i,(i-1)/2);
            i=(i-1)/2;
        }
    }

    public void heapDown(int i){
        int index=i;
        int left=2*i+1;
        while (left<heapSize){
            left=left+1<heapSize && arr[left+1]<arr[left]?left+1:left;//左右两个儿子中找出最小的
            if(arr[i]<arr[left]){
                break;
            }
            swap(arr,i,left);
            i=left;
            left=2*i+1;
        }
    }

    public void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
