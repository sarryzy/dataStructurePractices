package com.zhangyu.datastructure.dataStructure0204;

import java.util.Arrays;

public class practice01 {
    public static void main(String[] args){
        for (int i = 0; i < 100000; i++) {
            int[] arr = arrGenerator();
            quickSort(arr,0,arr.length-1);
            if(!judge(arr)){
                System.out.println("出错了");
                System.out.println(Arrays.toString(arr));
                break;
            }
            System.out.println(i);
        }
        System.out.println("Nice!");
    }


    /**
     * 快速排序
     */
    public static void quickSort(int[] arr,int l,int r){
        if(arr==null || arr.length<2){
            return ;
        }
        process(arr,0,arr.length-1);
    }

    public static void process(int[] arr,int l,int r){
        if(l>=r){
            return;
        }

        int[] nums = netherlandsAns(arr, l, r);
        process(arr,l,nums[0]-1);//重点
        process(arr,nums[1]+1,r);
    }
    /**
     * 完成荷兰国旗问题的划分
     * 首先每次用数组的最后一个数作为划分值,将数组的左边界和右边界定义好,
     * i从第一个数开始,如果这个数比标准值要大,则与大于区的前一个数交换,大于区扩.如果小于,则与小于区的前一个交换,小于区右扩,如果相等直接++
     *
     */
    public static int[] netherlandsAns(int[] arr,int l,int r){
//        if(l>r){
//            return new int[]{-1,-1};
//        }else if(l==r){
//            return new int[]{l,r};
//        }
        /**
         * 优化快排,即每次用一个随机的数来进行
         */
        swap(arr,(int)(Math.random()*(r-l+1)+l),r);
        int left=l-1;
        int right=r;
        int i=l;
        while (i<right){
            if(arr[i]>arr[r]){
                swap(arr,i,--right);
            }else if(arr[i]<arr[r]){
                swap(arr,i++,++left);
            }else{
                i++;
            }
        }
        swap(arr,r,right);
//        System.out.println(Arrays.toString(new int[]{left+1,right}));
        return new int[]{left+1,right};
    }

    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    /**
     * 对数器,随机数组生成器
     */
    public static int[] arrGenerator(){
        int maxLen=10000;
        int maxVal=10000;
        int[] arr=new int[(int)(Math.random()*maxLen)];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int)(Math.random()*maxVal);
        }
        return arr;
    }

    public static boolean judge(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            if(arr[i]>arr[i+1]){
                return false;
            }
        }
        return true;
    }


}
