package com.zhangyu.datastructure.dataStructure0201;

import java.util.Stack;

/**
 * 两个栈实现队列
 */
public class CQueue {
    private Stack<Integer> stack1=new Stack<>();
    private Stack<Integer> stack2=new Stack<>();
    public CQueue() {

    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if(stack1.isEmpty()){
            return -1;
        }
        while (!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        int res=stack2.pop();
        while (!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        return res;
    }

    public static void main(String[] args){
        CQueue c=new CQueue();
        c.appendTail(1);
        c.appendTail(2);
        c.appendTail(3);
        c.appendTail(4);
        c.appendTail(5);
        System.out.println(c.deleteHead());
        System.out.println(c.deleteHead());
        System.out.println(c.deleteHead());
        System.out.println(c.deleteHead());
        System.out.println(c.deleteHead());
    }
}
