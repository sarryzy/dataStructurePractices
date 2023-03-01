package com.zhangyu.datastructure.dataStructure0202;

import java.util.Arrays;

public class practice03 {

    public static void main(String[] args){
        int[] arr={14,15,16,22,53,60};
        int[] ints = twoSum1(arr, 76);
        System.out.println(Arrays.toString(ints));
    }

    //两数之和
    public static int[] twoSum1(int[] nums, int target) {
        int[] arr=new int[2];
        int n=nums.length;
        for (int i = 0; i < nums.length-1; i++) {
            arr[0]=nums[i];
            int tar=target-arr[0];
            int l=i+1;
            int r=n-1;
            while (l<=r){
                int m=l+(r-l)/2;
                if(nums[m]==tar){
                    arr[1]=nums[m];
                    return arr;
                }else if(nums[m]<tar){
                    l=m+1;
                }else{
                    r=m-1;
                }
            }
        }
        return arr;
    }

    public static int[] twoSum(int[] arr,int target){
        int i=0;
        int j=arr.length-1;
        while (true){
            int res=arr[i]+arr[j];
            if(res==target){
                return new int[]{arr[i],arr[j]};
            }else if(res<target){
                i++;
            }else{
                j--;
            }
        }
    }
}
