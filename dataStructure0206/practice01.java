package com.zhangyu.datastructure.dataStructure0206;

public class practice01 {
    public static void main(String[] args){
         t1();
    }

    public static void t1(){
        Node head=new Node(1);
        Node cur=head;
        for (int i = 0; i < 99; i++) {
            cur.next=new Node(i+2);
            cur=cur.next;
        }
        Node midOrUpMid = getMidOrUpMid(head);
        System.out.println(midOrUpMid);
    }

    public static Node getMidOrUpMid(Node head){
        if(head==null || head.next==null || head.next.next==null){
            return head;
        }
        Node fast=head;
        Node slow=head;
        while (fast.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
}

class Node{
    int val;
    Node next;

    public Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val ;
    }
}
