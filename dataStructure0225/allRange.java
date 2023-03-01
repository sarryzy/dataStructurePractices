package com.zhangyu.datastructure.dataStructure0225;

import java.util.*;

public class allRange {
    public static void main(String[] args){
        ArrayList<Integer> list=new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

    }

    /**
     * 全排列,思路:每次决定当前数字要不要,用一个索引表示来到的位置,每次看看当前数字要不要
     */
    public static List<List<Integer>> permute1(int[] arr){
        List<List<Integer>> bigList=new ArrayList<>();
        List<Integer> list=new ArrayList<>();
        for (int i : arr) {
            list.add(i);
        }
        process1(arr,0,bigList,list);
        System.out.println(bigList);
        return bigList;
    }

    public static void process1(int[] arr,int index,List<List<Integer>> bigList,List<Integer> list){
        //每次决定当前位置要不要
        if(arr.length==index){
            bigList.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i <arr.length; i++) {
            Collections.swap(list,i,index);
            process1(arr,index+1,bigList,list);
            Collections.swap(list,i,index);
        }
    }

    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
