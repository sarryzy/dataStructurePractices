package com.zhangyu.datastructure.dataStructure0223;

import java.util.Arrays;

public class sevenToTen {
    public static void main(String[] args){
        int[] arr=new int[10];
        for(int i=0;i<1000000;i++){
            arr[f2()-1]++;
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 用1到7等概率生成1到10等概率
     */
    //等概率生成1到7
    public static int f(){
        return (int)(Math.random()*7)+1;
    }

    //首先返回0,1等概率
    public static int f1(){
//        int num=f();
//        while (num==4){
//            num=f();
//        }
//        return num<4?0:1;

        int num;
        do{
            num=f();
        }while (num==4);
        return num<4?0:1;
    }

    //1到10等概率
    public static int f2(){
        int num=0;
        while (!(num>=1&&num<=10)){
            num=0;
            for(int i=0;i<4;i++){
                num+=(f1()<<i);
            }
        }
        return num;
    }
}
