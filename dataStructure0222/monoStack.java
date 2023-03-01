package com.zhangyu.datastructure.dataStructure0222;

import java.util.ArrayList;
import java.util.Stack;

public class monoStack {
    public static void main(String[] args){
        int[] arr={1,2,2,2,3,3,3,4,4,5};
        int[][] stack1 = getStack1(arr);
        for(int i=0;i<1000000;i++){
            int[] arr1=new int[100];
            for (int i1 = 0; i1 < arr.length; i1++) {
                arr1[i1]=(int)(Math.random()*100);
                getStack1(arr1);
                System.out.println(i);
            }
        }
    }
    public static void printArr(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
    /**
     * ### 3.单调栈,找到一个数组中左边的比他小的索引和右边的比他小的索引.
     *
     * 如果没有相同的值:
     *
     * 思路:让栈从底到顶依次增大,
     * 如果当前要加入的值比栈顶的元素要小的话.将这个位置的值弹出,并且将其左边和右边的值记下来.
     *
     * 如果有相同的值:
     *
     * 让相同的值如果捧在一起就组成一个list.
     */
    public static int[][] getStack1(int[] arr){
        int[][] ans=new int[arr.length][2];
        Stack<ArrayList<Integer>> stack=new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i]<arr[stack.peek().get(0)]){
                ArrayList<Integer> pop = stack.pop();
                for (Integer j : pop) {
                    ans[j][0]=stack.isEmpty()?-1:stack.peek().get(stack.peek().size()-1);
                    ans[j][1]=i;
                }
            }
            if(!stack.isEmpty()&&arr[i]==arr[stack.peek().get(0)]){
                stack.peek().add(i);
            }else{
                ArrayList<Integer> list=new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()){
            ArrayList<Integer> pop = stack.pop();
            for (Integer j : pop) {
                ans[j][1]=-1;
                ans[j][0]=!stack.isEmpty()?stack.peek().get(stack.peek().size()-1):-1;
            }
        }
        return ans;
    }
}
