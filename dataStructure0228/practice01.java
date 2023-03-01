package com.zhangyu.datastructure.dataStructure0228;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;

public class practice01 {
    public static void main(String[] args){
        t1();
        t2();
    }
    
    public static void t1(){
        int times=100000;
        int addOrPollTimes=100000;
        long start=System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            ArrayDeque<Integer> queue1=new ArrayDeque<>();
            for (int j = 0; j < addOrPollTimes; j++) {
                if(Math.random()<0.5){
                    queue1.add((int)(Math.random()*1000));
                }else{
                    if(!queue1.isEmpty()){
                        queue1.poll();
                    }
                }
            }
        }
        long end=System.currentTimeMillis();
        System.out.println("t1:"+(end-start));
    }

    public static void t2(){
        int times=100000;
        int addOrPollTimes=100000;
        long start=System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            LinkedList<Integer> queue2=new LinkedList<>();
            for (int j = 0; j < addOrPollTimes; j++) {
                if(Math.random()<0.5){
                    queue2.add((int)(Math.random()*1000));
                }else{
                    if(!queue2.isEmpty()){
                        queue2.poll();
                    }
                }
            }
        }
        long end=System.currentTimeMillis();
        System.out.println("t2:"+(end-start));
    }
    
    
}
