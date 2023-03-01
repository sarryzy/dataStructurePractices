package com.zhangyu.datastructure.dataStructure0226;

public class findSquare {
    public static void main(String[] args){
        int[][] arr={{1,2},{3,4}};
        rotate1(arr);
        printArr(arr);
    }

    public static void printArr(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    //思路:每次找到离根号n的下界的最近的数的平方
    public static void rotate1(int[][] arr){
        //原地旋转,首先旋转最外层
        int x1=0,y1=0,x2=arr.length-1,y2=arr[0].length-1;
        while (x1<x2 && y1<y2) {
            for (int i = 0; i < x2-x1; i++) {
                int temp=arr[x1][y1+i];
                arr[x1][y1+i]=arr[x2-i][y1];
                arr[x2-i][y1]=arr[x2][y2-i];
                arr[x2][y2-i]=arr[x1+i][y2];
                arr[x1+i][y2]=temp;
            }
            x1++;x2--;y1++;y2--;
        }
    }
}
