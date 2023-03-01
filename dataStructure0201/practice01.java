package com.zhangyu.datastructure.dataStructure0201;

import java.util.ArrayList;


public class practice01 {
    public static void main(String[] args){
        System.out.println(getNum(15, 1));
        System.out.println(getNum(15, 2));
        System.out.println(getNum(15, 3));
        System.out.println(getNum(15, 4));
    }

    /**
     * 取出一个数字从右向左数第k个1
     */
    public static int getNum(int num,int k){
        //取出从右向左第一个1是eor&(~eor+1)
        for (int i = 1; i < k; i++) {
            num=num^(num&(~num+1));
        }
        return num&(~num+1);
    }

    /**
     * 删除链表中指定的值
     */
    public static ListNode deleteNode(ListNode head,int num){
        //使head成为真正的头部
        while (head!=null){
            if(head.val!=num){
                break;
            }
            head=head.next;
        }
        ListNode pre=head;
        ListNode cur=head;

        while (cur!=null){
            if(cur.val==num){
                pre.next=cur.next;
            }else{
                pre=cur;
            }
            cur=cur.next;
        }

        return head;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ArrayList<ListNode> list=new ArrayList<>();
        ListNode cur=head;
        while (cur!=null){
            list.add(cur);
            cur=cur.next;
        }
        int i=list.size();
        if(n==i){
            return head.next;
        }
        list.get(i-n-1).next=list.get(i-n).next;
        return head;
    }
}

class ListNode{
    int val;
    ListNode next;

    public ListNode(int val){
        this.val=val;
    }
}
