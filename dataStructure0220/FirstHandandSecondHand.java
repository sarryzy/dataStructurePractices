package com.zhangyu.datastructure.dataStructure0220;

public class FirstHandandSecondHand {
    public static void main(String[] args){
        int[] arr={1,2,3,4,5,6,7,8,9,10};
        int max1 = getMax1(arr);
        int max2 = getMax2(arr);
        System.out.println(max1);
        System.out.println(max2);
    }

    public static int getMax2(int[] arr){
        int[][] dp1=new int[arr.length][arr.length];//以l和r构建dp数组,f函数
        int[][] dp2=new int[arr.length][arr.length];//以l和r构建dp数组,s函数
        for (int i = 0; i < dp1.length; i++) {
            for (int j = 0; j < dp1.length; j++) {
                if(i==j){
                    dp1[i][j]=arr[i];
                }
            }
        }
        for(int i=1;i<arr.length;i++){
            int row=0;
            int col=i;
            while (row<arr.length && col<arr.length){
                dp1[row][col]=Math.max(dp2[row+1][col]+arr[row],arr[col]+dp2[row][col-1]);
                dp2[row][col]=Math.min(dp1[row+1][col],dp1[row][col-1]);
                row++;col++;
            }
        }
        return Math.max(dp1[0][arr.length-1],dp2[0][arr.length-1]);
    }



    public static int getMax1(int[] arr){
        return Math.max(f1(arr,0,arr.length-1),s1(arr,0,arr.length-1));
    }

    public static int f1(int[] arr,int l,int r){
        if(l==r){
            return arr[l];
        }
        return Math.max(arr[l]+s1(arr,l+1,r),arr[r]+s1(arr,l,r-1));
    }

    public static int s1(int[] arr,int l,int r){
        if(l==r){
            return 0;
        }
        return Math.min(f1(arr,l+1,r),f1(arr,l,r-1));
    }
}
