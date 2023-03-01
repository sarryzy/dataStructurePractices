package com.zhangyu.datastructure.dataStructure0217;

public class Hanoi {

    public static void main(String[] args){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            System.out.println("move "+i+" from "+" to ");
        }
        long end = System.currentTimeMillis();
        System.out.println((end-start));
    }

    public static void hanoi(int n){
        process(n,"left","right","mid");
        System.out.println(count);
    }

    /**
     *
     * @param n 为当前需要移动的个数
     * @param from
     * @param to
     * @param help 帮助杆
     */
    static int count=0;//打印一次加一次
    //把n个从左移动右边相当于先把n-1个移到中间,再把一个移到右边
    public static void process(int n,String from,String to,String help){
        if(n==1){
            count++;
//            System.out.println("move 1 from "+from+" to "+to);
            return;
        }
        process(n-1,from,help,to);
        count++;
//        System.out.println("move "+n+" from "+from+" to "+to);
        process(n-1,help,to,from);
    }
}
