package com.zhangyu.datastructure.dataStructure0202;

import java.util.ArrayList;
import java.util.Arrays;

public class practice02 {
    public static void main(String[] args){
        int[] arr={1,2,3,4,5};
//        System.out.println(normalGetBig(arr));
//        System.out.println(getBig(arr));
//        System.out.println(Arrays.toString(arr));
        mergeBig(arr,0,1,arr.length-1);


    }

    public static void t1(){
        int maxLen=100;
        int maxVal=100000;
        int times=1000000;
        for (int i = 0; i < times; i++) {
            int[] arr=new int[(int)(maxLen*Math.random())];
            for (int j = 0; j < arr.length; j++) {
                arr[j]=(int)(Math.random()*maxVal);
            }
            if(getSmall(arr)!=getSmallNum(arr)){
                System.out.println(Arrays.toString(arr));
                System.out.println("出错了");
                break;
            }
            System.out.println(i);
        }
    }

    public long calArray(ArrayList<Integer> nums) {
        // write code here
        if(nums==null || nums.size()<2){
            return 0;
        }
        int[] arr=new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            arr[i]=nums.get(i);
        }
        return getSmallNum(arr);
    }

    public static long getSmallNum(int[] arr){
        if(arr==null || arr.length<2){
            return 0;
        }
        return process1(arr,0,arr.length-1);
    }

    public static long process1(int[] arr,int l,int r){
        if(l==r){
            return 0;
        }
        int m=l+((r-l)>>1);
        return process1(arr,l,m)+process1(arr,m+1,r)+merge1(arr,l,m,r);
    }

    public static long merge1(int[] arr,int l,int m,int r){
        int p1=l,p2=m+1;
        int[] help=new int[r-l+1];
        int i=0;
        long res=0;
        while (p1<=m && p2<=r){
            //相等时一定要左边先走
            res+=arr[p1]<=arr[p2]?  (r - p2 + 1) *arr[p1]:0;
            help[i++]=arr[p1]<=arr[p2]?arr[p1++]:arr[p2++];//当左边比右边小的时候才加上
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

    public static long getSmall(int[] arr){
        long res=0;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j]>=arr[i]){
                    res+=arr[i];
                }
            }
        }
        return res;
    }

    //使用非递归完成,计算一个数左边有多少个数比他大,把这些数加起来
    public static int getBig(int[] arr){
        if(arr==null || arr.length<2){
            return 0;
        }
        int res=0;
        int mergeSize=1;
        int n=arr.length;
        while (mergeSize< n){
            int l=0;
            while (l<n){
                int m=l+mergeSize-1;
                if(m>=n){
                    break;
                }
                int r=Math.min(m+mergeSize,n-1);
                res+=mergeBig(arr,l,m,r);
                l=r+1;
            }
            if(mergeSize>n/2){
                break;
            }
            mergeSize<<=1;
        }
        return res;
    }

    public static int normalGetBig(int[] arr){
        int res=0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i-1; j >=0; j--) {
                if(arr[j]>arr[i])
                    res+=arr[j];
            }
        }
        return res;
    }


    //必须要将左边多少个数比他大转化为右边有多少个数比他小
    //排序顺序为从大到小
    public static int mergeBig(int[] arr,int l,int m,int r){
        int[] help=new int[r-l+1];
        int i=0;
        int p1=l;
        int p2=m+1;
        int res=0;
        //相等先拷贝右边的
        while (p1<=m &&  p2<=r){
            if(arr[p1]>arr[p2]){
                res+=(r-p2+1)*arr[p1];
                help[i++]=arr[p1++];
            }else{
                help[i++]=arr[p2++];
            }
        }
        while (p1<=m){
            help[i++]=arr[p1++];
        }
        while (p2<=r){
            help[i++]=arr[p2++];
        }
        for (int h = 0; h < help.length; h++) {
            arr[l+h]=help[h];
        }
        return res;
    }

    public static void t2(){
        int maxLen=100;
        int maxVal=100000;
        int times=1000000;
        for (int i = 0; i < times; i++) {
            int[] arr=new int[(int)(maxLen*Math.random())];
            for (int j = 0; j < arr.length; j++) {
                arr[j]=(int)(Math.random()*maxVal);
            }
            if(normalGetBig(arr)!=getBig(arr)){
                System.out.println(Arrays.toString(arr));
                System.out.println("出错了");
                break;
            }
            System.out.println(i);
        }
    }
}
