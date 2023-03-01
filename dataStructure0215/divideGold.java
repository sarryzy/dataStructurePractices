package com.zhangyu.datastructure.dataStructure0215;

import java.util.PriorityQueue;

public class divideGold {
    public static void main(String[] args){
        int[] arr={20,10,30,40,50};
        int i = theMinValue1(arr);
        int i1 = theMinValue2(arr);
        System.out.println(i);
        System.out.println(i1);
    }
    /**
     * ### 4.金条划分问题,把一个金条划分为指定的长度,每次切割耗费的代价为当前金条的长度,求最小代价
     *
     * 简单做法:直接枚举,每次将两个进行合并,得到最小值返回.
     *
     * 复杂做法:利用一个小根堆,每次将最小的两个合并,这样非叶子节点加起来就是最小的总代价.
     */
    public static int theMinValue2(int[] arr){
        return process2(arr);
    }

    public static int process2(int[] arr){
        PriorityQueue<Integer> queue=new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }
        int res=0;
        while (queue.size()!=1){
            int num1=queue.poll();
            int num2=queue.poll();
            res+=num1+num2;
            queue.add(num1+num2);
        }
        return res;
    }

    //暴力递归方法
    public static int theMinValue1(int[] arr){
        return process1(arr,0);
    }

    /**
     *
     * @param arr 表示当前数组
     * @param res 表示当前的代价
     * @return
     */
    public static int process1(int[] arr,int res){
        if(arr.length==1){
            return res;
        }
        int min=Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                min=Math.min(min,process1(mergeThetwo(arr,i,j),res+arr[i]+arr[j]));
            }
        }
        return min;
    }

    //合并一个数组中的两个元素
    public static int[] mergeThetwo(int[] arr,int i,int j){
        int[] ans=new int[arr.length-1];
        int index=0;
        for (int k = 0; k < arr.length; k++) {
            if(k!=i && k!=j){
                ans[index++]=arr[k];
            }
        }
        ans[ans.length-1]=arr[i]+arr[j];
        return ans;
    }
}
