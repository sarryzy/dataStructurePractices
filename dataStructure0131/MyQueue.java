package com.zhangyu.datastructure.dataStructure0131;

/**
 * 用单链表模拟队列,先进先出即可
 * 提供peek,pop,add方法
 */
public class MyQueue {
    private Node head;
    private Node tail;
    private int size;

    public void add(Node node){
        if(head==null){
            head=node;
            tail=node;
        }
        tail.next=node;
        tail=node;
    }

    public int peek(){
        return head.val;
    }

    public Node pop(){
        if(head!=null){
            Node next = head;
            head=head.next;
            return head;
        }
        throw new RuntimeException();
    }
}


