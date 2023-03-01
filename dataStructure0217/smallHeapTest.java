package com.zhangyu.datastructure.dataStructure0217;

public class smallHeapTest {
    public static void main(String[] args){
        t1();
    }

    public static void t1(){
        Smallheap heap=new Smallheap();
        heap.add(1);
        heap.add(5435);
        heap.add(4);
        heap.add(42);
        heap.add(52);
        heap.add(61);
        heap.add(73);
        System.out.println(heap);
        while (!heap.isEmpty()){
            System.out.println(heap.pop());
        }
    }
}
