package com.zhangyu.datastructure.dataStructure0222;

public class getSubAdditionmulMin {
    public static void main(String[] args){
        int[] arr={1,2,3,4,5};
        int min = getMin(arr);
        System.out.println(min);
    }

    /**
     * ### 4.给定一个只包含正整数的数组arr,求所有子数组中(sub累加和)*(sub中的最小值)是什么.
     *
     * 思路:划分值为数组中的当前值为当前的这个值,
     * 用单调栈找到以当前的数作为最小的值的最大的和,然后用和乘上当前的数,即可.
     */
    public static int getMin(int[] arr){
        int[][] nums = monoStack.getStack1(arr);
        int[] ans=new int[arr.length];
        int sum=0;
        ans[0]=arr[0];
        for (int i = 1; i < ans.length; i++) {
            ans[i]=ans[i-1]+arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            int l=nums[i][0];//左边比他小的,可能为-1
            int r=nums[i][1];
            if(l==-1 && r==-1){
                //左右都边没有比我小的
                sum=Math.max(sum,arr[i]*(ans[ans.length-1]));
            }else if(l == -1){
                sum=Math.max(sum,arr[i]*(ans[r-1]));
            }else if(r == -1){
                sum=Math.max(sum,arr[i]*(ans[ans.length-1]-ans[l]));
            }else{
                sum=Math.max(sum,arr[i]*(ans[r-1]-ans[l]));
            }
        }
        return sum;
    }
}
