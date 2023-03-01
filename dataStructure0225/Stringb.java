package com.zhangyu.datastructure.dataStructure0225;

public class Stringb {

    public static int Stringb2(int n){
        int res=0;
        for (int i = 0; i < 32; i++) {
            res=res|(n>>>(31-i));
            n=(n<<1)&Integer.MIN_VALUE;
        }
        return res;
    }

    //位运算分治,思路,每两位交换,每4位交换,每8位交换
    public static int divideAndRule1(int n){
        int M1=0x55555555;
        int M2=0x33333333;
        int M4=0x0f0f0f0f;
        int M8=0x0000ffff;
        n=(n>>>1)&M1|((n&M1)<<1);
        n=(n>>>1)&M2|((n&M2)<<1);
        n=(n>>>1)&M4|((n&M4)<<1);
        n=(n>>>1)&M8|((n&M8)<<1);
        return (n<<16)|(n>>>16);
    }
}
