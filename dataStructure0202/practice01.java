package com.zhangyu.datastructure.dataStructure0202;

import java.util.Arrays;

public class practice01 {
    public static void main(String[] args){
//        int[] arr={1,3,5,2,4,6};
//        System.out.println(getSmall(arr));
//        int smallNum = getSmallNum(arr);
//        System.out.println(Arrays.toString(arr));
//        System.out.println(smallNum);
        t1();

    }

    /**
     * 归并排序
     */
    public static void process(int[] arr,int l,int r){
        if(l==r){
            return;
        }
        int m=l+(r-l)/2;
        process(arr,l,m);
        process(arr,m+1,r);
        merge(arr,l,m,r);
    }

    public static void merge(int[] arr,int l,int m,int r){
        int[] help=new int[r-l+1];
        int k=0;
        int i=l;
        int j=m+1;
        while(i<=m && j<=r){
            help[k++]=arr[i]<=arr[j]?arr[i++]:arr[j++];
        }
        while(i<=m){
            help[k++]=arr[i++];
        }
        while(j<=r){
            help[k++]=arr[j++];
        }
        for (int a = 0; a < help.length; a++) {
            arr[l+a]=help[a];
        }
    }

    /**
     * 非递归版本的归并排序
     * 首先按照每1个sort一次,然后再每两个sort一次,以此类推
     * @param arr
     * @param l
     * @param r
     */
    public static void mergesort(int[] arr,int l,int r){
        if(arr==null || arr.length<2){
            return ;
        }
        int mergeSize=1;
        int n=arr.length;
        while (mergeSize<n){
            int left=0;//标志从左边开始
            while (left<n){
                int mid=left+mergeSize-1;
                if(mid>=n-1){
                    break;
                }
                int right=Math.min(mid+mergeSize,n-1);
                merge(arr,left,mid,right);
                left=right+1;
            }
            if(mergeSize>=n/2){
                break;
            }
            mergeSize<<=1;
        }
    }

    /**
     * 计算小和问题
     */
    public static int getSmallNum(int[] arr){
        if(arr==null || arr.length<2){
            return 0;
        }
        return process1(arr,0,arr.length-1);
    }

    public static int process1(int[] arr,int l,int r){
        if(l==r){
            return 0;
        }
        int m=l+((r-l)>>1);
        return process1(arr,l,m)+process1(arr,m+1,r)+merge1(arr,l,m,r);
    }

    public static int merge1(int[] arr,int l,int m,int r){
        int p1=l,p2=m+1;
        int[] help=new int[r-l+1];
        int i=0;
        int res=0;
        while (p1<=m && p2<=r){
            res+=arr[p1]<arr[p2]?(r-p2+1)*arr[p1]:0;
            help[i++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];//当左边比右边小的时候才加上
        }
        while(p1<=m){
            help[i++]=arr[p1++];
        }
        while(p2<=r){
            help[i++]=arr[p2++];
        }
        for (int i1 = 0; i1 < help.length; i1++) {
            arr[l+i1]=help[i1];
        }
        return res;
    }

    //测试小和的对数器
    public static void t1(){
        int maxLen=100;
        int maxVal=100000;
        int times=1000000;
        for (int i = 0; i < times; i++) {
            int[] arr=new int[(int)(maxLen*Math.random())];
            for (int j = 0; j < arr.length; j++) {
                arr[j]=(int)(Math.random()*maxVal);
            }
            if(getSmall(arr)!=(long)getSmallNum(arr)){
                System.out.println(Arrays.toString(arr));
                System.out.println("出错了");
                break;
            }
            System.out.println(i);
        }
    }

    public static long getSmall(int[] arr){
        long res=0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if(arr[j]>arr[i]){
                    res+=arr[i];
                }
            }
        }
        return res;
    }
}
