package com.zhangyu.datastructure.dataStructure0225;

public class TheLostNumberK {
    public static void main(String[] args){

    }

    public static int getNumber(int[] arr,int k){
        for (int j : arr) {
            if (j <= k) {
                k++;
            } else {
                break;
            }
        }
        return k;
    }
}
