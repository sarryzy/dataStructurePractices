package com.zhangyu.datastructure.dataStructure0225;

import com.zhangyu.datastructure.dataStructure0224.BinaryTreeKMP;

import java.util.Stack;

public class deleteThesame {
    /**
     * 删除重复的节点
     * 思路:首先看看能不能找到头结点,怎么找?用一个指针pre指向head,当前节点来到head.next,如果,直到找到一个这两个值不相等的节点,
     */

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }


    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        int len=0;
        ListNode cur=l1;
        while (cur!=null){
            len++;
            cur=cur.next;
        }
        int[] arr1=new int[len];
        len=0;
        cur=l2;
        while (cur!=null){
            len++;
            cur=cur.next;
        }
        int[] arr2=new int[len];
        cur=l1;
        for (int i = 0; i < arr1.length; i++) {
            arr1[i]=cur.val;
            cur=cur.next;
        }
        cur=l2;
        for (int i = 0; i < arr2.length; i++) {
            arr2[i]=cur.val;
            cur=cur.next;
        }
        int[] big=arr1.length>arr2.length?arr1:arr2;
        int[] small=big==arr1?arr2:arr1;
        int carry=0;
        int sum=0;
        int i=0;
        while (i<small.length){
            sum=big[big.length-1-i]+small[small.length-1-i]+carry;
            big[big.length-1-i]=sum%10;
            carry=sum/10;
            i++;
        }
        while (i<big.length){
            sum=big[big.length-1-i]+carry;
            big[big.length-1-i]=sum%10;
            carry=sum/10;
            i++;
        }
        ListNode pre=new ListNode(0);
        cur=pre;
        for (int k = 0; k < big.length; k++) {
            cur.next=new ListNode(big[k]);
            cur=cur.next;
        }
        if(carry==1){
            pre.val=1;
            return pre;
        }else{
            return pre.next;
        }
    }

    public ListNode addTwoNumbers(ListNode node1,ListNode node2){
        Stack<Integer> stack1=new Stack<>();
        Stack<Integer> stack2=new Stack<>();
        while (node1!=null){
            stack1.push(node1.val);
            node1=node1.next;
        }
        while (node2!=null){
            stack2.push(node2.val);
            node2=node2.next;
        }
        int carry=0;
        ListNode cur=null;
        int sum=0;
        while (!stack1.isEmpty()|| !stack2.isEmpty()||carry!=0){
            sum=carry;
            sum+=stack1.isEmpty()?0:stack1.pop();
            sum+=stack2.isEmpty()?0:stack2.pop();
            ListNode node=new ListNode(sum%10);
            carry=sum/10;
            node.next=cur;
            cur=node;
        }
        return cur;
    }


}
