package com.zhangyu.datastructure.dataStructure0225;

import java.util.HashSet;

public class happyNumber {
    public static void main(String[] args){
        boolean happy = happy(2);
        System.out.println(happy);
    }

    public static boolean happy(int num){
        //判断一个数是不是快乐数
        int n=getSum(num);
        HashSet<Integer> set=new HashSet<>();
        while (n!=1){
            set.add(n);
            n=getSum(n);
            if(set.contains(n)){
                return false;
            }
        }
        return true;
    }

    public static int getSum(int num){
        //得到一个数每个位置上的平方和
        int sum=0;
        while (num>0){
            int i=(num%10);
            sum+=i*i;
            num/=10;
        }
        return sum;
    }
}
