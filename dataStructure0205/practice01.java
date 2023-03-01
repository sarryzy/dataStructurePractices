package com.zhangyu.datastructure.dataStructure0205;

import java.util.Arrays;

public class practice01 {
    public static void main(String[] args){
//        int[] arr={2,8,4,2,8,23,23,4,5,6,7};
//        heapSort(arr);
//        System.out.println(Arrays.toString(arr));
        t1();
    }

    /**
     * 对堆结构O(N)的优化
     */
    public static void heap(int[] arr){
        for (int i = arr.length - 1; i >= 0; i--) {

        }
    }

    /**
     * 在堆里插入数字,每个数字只是向上看
     * @param arr
     * @param num
     */
    public static void heapInsert(int[] arr,int num,int heapSize){
        arr[heapSize]=num;

    }

    /**
     * 在第i个节点看此处是不是大根堆,进行堆结构的调整,每次检查节点是否需要下沉
     * @param arr
     * @param i
     */
//    public static void heapify(int[] arr,int i,int heapSize){
//        int left=i*2+1;
//        while (left<heapSize){
//            int large=left+1<heapSize && arr[left]<arr[left+1]?left+1:left;
//            large=arr[large]>arr[i]?large:i;
//            if(large==i){
//                swap(arr,i,large);
//            }
//        }
//    }

    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    //向下heapify的过程
    public static void heapifyDown(int[] arr,int index,int heapSize){
        int left=2*index+1;
        while (left<heapSize){
            left=left+1<heapSize && arr[left]<arr[left+1] ? left+1:left;
            if(arr[index]<arr[left]){
                swap(arr,index,left);
                index=left;
                left=2*left+1;
            }else{
                break;
            }
        }
    }

    public static void heapSort(int[] arr){
        if(arr==null || arr.length<2){
            return;
        }
        int heapSize=arr.length;
        //首先变成一个大根堆,这样每次第一个数都为最大值
        for (int i = arr.length - 1; i >= 0; i--) {
            heapifyDown(arr,i, heapSize);
//            while (arr[i]<arr[(i-1)/2]){
//                swap(arr,(i-1)/2,i);
//                i=(i-1)/2;
//            }
        }
        while (heapSize>0){
            swap(arr,0,--heapSize);
            heapifyDown(arr,0,heapSize);
        }
    }

    public static int[] getArr(){
        int maxLen=10;
        int maxVal=10;
        int[] arr=new int[(int)(Math.random()*maxLen)];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int)(Math.random()*maxVal);
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
        int times=10000;
        for (int i = 0; i < times; i++) {
            int[] arr = getArr();
            int[] arr2=Arrays.copyOf(arr,arr.length);
            heapSort(arr);
            if(!judge(arr)){
                System.out.println("出错了");
                System.out.println(Arrays.toString(arr));
                System.out.println(Arrays.toString(arr2));
                break;
            }
            System.out.println(i);
        }
        System.out.println("Nice!");
    }
}
