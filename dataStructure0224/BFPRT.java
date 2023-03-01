package com.zhangyu.datastructure.dataStructure0224;

import java.util.Arrays;

public class BFPRT {
    public static void main(String[] args){
        int[] arr={2,3,34,2,1,3,5,6,4,3,2,4,6,7,7,8,9,9,7,6,45,3,3,2,1,1,2,1,4,4,3,43,42,3231,1212};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        int numberK1 = getNumberK1(arr, 5);
        int numberK2 = getNumberK2(arr, 5);
        System.out.println(numberK1);
        System.out.println(numberK2);
    }

    /**
     * 简单思路:利用荷兰国旗问题,如果划分值左边的数个数少,则在右边继续进行划分.
     */
    public static int getNumberK1(int[] arr,int k){
        return process1(arr,0,arr.length-1,k);
    }

    //划分数组
    public static int process1(int[] arr,int l,int r,int k){
        int index=(int)(Math.random()*(r-l+1))+l;
        int[] nums = partition(arr, arr[index]);
        if(nums[0]<=k-1 && nums[1]>=k-1){
            return arr[nums[0]];
        }else if(nums[0]>k-1){
            return process1(arr,l,nums[0]-1,k);
        }else{
            return process1(arr,nums[1]+1,r,k);
        }
    }

    //需要保证这个值在数组中存在才有效
    public static int[] partition(int[] arr,int pivot){
        //以pivot进行划分
        int left=-1;
        int right=arr.length;
        int index=0;
        while (index<right){
            if(arr[index]<pivot){
                swap(arr,index++,++left);
            }else if(arr[index]>pivot){
                swap(arr,index,--right);
            }else{
                index++;
            }
        }
        return new int[]{left+1,right-1};
    }

    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    //在i到j上进行插入排序
    //插入排序,首先确定第一个数,再看第i个数,如果这个数字比他前面的数字小,则交换这两个数
    public static void insertSort(int[] arr,int i,int j){
//        for (int p1 = i+1; p1 <=j; p1++) {
//            int p2=p1;
//            while (p2>0 && arr[p2]<arr[p2-1]){
//                swap(arr,p2,p2-1);
//                p2--;
//            }
//        }
        for (int p1 = i+1; p1 <=j; p1++) {
            for(int p2=p1;p2>0 && arr[p2]<arr[p2-1];p2--){
                swap(arr,p2,p2-1);
            }
        }
    }

    //主函数入口
    public static int getNumberK2(int[] arr,int k){
        return bfprt(arr,0,arr.length-1,k);
    }

    //数组对给定值进行划分,返回l到r上的第k个数是多少
    public static int bfprt(int[] arr,int l,int r,int k){
        if(l==r){
            return arr[l];
        }
        //使用process2得到划分值
        int index = process2(arr, l, r);
        //剩下的和荷兰国旗问题一样

        int[] part = partition(arr, index);

        if(part[0]<=k && part[1]>=k){
            return arr[part[0]];
        }else if(part[0]>k){
            //左边过大
            return bfprt(arr,l,part[0]-1,k);
        }else{
            return bfprt(arr,part[1]+1,r,k);
        }
    }

    //求得指定的bfprt的数字
    public static int process2(int[] arr,int l,int r){
        //首先确定有多少组
        int size=r-l+1;
        int[] res=new int[size/5+((size%5)==0?0:1)];
        for (int i = 0; i < res.length; i++) {
            int left=l+5*i;
            int right=Math.min(left+4,r);
            res[i]=getMiddle(arr,left,right);
        }
        //返回res数组的中间的值
        return bfprt(res,0,res.length-1,res.length/2);
    }

    //得到i和j排序后的值
    public static int getMiddle(int[] arr,int i,int j){
        insertSort(arr,i,j);
        return arr[(i+j)/2];
    }
}
