package com.zhangyu.datastructure.dataStructure0131;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MyTest {

    /**
     * 测试思路:创建10000个链表
     */
    @Test
    public void t1(){
        int times=10000;
        int maxVal=10000;
        int maxLen=10000;

//        Object[] arr = getLinked(10, 10);
//        Node head = (Node)arr[0];
//        ArrayList<Integer> list= (ArrayList<Integer>) arr[1];
//        while (head!=null){
//            System.out.print(head.val+" ");
//            head=head.next;
//        }
//        System.out.println(list);


        for (int i = 0; i < times; i++) {
            Object[] arr = getLinked(maxLen, maxVal);
            Node head = (Node)arr[0];
            ArrayList<Integer> list= (ArrayList<Integer>) arr[1];
            head=reverseLinkedList.reverse(head);
            if(!judge(head,list)){
                break;
            }
            System.out.println(i);
        }
    }


    /**
     * 创建一个随机链表
     *  将链表中的元素依次放到一个arraylist中
     */
    public static Object[] getLinked(int maxLen,int maxVal){
        Node head=new Node((int)(Math.random()*maxVal));
        Node cur=head;
        ArrayList<Integer> list=new ArrayList<>();
        list.add(head.val);
        for (int i = 0; i < maxLen; i++) {
            Node node = new Node((int) (Math.random() * maxVal));
            list.add(node.val);
            cur.next=node;
            cur=node;
        }
        Object[] arr=new Object[2];
        arr[0]=head;
        arr[1]=list;
        return arr;
    }

    /**
     * 比较反转后的链表和arraylist中的元素是否是逆序关系
     * 直接比较
     */
    public static boolean judge(Node head,ArrayList<Integer> list){
        int i=list.size()-1;
        for (; i >=0; i--) {
            if(head.val!=list.get(i)){
                System.out.println("出错了");
                System.out.println(list);
                return false;
            }
            head=head.next;
        }
        return true;
    }


}
