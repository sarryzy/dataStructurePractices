package com.zhangyu.datastructure.dataStructure0222;

import java.util.Arrays;

public class MatrixMultiply {
    public static void main(String[] args){
        int[][][][][][][][][][][][][][][][][][][][][][][] arr1=new int[10][10]
                [10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10];
        Arrays.fill(arr1,1);

    }

    public static void printArr(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     * 矩阵相乘
     */
    public static int[][] getMultiply(int[][] arr1,int[][] arr2){
        if(arr1[0].length!=arr2.length){
            throw new RuntimeException("不能相乘");
        }
        int[][] res=new int[arr1.length][arr2[0].length];
        for (int i = 0; i < res.length; i++) {//表示行
            for (int j = 0; j < res[0].length; j++) {
                for(int k=0;k<arr1[0].length;k++){
                    res[i][j]+=arr1[i][k]*arr2[k][j];
                }
            }
        }
        return res;
    }
}
