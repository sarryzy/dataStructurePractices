package com.zhangyu.datastructure.dataStructure0225;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class subSeuqu {
    public static void main(String[] args){
        int[] arr={1,2,3};
        subsets(arr);
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> bigList=new ArrayList<>();
        List<Integer> list=new ArrayList<>();
        process(nums,list,0,bigList);
        System.out.println(bigList);
        return bigList;
    }

    public static void process(int[] nums,List<Integer> list,int index,List<List<Integer>> bigList){
        if(index==nums.length){
            bigList.add(new ArrayList<>(list));
            list.removeAll(list);
            return;
        }
        process(nums,list,index+1,bigList);
        list.add(nums[index]);
        process(nums,list,index+1,bigList);
    }
}
