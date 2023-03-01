package com.zhangyu.datastructure.dataStructure0218;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class practice01 {
    /**
     * 滑动窗口的最大值
     * 准备一个双向队列,当其为空或者新加入的值小于等于最后面的值的时候,直接加入,否则从后面依次弹出,再把这个值加进去,当这个索引的位置超过范围的时候,将其从前面弹出
     */
    public static int[] getMax(int[] nums, int k){
        Deque<Integer> queue=new LinkedList<>();
        int[] arr=new int[nums.length-k+1];
        int index=0;
        for (int i = 0; i < nums.length; i++) {
            while (!queue.isEmpty() && nums[i]>nums[queue.peekLast()]){
                queue.pollLast();
            }
            queue.addLast(i);
            if(i- queue.peekFirst()>=k){
                queue.pollFirst();
            }
            if(i-k>=-1){
                arr[index++]=nums[queue.peekFirst()];
            }
        }
        return arr;
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  public static ListNode moveLinkedList(ListNode head, int k){
        if(head==null || k==0){
            return head;
        }
        ListNode cur=head;
        int len=0;
        while (cur!=null){
            cur=cur.next;
            len++;
        }
        k=k%len;
        if(k==0){
            return head;
        }
        cur=head;
      ListNode pre=new ListNode(0);
        pre.next=head;
      //要找到倒数第K个节点
      int step=len-k;
      while (step!=0){
          pre=pre.next;
          cur=cur.next;
          step--;
      }
      pre.next=null;
      pre=cur;//现在头为cur
      while (pre.next!=null){
          pre=pre.next;
      }
      pre.next=head;
      return cur;
  }
    public static boolean judge(char[][] board){
        //每一行
        for (char[] chars : board) {
            int[] arr=generateArr();
            for (int i = 0; i < chars.length; i++) {
                if(chars[i]>'0' && chars[i]<='9'){
                    arr[chars[i]-'0'-1]--;
                }
                if(!check(arr)){
                    return false;
                }
            }
        }
        //每一列
        for (int i = 0; i < board[0].length; i++) {
            int[] arr=generateArr();
            for (int j = 0; j < board.length; j++) {//j为行号
                if (board[j][i] > '0' && board[j][i] <= '9') {
                    arr[board[j][i] - '0' - 1]--;
                }
                if (!check(arr)) {
                    return false;
                }

            }
        }

        //每个9宫格

        for (int x = 0; x < 9; x+=3) {
            for (int y = 0; y < 9; y+=3) {
                int[] arr=generateArr();
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[x + j][y + i] > '0' && board[x + j][y + i] <= '9') {
                            arr[board[x+j][y+i] - '0' - 1]--;
                        }
                        if (!check(arr)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static int[] generateArr(){
        int[] arr=new int[9];
        Arrays.fill(arr,1);
        return arr;
    }

    public static boolean check(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]<0){
                return false;
            }
        }
        return true;
    }
}
