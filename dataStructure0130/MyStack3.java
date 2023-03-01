package com.zhangyu.datastructure.dataStructure0130;

import java.util.Stack;

public class MyStack3 {
    private Stack<Integer> stack=new Stack<>();
    int min=Integer.MAX_VALUE;

    public void push(int val){
        if(val<=min){
            stack.push(min);
            min=val;
        }
        stack.push(val);
    }

    public int top(){
        return stack.peek();
    }

    public void pop(){
        if(stack.peek()==min){
            stack.pop();
            min=stack.pop();
        }else{
            stack.pop();
        }
    }

    public int getMin(){
        return min;
    }
}
