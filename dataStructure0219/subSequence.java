package com.zhangyu.datastructure.dataStructure0219;

import java.util.HashSet;

public class subSequence {
    public static void main(String[] args){
        getSubSequence1("abbcbb");
    }
    /**
     * ### 1.求出一个字符串的所有子序列.
     *
     * 思路:使用递归完成,每个位置上考虑有还是没有,直接向下执行.
     */
    public static void getSubSequence(String s){
        process(s,0,"");
    }

    public static void process(String s,int index,String cur){
        if(index==s.length()){
            System.out.println(cur);
            return;
        }
        String no=cur;
        process(s,index+1,no);
        String yes=no+s.substring(index,index+1);
        process(s,index+1,yes);
    }

    /**
     * ### 2.求出一个字符串的所有子序列,不能出现相同的字面值.
     *
     * 思路:通过一个哈希表来记录加入过的字符串.
     */
    public static void getSubSequence1(String s){
        HashSet<String> set=new HashSet<>();
        process1(s,0,"",set);
        System.out.println(set.size());
    }

    public static void process1(String s, int index, String cur, HashSet<String> set){
        if(index==s.length()){
            if(!set.contains(cur)){
                set.add(cur);
            }
            return;
        }
        String no=cur;
        process1(s,index+1,no,set);
        String yes=no+s.substring(index,index+1);
        process1(s,index+1,yes,set);
    }
}
