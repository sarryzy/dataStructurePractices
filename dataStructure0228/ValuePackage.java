package com.zhangyu.datastructure.dataStructure0228;

import java.util.*;

public class ValuePackage {
    public static List<List<Integer>> getPackage(int[][] term1,int[][] term2){
        TreeMap<Integer,Integer> map=new TreeMap<>();
        for (int[] arr : term1) {
            if(!map.containsKey(arr[0])){
                map.put(arr[0],arr[1]);
            }else{
                map.put(arr[0],map.get(arr[0])+arr[1]);
            }
        }
        for (int[] arr : term2) {
            if(!map.containsKey(arr[0])){
                map.put(arr[0],arr[1]);
            }else{
                map.put(arr[0],map.get(arr[0])+arr[1]);
            }
        }
        List<List<Integer>> ans=new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ans.add(new ArrayList<>(Arrays.asList(entry.getKey(),entry.getValue())));
        }
        return ans;
    }
}
