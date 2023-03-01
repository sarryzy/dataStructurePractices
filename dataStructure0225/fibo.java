package com.zhangyu.datastructure.dataStructure0225;

public class fibo {
    public static void main(String[] args){
        int fibo = getFibo(5);
    }

    public static int getFibo(int n){
        if(n==1 || n==2){
            return 1;
        }
        int[][] arr={{1,1},{1,0}};
        //现在需要得到arr的n-2次方
        int process = process(arr, n - 2);
        System.out.println(process);
        return process;
    }

    public static int process(int[][] arr,int n){
        //利用快速幂计算arr的n次方
        int[][] res={{1,0},{0,1}};
        while (n>0){
            if((n&1)==1){
                res=multiplyMatrix(res,arr);
            }
            arr=multiplyMatrix(arr,arr);
            n>>=1;
        }
        return res[0][0]+res[1][0];
    }

    public static int[][] multiplyMatrix(int[][] A,int[][] B){
        int[][] res=new int[A.length][A[0].length];
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A[0].length;j++){
                for(int k=0;k<A.length;k++){
                    res[i][j]+=A[i][k]*B[k][j];
                }
            }
        }
        return res;
    }

    public static void printArr(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
}
