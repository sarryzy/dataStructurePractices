package com.zhangyu.datastructure.dataStructure0301;

import java.util.Arrays;
import java.util.HashMap;

public class getTheLongest {
    public static void main(String[] args){
        t1();
    }

    public static int getLong(int[] arr,int k){
        int sum=0;
        int len=0;
        HashMap<Integer,Integer> map=new HashMap<>();//记录前多少个数的和
//        map.put(0,-1);//出现前缀和为0的位置在-1
        for (int i = 0; i < arr.length; i++) {
            sum+=arr[i];
            if(sum==k){
                len=Math.max(len,i+1);
//                continue;
            }
            if(map.containsKey(sum-k)){
                len=Math.max(len,i-map.get(sum-k));
            }
            if(!map.containsKey(sum)){
                map.put(sum,i);
            }
        }
//        System.out.println("map:"+map);
        return len;
    }

    public static int[] generateArr(int max,int len){
        int[] arr=new int[(int)(Math.random()*len+1)];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int)(Math.random()*max)-(int)(Math.random()*max);
        }
        return arr;
    }

    public static int getLong1(int[] arr,int k){
        int len=0;
        int sum=0;
        for (int i = 0; i < arr.length; i++) {
            sum=0;//以i为头部
            for (int j = i; j < arr.length; j++) {
                sum+=arr[j];
                if(sum==k){
                    len=Math.max(len,j-i+1);
                }
            }
        }
        return len;
    }

    public static void t1(){
        int testTimes=1000000;
        int max=10;
        int len=10;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateArr(max, len);
            int k=(int)(Math.random()*max-Math.random()*max);
            if(getLong(arr,k)!=getLong1(arr,k)){
                System.out.println("错戳了");
                System.out.println(Arrays.toString(arr));
                System.out.println("k:"+k);
                System.out.println(getLong(arr,k));
                System.out.println(getLong1(arr,k));
                break;
            }
            System.out.println(i);
        }
    }
}
