package com.zhangyu.datastructure.dataStructure0215;

import java.util.Arrays;
import java.util.Comparator;

public class practice01 {
    public static void main(String[] args){
        t1();
    }

    public static void t1(){
        //        Program p1=new Program(0,2);
//        Program p2=new Program(10,22);
//        Program p3=new Program(10,24);
//        Program p4=new Program(14,18);
//        Program p5=new Program(20,24);
        int times=100000;
        for (int i = 0; i < times; i++) {
            Program[] programs = generatePrograms(100);
            if(openProgram1(programs)!=openProgram2(programs)){
                System.out.println("出错了");
                break;
            }
            System.out.println(i);
        }
    }
    public static Program[] generatePrograms(int maxVal){
        int len=(int)(Math.random()*maxVal);
        Program[] programs=new Program[len];
        for (int i = 0; i < programs.length; i++) {
            int start,end;
            while (true){
                start=(int)(Math.random()*24);
                end=(int)(Math.random()*24);
                if(start<end){
                    break;
                }
            }
            Program p=new Program(start,end);
            programs[i]=p;
        }
        return programs;
    }



    public static class Program{
        int start;
        int end;
        public Program(int start,int end){
            this.start=start;
            this.end=end;
        }
    }

    /**
     * 利用会议结束的时间进行排序,越早结束越先排
     * @param programs
     * @return
     */
    public static int openProgram2(Program[] programs){
        if(programs==null || programs.length==0){
            return 0;
        }
        return process2(programs,0,0);

    }

    public static int process2(Program[] programs,int res,int time){
        Arrays.sort(programs, new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                return o1.end-o2.end;
            }
        });
        for (int i = 0; i < programs.length; i++) {
            if(programs[i].start>=time){
                res++;
                time=programs[i].end;
            }
        }
        return res;
    }

    /**
     * 纯暴力方法,直接递归,给定要开的会的列表
     * @return
     */
    public static int openProgram1(Program[] programs){
        if(programs==null || programs.length==0){
            return 0;
        }
        return process1(programs,0,0);
    }

    /**
     *
     * @param programs 剩余的开会的数量,等于0时直接返回
     * @param res 目前开会的数量
     * @param time 当前来到的 时间
     * @return
     */
    public static int process1(Program[] programs,int res,int time){
        if(programs.length==0){
            return res;
        }
        int max=res;
        for (int i = 0; i < programs.length; i++) {
            if(programs[i].start>=time){
                //可以开这个会
                Program[] p = help1(programs, i);
                max=Math.max(process1(p,res+1,programs[i].end),max);
            }
        }
        return max;
    }

    //拷贝数组,把i这个位置的去掉
    public static Program[] help1(Program[] programs,int i){
        Program[] p=new Program[programs.length-1];
        int k=0;
        for (int j = 0; j < programs.length; j++) {
            if(j==i){
                continue;
            }
            p[k++]=programs[j];
        }
        return p;
    }
}
