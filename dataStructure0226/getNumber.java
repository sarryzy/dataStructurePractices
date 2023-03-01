package com.zhangyu.datastructure.dataStructure0226;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class getNumber {
    public static void main(String[] args){
        int[] arr={10,2};
        System.out.println(getMax(arr));

    }

    public static String getMax(int[] arr){
        ArrayList<Integer> list=new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return com(o1,o2);
            }
        });
        StringBuilder sb=new StringBuilder();
        for (Integer integer : list) {
            sb.append(integer);
        }
        if(sb.charAt(0)=='0'){
            return 0+"";
        }
        return sb.toString();
    }

    public static int com(int o1,int o2){
        String s1=o1+""+o2;
        String s2=o2+""+o1;
        for (int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i)<s2.charAt(i)){
                return 1;
            }else if(s1.charAt(i)>s2.charAt(i)){
                return -1;
            }
        }
        return 0;
    }
}
