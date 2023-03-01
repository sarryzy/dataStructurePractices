package com.zhangyu.datastructure.dataStructure0201;

import java.util.Stack;

public class MyQueueWith2Stack {
    private Stack<Integer> stack1;//stack1用来装入数据
    private Stack<Integer> stack2;//stack2用来倒出数据

    public MyQueueWith2Stack(){
        stack1=new Stack<>();
        stack2=new Stack<>();
    }

    public void appendTail(int val){
        stack1.push(val);
    }

    public int deleteHead(){
        if(stack1.isEmpty() && stack2.isEmpty()){
            return -1;
        }
        if(!stack2.isEmpty()){
            return stack2.pop();
        }
        while (!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    public static void main(String[] args){
        MyQueueWith2Stack queue=new MyQueueWith2Stack();
        queue.appendTail(1);
        queue.appendTail(2);
        queue.appendTail(3);
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
    }
}
