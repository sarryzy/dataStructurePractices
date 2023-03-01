package com.zhangyu.datastructure.dataStructure0209;

import java.util.ArrayList;
import java.util.List;

public class practice01 {
    public static class ListNode{ int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode cur=head;
        int len=0;
        while (cur!=null){
            len++;
            cur=cur.next;
        }
        if(len==n){
            return head.next;
        }
        cur=head;
        int i=0;
        while (i!=len-n-1){
            i++;
            cur=cur.next;
        }
        ListNode next=cur.next.next;
        cur.next=next;
        return head;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        int n=lists.length;
        ListNode head=new ListNode(0);
        ListNode cur=head;
        System.out.println(getMin(lists));
        while (getMin(lists)!=-1){
            int min=getMin(lists);
            head.next=new ListNode(lists[min].val);
            lists[min]=lists[min].next;
            head=head.next;
        }
        return cur.next;
    }

    public static int getMin(ListNode[] lists){
        int index=-1;//记录下最小值的位置
        for (int i = 0; i < lists.length; i++) {
            if(lists[i]!=null){
                index=i;
                break;
            }
        }
        if(index==-1){
            return -1;//说明全为null
        }
        for (int i = 0; i < lists.length; i++) {
            if(lists[i]==null){
                continue;
            }
            if(lists[i].val<lists[index].val){
                index=i;
            }
        }
        return index;
    }

    public ListNode swapPairs(ListNode head) {
        if(head==null || head.next==null){
            return head;
        }
        ListNode head1=head.next;
        ListNode cur=head1.next;
        ListNode lastEnd=head;
        head1.next=lastEnd;
        while (cur!=null && cur.next!=null){
            ListNode end=cur.next;
            ListNode next=cur.next.next;
            end.next=cur;
            lastEnd.next=end;
            lastEnd=cur;
            cur=next;
        }
        lastEnd.next=cur;
        return head1;
    }

    public void deleteNode(ListNode node) {
        node.val=node.next.val;
        node.next=node.next.next;
    }

    public ListNode middleNode(ListNode head) {
        if(head.next==null){
            return head;
        }
        ListNode fast=head.next;
        ListNode slow=head.next;
        while (fast.next!=null && fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }

    public int[] reversePrint(ListNode head) {
        int len=0;
        ListNode cur=head;
        while (cur!=null){
            cur=cur.next;
            len++;
        }
        int[] arr=new int[len];
        cur=head;
        for (int i = 0; i < arr.length; i++) {
            arr[arr.length-1-i]=cur.val;
            cur=cur.next;
        }
        return arr;
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        int len=0;
        ListNode cur=head;
        while (cur!=null){
            len++;
            cur=cur.next;
        }
        cur=head;
        while (len-k>0){
            len--;
            cur=cur.next;
        }
        return cur;
    }

    public ListNode reverseList(ListNode head) {
        if(head==null || head.next==null){
            return head;
        }
        ListNode next=null;
        ListNode pre=null;
        ListNode cur=head;
        while (cur!=null){
            next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        return pre;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head=new ListNode(0);
        ListNode cur=head;
        while (l1!=null && l2 !=null){
            if(l1.val<l2.val){
                head.next=l1;
                l1=l1.next;
            }else{
                head.next=l2;
                l2=l2.next;
            }
            head=head.next;
        }
        if (l1!=null){
            head.next=l1;
        }
        if (l2!=null){
            head.next=l2;
        }
        return cur.next;
    }

    public ListNode getIntersectionNode(ListNode head1, ListNode head2) {
        int len=0;
        ListNode cur=head1;
        while (cur!=null){
            cur=cur.next;
            len++;
        }
        cur=head2;
        while (cur!=null){
            cur=cur.next;
            len--;
        }
        ListNode cur1=len>0?head1:head2;
        cur=cur1==head1?head2:head1;
        len=Math.abs(len);
        while (len>0){
            cur1=cur1.next;
            len--;
        }
        while (cur!=cur1){
            cur1=cur1.next;
            cur=cur.next;
        }
        return cur;
    }

    public static void main(String[] args){
        List<List<String>> lists0 = new practice01().solveNQueens(9);
        List<List<String>> lists1 = new practice01().solveNQueens(8);
        List<List<String>> lists2 = new practice01().solveNQueens(7);
        List<List<String>> lists3 = new practice01().solveNQueens(6);
        List<List<String>> lists4= new practice01().solveNQueens(5);
        List<List<String>> lists5 = new practice01().solveNQueens(4);
        List<List<String>> lists6 = new practice01().solveNQueens(3);
        List<List<String>> lists7 = new practice01().solveNQueens(2);
        List<List<String>> lists8 = new practice01().solveNQueens(1);
        System.out.println(lists0.size());
        System.out.println(lists1.size());
        System.out.println(lists2.size());
        System.out.println(lists3.size());
        System.out.println(lists4.size());
        System.out.println(lists5.size());
        System.out.println(lists6.size());
        System.out.println(lists7.size());
        System.out.println(lists8.size());
    }

    int count=0;
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> list=new ArrayList<>();
        int[] arr=new int[n];
        set(0,arr,n,list);
        return list;
    }

    public void set(int n,int[] arr,int k,List<List<String>> list){
        //放置第n个,k表示总共 的位置
        if(n==k){
            count++;
            List<String> list1=new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                String s="";
                for (int j = 0; j < arr.length; j++) {
                    if(j==arr[i]){
                        s+="Q";
                    }else{
                        s+=".";
                    }
                }
                list1.add(s);
            }
            list.add(list1);
            return;
        }
        for (int i = 0; i < k; i++) {
            arr[n]=i;
            if(check(n,arr)){
                set(n+1,arr,k,list);
            }
        }
    }

    public boolean check(int n,int[] arr){
        //检查第n位放置的是否正确
        for (int i = 0; i < n; i++) {
            if(Math.abs(arr[i]-arr[n])==Math.abs(i-n) || arr[i]==arr[n]){
                return false;
            }
        }
        return true;
    }
}
