package com.zhangyu.datastructure.dataStructure0130;

import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.Stack;

public class MyStackTest {
    /**
     * 测试思路:
     * 1.创建一个mystack
     * 2.随机添加数字1-10000
     * 3.设置测试次数
     * 4.设定一个求最小值的方法,利用集合的包装类来完成,需要将Mystack中提供得到stack1的方法
     * 5.比较两种方法得到的最小值
     * 6.弹出时,再次比较一遍
     */
    @Test
    public void test1(){
        MyStack stack=new MyStack();
        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(1);
        Stack<Integer> stack1 = stack.getStack1();
        System.out.println(Collections.min(stack1));
    }

    @Test
    public void test2(){
        MyStack stack=new MyStack();
        int maxNum=1000000;
        int times=10000;
        for (int i = 0; i < times; i++) {
            int ran=(int)(Math.random()*maxNum);
            stack.push(ran);
            if(stack.getMin()!=Collections.min(stack.getStack1())){
                System.out.println("出错了");
                System.out.println(stack);
            }
            System.out.println("push======"+i);
        }
        for (int i = 0; i < times; i++) {
            int ran=(int)(Math.random()*maxNum);
            stack.pop();
            if(stack.getMin()!=Collections.min(stack.getStack1())){
                System.out.println("出错了");
                System.out.println(stack);
            }
            System.out.println("pop======"+i);
        }
    }

    @Test
    public void test3(){
        MyStack1 stack=new MyStack1();
        int maxNum=1000000;
        int times=100000;
        String f="0";
        String ff;
        for (int i = 0; i < times; i++) {
            int ran=(int)(Math.random()*maxNum);
            stack.push(ran);
            if(stack.getMin()!=Collections.min(stack.getStack1())){
                System.out.println("出错了");
                System.out.println(stack);
            }
            //System.out.println("push======"+i);
            double v = i * 100 / (double) times;
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMaximumFractionDigits(2);
            ff = nf.format(v);
            if(Math.abs(Double.parseDouble(ff)-Double.parseDouble(f))>=0.01){
                f=ff;
                System.out.println("push当前进度"+ ff +"%");
            }
        }
        for (int i = 0; i < times; i++) {
            int ran=(int)(Math.random()*maxNum);
            stack.pop();
            if(stack.getMin()!=Collections.min(stack.getStack1())){
                System.out.println("出错了");
                System.out.println(stack);
            }
            System.out.println("pop======"+i);
        }
    }
}
