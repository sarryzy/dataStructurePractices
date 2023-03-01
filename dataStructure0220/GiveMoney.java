package com.zhangyu.datastructure.dataStructure0220;

public class GiveMoney {
    public static void main(String[] args){
        int[] arr={1,2,3,4,5};
        int n = getN(arr, 10);
        int ndp = getNdp(arr, 10);
        int ndp1 = getNdp1(arr, 10);
        System.out.println(n);
        System.out.println(ndp);
        System.out.println(ndp1);
    }

    public static int getNdp(int[] arr,int target){
        //dp数组显然和当前的索引以及当前的钱数有关
        int[][] dp=new int[arr.length+1][target+1];
        dp[arr.length][0]=1;
        int index=arr.length-1;
        while (index>=0){
            for(int rest=0;rest<=target;rest++){
                int res=0;
                for(int num=0;num*arr[index]<=rest;num++){
                    res+=dp[index+1][rest-num*arr[index]];
                }
                dp[index][rest]=res;
            }
            index--;
        }
        return dp[0][target];


    }

    public static int getNdp1(int[] arr,int target){
        //dp数组显然和当前的索引以及当前的钱数有关
        int[][] dp=new int[arr.length+1][target+1];
        dp[arr.length][0]=1;
        int index=arr.length-1;
        while (index>=0){
            for(int rest=0;rest<=target;rest++){
                dp[index][rest]=dp[index+1][rest];
                if(rest-arr[index]>=0){
                    dp[index][rest]+=dp[index][rest-arr[index]];
                }
            }
            index--;
        }
        return dp[0][target];


    }

    /**
     * ### 4.给定指定的面额,求凑够指定金额的所有方法.
     *
     * 递归思路:从第一个位置开始,从0张到最大张数,每次去尝试.
     * @param arr
     * @param target
     * @return
     */
    public static int getN(int[] arr,int target){
        return process(arr,0,target);
    }

    /**
     *
     * @param arr
     * @param index 当前来到的数组的索引的位置
     * @param target 当前的目标的总金额数
     * @return
     */
    public static int process(int[] arr,int index,int target){
        if(target<0){
            return 0;
        }
        if(index==arr.length){
            return target==0?1:0;
        }
        int res=0;
        for(int num=0;num*arr[index]<=target;num++){
            res+=process(arr,index+1,target-num*arr[index]);
        }
        return res;
    }
}
