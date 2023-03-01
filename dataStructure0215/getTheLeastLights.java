package com.zhangyu.datastructure.dataStructure0215;

import java.util.HashSet;

public class getTheLeastLights {
    public static void main(String[] args){
        String s="...XXX.............";
        int theLeastLights1 = getTheLeastLights1(s);
        int theLeastLights2 = getTheLeastLights2(s);
        long start = System.currentTimeMillis();
        System.out.println(theLeastLights1);
        long end = System.currentTimeMillis();
        System.out.println("1:"+(end-start));
        start = System.currentTimeMillis();
        System.out.println(theLeastLights2);
        end = System.currentTimeMillis();
        System.out.println("2:"+(end-start));


    }

    /**
     * ### 3.放灯问题,把空余的位置照亮需要多少灯.
     *
     * 简单方法:暴力,每个点都去尝试两种可能.
     *
     * 最优解:每次考虑当前位置应该放置哪一个值,如果当前为X,直接跳到下一个,
     * 如果当前为点则考虑后续情况,如果后续为X直接在这里放一个灯.如果后面两个都为点,则在中间放灯.
     * @param s
     * @return
     */
    public static int getTheLeastLights2(String s){
        char[] chars = s.toCharArray();
        int res=0;
        int index=0;
        while (index<chars.length){
            if(chars[index]=='X'){
                index++;
            }else{
                //为.
                if(index+1>=chars.length){
                    res++;
                    break;
                }
                if(chars[index+1]=='X'){
                    res++;
                    index=index+2;
                }else if(chars[index+1]=='.'){
                    res++;
                    index=index+3;
                }
            }
        }
        return res;
    }
    /**
     * ### 3.放灯问题,把空余的位置照亮需要多少灯.
     *
     * 简单方法:暴力,每个点都去尝试两种可能.
     */
    public static int getTheLeastLights1(String s){
        char[] chars = s.toCharArray();
        return process1(chars,0,new HashSet<>());
    }

    /**
     *
     * @param chars 表示给定的数组
     * @param num   表示当前的索引位置
     * @param set   当前存放了灯的位置
     * @return
     */
    public static int process1(char[] chars, int num, HashSet<Integer> set){
        if(num==chars.length){
            //到达最后一个开始清算,检查放置的结果是否正确
            for (int i = 0; i < chars.length; i++) {
                if(chars[i]!='X'){//这个位置需要查看有没有被照亮
                    if(!(set.contains(i-1)||set.contains(i) ||set.contains(i+1))){
                        //没有被照亮
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return set.size();
        }else{
            int no=process1(chars,num+1,set);//表示当前位置不放灯
            int yes=Integer.MAX_VALUE;
            if(chars[num]=='.'){
                //放灯
                set.add(num);
                yes=process1(chars,num+1,set);
                set.remove(num);
            }
            return Math.min(yes,no);
        }
    }
}
