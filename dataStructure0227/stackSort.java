package com.zhangyu.datastructure.dataStructure0227;

import java.util.Arrays;
import java.util.Stack;

public class stackSort {
    public static void main(String[] args){
        int[] arr={1,2,4,2,12,6,6};
        staSort(arr);
    }

    public static void staSort(int[] arr){
        //从大到小放入
        Stack<Integer> stack1=new Stack<>();
        Stack<Integer> stack2=new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack1.isEmpty() && arr[i]>stack1.peek()){
                stack2.push(stack1.pop());
            }
            stack1.push(arr[i]);
            while (!stack2.isEmpty()){
                stack1.push(stack2.pop());
            }
        }
        while (!stack1.isEmpty()){
            System.out.print(stack1.pop()+" ");
        }
    }
}
