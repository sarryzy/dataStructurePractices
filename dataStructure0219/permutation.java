package com.zhangyu.datastructure.dataStructure0219;

import java.util.Arrays;
import java.util.HashSet;

public class permutation {

    public static void main(String[] args){
        String s="abcdegf";
        int permutationNumber1 = getPermutationNumber1(s);
        int permutationNumber2 = getPermutationNumber2(s);
        System.out.println(permutationNumber1);
        System.out.println(permutationNumber2);
        char[] arr={'a','b','c'};
        System.out.println(Arrays.toString(arr));
    }

    public static void getPermutation(String s){
        process(s.toCharArray(),0);
    }

    public static void process(char[] s,int index){
        if(index==s.length){
            System.out.println(Arrays.toString(s));
            return;
        }
        for(int j=index;j<s.length;j++){
            swap(s,index,j);
            process(s,index+1);
            swap(s,index,j);
        }
    }

    /**
     * ### 5.打印一个字符串的全部排列,要求不重复.
     *
     * 简单方法:使用hashset
     *
     * 复杂方法:在每一个位置上考虑开头的时候,使用一个序列来表示有没有有没有用这个开头的,如果没有就进行.
     * @param s
     * @return
     */
    public static int getPermutationNumber1(String s){
        HashSet<String> set=new HashSet<>();
        process1(s.toCharArray(),0,set);
        return set.size();
    }

    public static void process1(char[] s, int i, HashSet<String> set){
        if(i==s.length){
            set.add(Arrays.toString(s));
            return;
        }
        for(int j=i;j<s.length;j++){
            swap(s,i,j);
            process1(s,i+1,set);
            swap(s,i,j);
        }
    }

    static int count=0;
    public static int getPermutationNumber2(String s){
        process2(s.toCharArray(),0);
        return count;
    }

    public static void process2(char[] s, int i){
        if(i==s.length){
            count++;
            return;
        }
        boolean[] check=new boolean[26];
        for(int j=i;j<s.length;j++){
            if(!check[s[j]-'a']){
                check[s[j]-'a']=true;
                swap(s,i,j);
                process2(s,i+1);
                swap(s,i,j);
            }
        }
    }

    public static void swap(char[] s,int i,int j){
        char temp=s[i];
        s[i]=s[j];
        s[j]=temp;
    }
}
