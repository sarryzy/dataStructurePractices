package com.zhangyu.datastructure.dataStructure0222;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class slideWindowMax {
    public static void main(String[] args){
        int[] arr={1,2,3,4,5};
        int[] max = getMax(arr, 3);
        System.out.println(Arrays.toString(max));
    }

    public static int[] getMax(int[] arr,int k){
        if(arr==null ){
            return null;
        }
        int n=arr.length;
        int[] ans=new int[n-k+1];
        int index=0;
        Deque<Integer> queue=new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            while (!queue.isEmpty() && arr[queue.peekLast()]<arr[i]){
                queue.pollLast();
            }
            queue.addLast(i);
            if(i-k==queue.peekFirst()){
                queue.pollFirst();
            }
            if(i-k+1>=0){
                ans[index++]=arr[queue.peekFirst()];
            }

        }
        return ans;
    }
}
