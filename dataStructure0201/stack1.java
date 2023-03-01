package com.zhangyu.datastructure.dataStructure0201;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用两个队列实现栈
 */
public class stack1 {
    public static void main(String[] args){
        MyStack stack=new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
    private Queue<Integer> queue1=new LinkedList<>();
    private Queue<Integer> queue2=new LinkedList<>();

    public void push(int x) {
        queue2.add(x);
        while (!queue1.isEmpty()){
            queue2.add(queue1.poll());
        }
        queue1=queue2;
        queue2=new LinkedList<>();
    }

    public int pop() {
        return queue1.poll();
    }

    public int top() {
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}

/**
 * 用一个队列实现栈,每次要把前面的元素都取出来
 */
class MyStack {
    private Queue<Integer> queue=new LinkedList<>();

    public MyStack() {

    }

    public void push(int x) {
        int n=queue.size();
        queue.add(x);
        for (int i = 0; i < n; i++) {
            queue.add(queue.poll());
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
