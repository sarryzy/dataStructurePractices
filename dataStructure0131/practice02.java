package com.zhangyu.datastructure.dataStructure0131;

/**
 *链表相加
 */
public class practice02 {

    public static void main(String[] args){
        ListNode listNode = new ListNode(0);
        ListNode listNode1 = new ListNode(6);
        listNode1.next=new ListNode(3);
        ListNode listNode2 = addInList(listNode, listNode1);
        printListNode(listNode2);
    }
    /**
     * 首先将两个链表反转,找到较长的那一个,让它作为底,把较短的加到较长的上面来
     * @param head1
     * @param head2
     * @return
     */
    public static ListNode addInList (ListNode head1, ListNode head2) {
        // write code here
        head1=reverse(head1);
        head2=reverse(head2);
        int len1 = getLen(head1);
        int len2 = getLen(head2);
        ListNode cur1=len1>=len2?head1:head2;//较长
        ListNode cur2=cur1==head1?head2:head1;//较短
        head1=cur1;
        int carry=0;
        while (cur2!=null){
            int num = cur1.val + cur2.val + carry;
            cur1.val= num %10;
            carry=num/10;
            cur1=cur1.next;
            cur2=cur2.next;
        }
        while (cur1!=null){
            int num = cur1.val + carry;
            cur1.val= num %10;
            carry=num/10;
            cur1=cur1.next;
        }
        if(carry!=0){
            ListNode listNode = new ListNode(1);
            listNode.next=reverse(head1);
            return listNode;
        }
        return reverse(head1);
    }

    public static ListNode reverse(ListNode head){
        ListNode pre=null;
        ListNode next=null;
        while (head!=null){
            next=head.next;
            head.next=pre;
            pre= head;
            head=next;
        }
        return pre;
    }

    public static int getLen(ListNode head){
        int k=0;
        while (head!=null){
            head=head.next;
            k++;
        }
        return k;
    }

    public static void printListNode(ListNode head){
        while (head!=null){
            System.out.print(head.val);
            head=head.next;
        }
    }
}
