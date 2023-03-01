package com.zhangyu.datastructure.dataStructure0221;

public class WashCoffee {
    public static void main(String[] args){
        int[] arr=new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=i;
        }
//        int washCoffeeTime1 = getWashCoffeeTime1(arr, 3, 10, 0);
        int washCoffeeTime2 = getWashCoffeeTime2(arr, 3, 10, 0);
//        System.out.println(washCoffeeTime1);
        System.out.println(washCoffeeTime2);
    }

    /**
     * ### 3.给定一个数组,代表每个人喝完咖啡准备刷杯子的时间.
     * 只有一台咖啡机一次只能洗一个杯子耗费时间a,也可以自己挥发干净耗费时间b.
     * 咖啡杯可以并行挥发,求最早完成时间.
     *
     * 思路:每次传入递归函数一个洗咖啡机能够空余的时间,然后比较当前杯子洗或者不洗的最小值即可.
     */
    public static int getWashCoffeeTime1(int[] arr,int a,int b,int washTime){
        return process1(arr,a,b,0,washTime);
    }

    public static int process1(int[] arr,int a,int b,int index,int washTime){
        if(index==arr.length-1){
            //如果来到了最后一个咖啡机,有两种情况
            //一种是放到咖啡机中洗
            int time1=Math.max(arr[index],washTime)+a;
            //另一种是自然挥发
            int time2=arr[index]+b;
            //返回最小的
            return Math.min(time1,time2);
        }
        //考虑当前的咖啡放到咖啡机中洗的情况
        int time1=Math.max(arr[index],washTime)+a;
        int time2=process1(arr,a,b,index+1,time1);
        int p1=Math.max(time1,time2);

        //自然挥发
        int time3=arr[index]+b;
        int time4=process1(arr,a,b,index+1,washTime);
        int p2=Math.max(time3,time4);

        return Math.min(p1,p2);
    }

    public static int getWashCoffeeTime2(int[] arr,int a,int b,int washTime){
        return process2(arr,a,b,washTime);
    }

    public static int process2(int[] arr,int a,int b,int washTime){
        int n=arr.length;
        if(a>=b){
            return arr[n-1]+b;
        }
        int limit=0;
        for(int i=0;i<n;i++){
            limit=Math.max(limit,arr[i])+a;
        }
        int[][] dp=new int[n][limit+1];

        //先填最后一行
        for(int i=0;i<dp[0].length;i++){
            int time1=Math.max(arr[n-1],i)+a;
            int time2=arr[n-1]+b;
            dp[n-1][i]= Math.min(time1,time2);
        }
        for(int index=n-2;index>=0;index--){
            for(int i=0;i<dp[0].length;i++){
                int time1=Math.max(arr[index],i)+a;
                int p1=Integer.MAX_VALUE;
                if(time1<=limit){
                    int time2=dp[index+1][time1];
                    p1=Math.max(time1,time2);
                }
                int time3=arr[index]+b;
                int time4=dp[index+1][i];
                int p2=Math.max(time3,time4);
                dp[index][i]=Math.min(p1,p2);
            }
        }
        return dp[0][0];
    }
}
