package com.zhangyu.datastructure.dataStructure0223;

public class MatrixFibonacci {
    public static void main(String[] args){
        int fibo9 = getFibo1(100);
        int fibo1 = getFibo2(100);
        System.out.println(fibo9);
        System.out.println(fibo1);
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
     * ### 1.实现矩阵乘法快速幂
     *
     * 思路:将幂次变为二进制数,然后从右向左相乘,例如10的9次方变为10的8次方乘10. 
     */
    public static int getFibo1(int n){
        //使用矩阵快速幂
        if(n==1 || n==2){
            return 1;
        }
        int[][] factorial = getFactorial(n-2);
        return factorial[0][0]+factorial[1][0];
    }

    /**
     * 计算矩阵的n次方
     * @param n
     * @return
     */
    public static int[][] getFactorial(int n){
        int[][] res=new int[2][2];
        for (int i = 0; i < res.length; i++) {
            res[i][i]=1;
        }
        int[][] matrix={{1,1},{1,0}};
        for(;n>0;n>>=1){
            if((n&1)==1){
                res=getMultiply(matrix,res);
                printArr(res);
            }
            matrix=getMultiply(matrix,matrix);
        }
        return res;
    }

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
    
    public static int getFibo2(int n){
        if(n==1||n==2){
            return 1;
        }
        return getFibo2(n-1)+getFibo2(n-2);
    }
}
