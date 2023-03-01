package com.zhangyu.datastructure.dataStructure0225;

public class differentDigit {
    public static int getNum(int x,int y){
        x=x^y;
        int count=0;
        while (x>0){
            if((x&1)==1){
                count++;
            }
            x>>=1;
        }
        return count;
    }
}
