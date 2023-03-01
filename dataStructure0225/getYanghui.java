package com.zhangyu.datastructure.dataStructure0225;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class getYanghui {
    public static void main(String[] args){
        List<Integer> row = getRow(5);
    }

    public static List<Integer> getRow(int n){
        List<Integer> list=new ArrayList<>();
        int[] arr=new int[n+1];
        arr[0]=1;
        for (int i = 0; i <=n; i++) {
            arr[0]=1;
            arr[i]=1;
            for(int j=i-1;j>0;j--){
                arr[j]=arr[j-1]+arr[j];
            }
        }
        for (int j : arr) {
            list.add(j);
        }
        return list;
    }
}
