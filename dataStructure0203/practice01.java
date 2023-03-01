package com.zhangyu.datastructure.dataStructure0203;

import java.util.Stack;

public class practice01 {
    public static void main(String[] args){
        Stack<Integer> stack=new Stack<>();
        stack.push(2);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        sortStack(stack);

    }


    //得到栈底的元素
    public static int getThereverseStackNum(Stack<Integer> stack){
        int res=stack.pop();
        if(stack.isEmpty()){
            return res;
        }else{
            int cur=getThereverseStackNum(stack);
            stack.push(res);
            return cur;
        }
    }


    public static void reverseStack(Stack<Integer> stack){
        if(stack.isEmpty()){
            return ;
        }
        int i=getThereverseStackNum(stack);
        reverseStack(stack);
        stack.push(i);
    }

    /**
     * 利用一个栈完成对另一个栈的排序,从栈顶到栈底依次变小
     * stack  pop出一个值,如果这个值比help栈中栈顶的元素要小,则先把help栈顶元素push进入stack内,再把这个值加进help
     */

    public static void sortStack(Stack<Integer> stack){
        Stack<Integer> help=new Stack<>();
        while (!stack.isEmpty()){
            int res=stack.pop();
            while (!help.isEmpty() && res>help.peek()){
                stack.push(help.pop());
            }
            help.push(res);
        }
        while (!help.isEmpty()){
            stack.push(help.pop());
        }
        System.out.println(stack);
    }
}
