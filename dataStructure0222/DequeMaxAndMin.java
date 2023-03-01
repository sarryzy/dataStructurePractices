package com.zhangyu.datastructure.dataStructure0222;

import java.util.LinkedList;

public class DequeMaxAndMin {
    public static void main(String[] args){
        int[] arr={1,2,3,4,5,6,7,8,9,10};
        int subCounts = getSubCounts(arr, 0);
        System.out.println(subCounts);
    }

    /**
     * ### 2.给定一个整型数组arr和一个整数num,
     * 某个arr中的子数组sub如果想达标必须要满足sub中最大值-sub中最小值<=num,返回arr中达标子数组的数量.
     *
     * 子数组:必须要连续.
     *
     * 思路:如果arr[l,r]达标,里面的任意一个子数组都达标.因为其子数组的最大值－最小值肯定会更小.
     *
     * 维持一个最大值的双端队列,维持一个最小值的双端队列,
     * 每次判断当前位置是否加入最大值或者加入最小值,
     * 如果到了某一个位置发现最大值减去最小值不满足要求,
     * 进行结算,以窗口的左端点为头,右端口的前一个为尾的全部子数列都满足要求.
     *
     * 然后让左窗口向右移动,更新最大值最小值队列,然后继续判断是否符合要求.
     */
    //子数列的最大值减去最小值必须要小于target
    public static int getSubCounts(int[] arr,int target){
        if(arr==null || arr.length<1){
            return 0;
        }
        int count=0;//总共的个数
        int left=0;//左边界的位置
        LinkedList<Integer> maxList=new LinkedList<>();
        LinkedList<Integer> minList=new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {//i为右边界的位置
            //先加进最大值和最小值
            while (!maxList.isEmpty() && arr[maxList.peekLast()]<=arr[i]){
                maxList.pollLast();
            }
            maxList.addLast(i);
            while (!minList.isEmpty() && arr[minList.peekLast()]>=arr[i]){
                minList.pollLast();
            }
            minList.addLast(i);
            //当前不符合要求,进行结算
            while(arr[maxList.peekFirst()]-arr[minList.peekFirst()]>target){
                count+=i-left;
                //结算后更新最大值和最小值表
                if(maxList.peekFirst()==left){
                    maxList.pollFirst();
                }
                if(minList.peekFirst()==left){
                    minList.pollFirst();
                }
                left++;
            }
        }
        return count+1;
    }

    public static void printArr(int[] arr,int i,int j){
        for(int k=i;k<j;k++){
            System.out.print(arr[k]+" ");
        }
    }
}
