package com.zhangyu.datastructure.dataStructure0225;

public class trailingZero {
    public static void main(String[] args){
        int fac = getFac(12);
        System.out.println(fac);
    }


    public static int get0(int n){
        //相当于统计5的因子的个数,其中像25这样的,相当于提供了2个5
        int count=0;
        while (n>0){
            count+=n/5;
            n/=5;
        }
        return count;
    }

    public static int getFac(int n){
        if(n==1){
            return 1;
        }
        return getFac(n-1)*n;
    }


}
