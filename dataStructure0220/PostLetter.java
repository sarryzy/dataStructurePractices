package com.zhangyu.datastructure.dataStructure0220;

import java.util.Arrays;

public class PostLetter {
    public static void main(String[] args){
        int number1 = getNumber1(10);
        int number2 = getNumber2(10);
        System.out.println(number1);
        System.out.println(number2);
    }

    /**
     * ### 1.寄信问题.规定每个人必须要寄出一封信,并且不能寄给自己.求有多少种方法.
     *
     * 思路:首先得出base case,即n为0,1,2的情况,
     * 然后当人数为n的时候,假设A把信寄给了B,B寄给了A,
     * 这相当于就变成了f(n-2)的问题,如果B不寄给A,
     * 相当于A和B就变成了同一个人,就变成了f(n-1)的问题.这样的情况总共有n-1种,因此
     *
     * f(n)=(n-1)*[f(n-1)+f(n-2)]
     */
    public static int getNumber2(int n){
        return process2(n);
    }

    public static int process2(int n){
        if(n==1){
            return 0;
        }else if(n==2){
            return 1;
        }
        return (n-1)*(process2(n-1)+process2(n-2));
    }



    //先用暴力方法尝试一遍,构造一个长度为n的数组进行全排列,如果有位置与当前相同的则返回false
    static int count=0;
    public static int getNumber1(int n){
        int[] arr=new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        process1(arr,n,0);
        return count;
    }

    public static void process1(int[] arr,int n,int i){
        //每次交换arr各位上的值
        if(i==n){
            if(check(arr)){
                count++;
//                System.out.println(Arrays.toString(arr));
                return;
            }
        }
        for(int j=i;j<arr.length;j++){
            swap(arr,i,j);
            process1(arr,n,i+1);
            swap(arr,i,j);
        }
    }

    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public static boolean check(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==i){
                return false;
            }
        }
        return true;
    }
}
