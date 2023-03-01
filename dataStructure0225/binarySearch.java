package com.zhangyu.datastructure.dataStructure0225;

public class binarySearch {
    public static void main(String[] args){

    }

    public static int sear(int[] arr,int target){
        int l=0;
        int r=arr.length-1;
        while (l<=r){
            int mid=l+(r-l)/2;
            if(arr[mid]==target){
                return mid;
            }else if(arr[mid]>target){
                r=mid-1;
            }else{
                l=mid+1;
            }
        }
        return 1;
    }
}
