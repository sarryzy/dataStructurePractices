package com.zhangyu.datastructure.dataStructure0225;

public class powerOf3 {
    public static void main(String[] args){
        boolean right = isRight(3);
        System.out.println(right);
    }


    public static boolean isRight(int num){
        int base=1;
        while (base<=num){
            if(base==num){
                return true;
            }
            if(base>num/3){
                return false;
            }
            base*=3;
            if(base>num){
                return false;
            }
        }
        return false;
    }
}
