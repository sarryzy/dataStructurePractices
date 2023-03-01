package com.zhangyu.datastructure.dataStructure0130;

import java.util.Stack;

/**
 * 方法二和第一种方法类似
 * 不过Stack2每次都会存入一个值,存入的这个值是当前的数字和stack2栈顶之间较小的那一个,
 * 这样每次弹出的时候都跟着弹出就可以了
 */
public class MyStack1 {
    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    /**
     * 每次push的时候两个栈都要push
     */
    public void push(int num){
        if(stack2.isEmpty()){
            stack2.push(num);
        }
        stack1.push(num);
        stack2.push(num<=stack2.peek()?num:stack2.peek());
    }

    /**
     * pop,每次两个一起Pop
     */
    public void pop(){
        if(stack1.isEmpty()){
            throw new RuntimeException();
        }
        stack1.pop();
        stack2.pop();
    }

    public int getMin(){
        if(stack1.isEmpty()){
            throw new RuntimeException();
        }
        return stack2.peek();
    }

    public Stack<Integer> getStack1() {
        return stack1;
    }

}
