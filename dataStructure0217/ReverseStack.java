package com.zhangyu.datastructure.dataStructure0217;

import java.util.Stack;

public class ReverseStack {

    public static void main(String[] args){
        t1();
    }

    public static void t1(){
        Stack<Integer> stack=new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack);
        reverse(stack);
        System.out.println(stack);
    }
    /**
     * ### 4.空间复杂度为O(1)逆序一个栈.
     *
     * 首先构造方法得到栈底的数.
     *
     * 然后得到这个数,再得到,再得到,然后压栈.
     */
    //得到栈底的数,不影响其他的数
    public static int getNum(Stack<Integer> stack){
        int res=stack.pop();
        if(stack.isEmpty()){
            return res;
        }else{
            int ans=getNum(stack);
            stack.push(res);
            return ans;
        }
    }

    public static void reverse(Stack<Integer> stack){
        if(stack.size()==1){
            return;
        }
        int res=getNum(stack);
        reverse(stack);
        stack.push(res);
    }
}
