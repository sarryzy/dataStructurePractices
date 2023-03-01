package com.zhangyu.datastructure.dataStructure0131;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.TreeMap;

public class practice01 {

    @Test
    public void test1(){
        TreeMap<Integer,String> map=new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        map.put(1,"1");
        map.put(2,"2");
        map.put(3,"3");
        map.put(4,"4");
        map.put(5,"5");
        map.put(6,"6");
        map.put(7,"7");
        map.put(8,"8");
        System.out.println(map.ceilingKey(0));
    }

    public ListNode reverseKGroup(ListNode head,int k){
        //反转k链表
        //先反转前k个
        ListNode firstEnd = getK(head, k);//第一个k个,也是整个链表的头
        if(firstEnd==null){
            return head;
        }
        reverse(head,firstEnd);
        ListNode lastEnd=head;//上一个的结尾
        while (lastEnd.next!=null){
            ListNode start=lastEnd.next;
            ListNode end = getK(start, k);
            if(end==null){
                break;
            }
            reverse(start,end);
            lastEnd.next=end;
            lastEnd=start;
        }
        return firstEnd;
    }

    /**
     * 返回第k个节点,可能为空,如果为空则说明不需要反转了
     * @return
     */
    public static ListNode getK(ListNode node,int k){
        while (k--!=1 && node!=null){
            node=node.next;
        }
        return node;
    }

    /**
     * 局部反转,反转start和end之间的链表
     * 反转过后要把start的连接确定
     */
    public static void reverse(ListNode start,ListNode end){
        if(end==null){
            return;
        }
        ListNode last=end.next;
        ListNode pre=null;
        ListNode cur=start;
        ListNode next=null;
        while(cur!=last){
            next= cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        start.next=last;
    }
}

class ListNode{
    int val;
    ListNode next;

    public ListNode(int val){
        this.val=val;
    }
}
