package com.zhangyu.datastructure.dataStructure0131;

public class reverseLinkedList {

    public static Node reverse(Node head){
        Node next=null;
        Node pre=null;
        Node cur=head;
        while (cur!=null){
            next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        return pre;
    }

    public static DoubleNode reverseDouble(DoubleNode head){
        DoubleNode last=null;
        DoubleNode pre=null;
        DoubleNode next=null;
        while (head!=null){
            next=head.next;
            head.last=next;
            head.next=pre;
            pre=head;
            head=next;
        }
        return pre;
    }
}

class Node{
    int val;
    Node next;

    public Node(int val){
        this.val=val;
    }

}

class DoubleNode{
    int val;
    DoubleNode next;
    DoubleNode last;

    public DoubleNode(int val){
        this.val=val;
    }
}
