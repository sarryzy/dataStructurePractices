package com.zhangyu.datastructure.dataStructure0130;

import java.util.Stack;

/**
 * 实现一个栈,使得查找最小值的时间复杂度为O(1);
 */
public class MyStack {

    private Stack<Integer> stack1=new Stack();//用于存放数字
    private Stack<Integer> stack2=new Stack();//用于存放最小值

    /**
     * push,stack2当当前的值比最小值栈的栈顶元素要小或者相等的时候才存入,或者为空时也直接存入
     * stack1全都存入
     */
    public void push(int num){
        stack1.push(num);
        if(stack2.isEmpty()){
            stack2.push(num);
            return;
        }
        //stack2不为空
        if(num<=stack2.peek()){
            stack2.push(num);
        }
    }

    /**
     * pop,stack1照常弹出
     * stack2,当弹出的值比当前栈顶的相等或者要小的时候再弹出
     */
    public void pop(){
        if(this.stack1.isEmpty()){
            throw new RuntimeException();
        }
        if(stack1.peek()<=stack2.peek()){
            stack2.pop();
        }
        stack1.pop();
    }

    /**
     * 得到最小值
     * 显然最小值为stack2的栈顶元素
     */
    public int getMin(){
        if(stack2.isEmpty()){
            throw new RuntimeException();
        }
        return stack2.peek();
    }

    public Stack<Integer> getStack1() {
        return stack1;
    }
}
