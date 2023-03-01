package com.zhangyu.datastructure.dataStructure0301;

public class getTheLongestSumSubArray {
    public static void main(String[] args){
        int[] arr={100,2,1,1,1};
        int aLong = getLong(arr,4);
        System.out.println(aLong);
    }

    /**
     * ### 1.在数组中找到累加和为固定值的最长子数组.
     *
     * 思路:使用一个窗口数组,如果窗口内的总和小于指定的sum,
     * 右边界右扩.如果大于指定sum,左边界右扩.如果恰好相等就更新.
     * @return
     */
    public static int getLong(int[] arr,int target){
        int l=0;
        int r=0;
        int sum=0;
        int len=0;
        sum+=arr[0];
        while (l<arr.length && r<arr.length){
            System.out.println("l:"+l);
            System.out.println("r:"+r);
            if(sum==target){
                len=Math.max(len,r-l+1);
                sum-=arr[l++];
            }else if(sum<target){
                if(++r==arr.length){
                    break;
                }
                sum+=arr[r];
            }else{
                sum-=arr[l++];
            }
        }
        return len;
    }
}
