package com.zhangyu.datastructure.dataStructure0204;

public class practice02 {
    public static void main(String[] args){
        t1();
    }

    public static void t1(){
        slowHeap heap2=new slowHeap();
        quickHeap heap1=new quickHeap();
        int times=1000000;
        for (int i = 0; i < times; i++) {
            int num=(int)(Math.random()*10);
            if(Math.random()<0.5){
                heap1.push(num);
                heap2.push(num);
            }else{
                int p1=heap1.pop();
                int p2=heap2.pop();
                if(p1!= p2){
                    System.out.println("出错了");
                    System.out.println("p1 "+p1+"\n"+"p2 "+p2);
                    heap1.printHeap();
                    System.out.println();
                    heap2.printHeap();
                    break;
                }
            }
            System.out.println(i);
        }
        System.out.println("nice");
    }

    public static void t2(){
        slowHeap heap2=new slowHeap();
        heap2.push(12);
        heap2.push(23);
        heap2.push(34);
        heap2.push(456);
        heap2.push(56);
        heap2.push(656);
        heap2.push(45);
        System.out.println(heap2.pop());
        System.out.println(heap2.pop());
        System.out.println(heap2.pop());
        System.out.println(heap2.pop());
        System.out.println(heap2.pop());
        System.out.println(heap2.pop());
    }
}
