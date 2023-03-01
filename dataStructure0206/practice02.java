package com.zhangyu.datastructure.dataStructure0206;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class practice02 {
    public static void main(String[] args){
        t7();
    }

    public static void t7(){
        Node node1=new Node(1);
        Node node2=new Node(2);
        Node node3=new Node(3);
        node1.next=node2;
        node2.next=node3;
        deleteNode(node2);
        System.out.println(node1);
    }


    public static void t6(){
        Node node1=new Node(1);
        Node node2=new Node(2);
        Node node3=new Node(3);
        Node node4=new Node(4);
        Node node5=new Node(5);
        Node node0=new Node(0);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node0.next=node3;
        System.out.println("node1"+node1);
        System.out.println("node2"+node2);
        Node theIntersection1 = getTheIntersection2(node1, node0);
        System.out.println(theIntersection1.val);
    }
    public static void t5(){
        Node node1=new Node(1);
        Node node2=new Node(2);
        Node node3=new Node(3);
        node1.next=node2;
        node2.next=node3;
        node3.next=node1;
        Node theEntrance1 = getTheEntrance2(node1);
        System.out.println(theEntrance1.val);
    }
    /**
     * 测试回文
     */
    public static void t1(){
        Node head=new Node(1);
        Node node1=new Node(2);
        Node node2=new Node(3);
        Node node3=new Node(2);
        Node node4=new Node(5);
        Node node5=new Node(52);
        Node node6=new Node(5445);
        Node node7=new Node(552);
        Node node8=new Node(51);
        Node node9=new Node(15);
        head.next=node1;
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=node6;
        node6.next=node7;
        node7.next=node8;
        node8.next=node9;
        Node node = partitionInLinkedList2(head, 10);
        System.out.println(node);
    }

    public static void t2(){
        int[] arr={1,54,4,3,4,5,6,7,8,44,22,33,5,6,65,10};
        arrayPartition(arr,10);
    }

    public static void t3(){
        Node node=new Node(1);
        Node node1=new Node(2);
        Node node2=new Node(3);
        addNode(node1,node2,node);
        System.out.println(node1);
    }

    public static void t4(){
        Node node=new Node(1);
        Node node1=new Node(2);
        Node node2=new Node(3);
        node.next=node1;
        node1.next=node2;
        node.rand=node2;
        node1.rand=node;
        node2.rand=node1;
        Node node3 = cloneLinkedList2(node);
        System.out.println(node3.next.next.rand);
        System.out.println(node2.rand);
    }

    public static void t0(){
        //测试引用
        Node node0=new Node(0);
        Node node1=new Node(1);
        Node node2=new Node(2);
        Node head=null;
        Node tail=null;
        if(head==null && tail==null){
            head=node0;
            tail=node0;

        }
        addNode(head,tail,node1);
        tail=tail.next;
        addNode(head,tail,node2);
        System.out.println(head);
    }

    public static void add(Node head){
        head.val++;
    }




    public static class Node{
        int val;
        Node next;
        Node rand;
        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    "next="+next+
                    '}';
        }
    }

    /**
     * ### 8.不给定链表的结点,只给定一个结点,删除这个结点:
     *
     * 简单做法:把下一个结点的值赋给这个结点,这个结点下一个指向下一个的下一个.
     *
     * 实际上:必须要有头结点才能够删除.
     */
    public static void deleteNode(Node node){
        node.val=node.next.val;
        node.next=node.next.next;
    }

    /**
     * ### 7.两个有环链表相交:
     *
     * 一个有环,一个无环:不可能
     *
     * 情况:
     *
     * (1).两个都有环,不相交
     *
     * (2).不在环内相交:若两个链表的入环结点相同,当做无环来看待.
     *
     * (3).在环内相交.第一个链表在环内转一圈,如果遇到了另一个链表的入环结点则相交.
     */
    public static Node getTheLoopIntersection(Node head1,Node head2){
        Node e1 = getTheEntrance2(head1);//得到环的入口
        Node e2 = getTheEntrance2(head2);
        if(e1==e2){
            //情况2,看做无环来看待
            Node cur1=head1;
            Node cur2=head2;
            int n=0;
            while (cur1!=e1){
                n++;
                cur1=cur1.next;
            }
            while (cur2!=e2){
                n--;
                cur2=cur2.next;
            }
            cur1=n>=0?head1:head2;//较长
            cur2=cur1==head1?head2:head1;//较短
            n=Math.abs(n);
            while (n>0){
                cur1=cur1.next;
            }
            while (cur1!=cur2){
                cur1=cur1.next;
                cur2=cur2.next;
            }
            return cur1;
        }
        //e1转一圈,看看能不能碰到e2,如果可以即为第三种情况
        Node cur=e1;
        while (cur.next!=e1){
            if(cur==e2){
                return cur;
            }
            cur=cur.next;
        }
        //运行到这里说明没有交点
        return null;
    }

    /**
     * ### 2.判断链表是否是回文链表:
     *
     * 简单做法:利用栈将节点放入,然后倒出来,一一对比
     *
     * 复杂做法:利用得到中间结点,将链表的后半部分节点进行逆转,然后一一比较,最后再将链表还原.
     */
    public static boolean judgeWhetherPalindrome1(Node head){
        if(head==null || head.next==null){
            return true;
        }
        Stack<Node> stack=new Stack<>();
        Node cur=head;
        while (cur!=null){
            stack.push(cur);
            cur=cur.next;
        }
        cur=head;
        while (cur!=null){
            if(cur.val!=stack.pop().val){
                return false;
            }else{
                cur=cur.next;
            }
        }
        return true;
    }

    public static boolean judgeWhetherPalindrome2(Node head){
        if(head==null || head.next==null){
            return true;
        }
        if(head.next.next==null){
            return head.val==head.next.val;
        }
        Node slow=head;
        Node fast=head;
        while (fast.next!=null && fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
//        System.out.println("slow"+slow);
//        System.out.println("fast"+fast);
        //这样slow即为中间结点
        Node n1=slow.next;
        slow.next=null;//slow为中间结点
        Node pre=null;
        Node next=null;
        while (n1!=null){
            next=n1.next;
            n1.next=pre;
            pre=n1;
            n1=next;
        }
//        System.out.println("pre:"+pre);
        //比较
        n1=pre;
        Node n2=head;
        boolean flag=true;
        while (n1!=null){
            if(n1.val!=n2.val){
                flag=false;
                break;
            }else{
                n1=n1.next;
                n2=n2.next;
            }
        }
        //还原链表
        Node cur=pre;
        pre=null;
        next=null;
        while (cur!=null){
            next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        slow.next=pre;
        System.out.println("head:"+head);
        return flag;
    }

    /**
     * 3.对链表进行partition划分,小于的放左边,大于的放右边,等于的放中间
     * 简单做法:利用数组,直接将链表的数字放入数组里面,然后利用数组进行partition
     *
     * 复杂做法:利用六个节点,把小于区,等于区,大于区的节点分别穿起来,最后再把他们一起穿起来
     * @param head
     */
    public static Node partitionInLinkedList1(Node head,int num){
        if(head==null || head.next==null){
            return head;
        }
        Node cur=head;
        int size=0;
        while (cur!=null){
            size++;
            cur=cur.next;
        }
        Node[] arr=new Node[size];
        cur=head;
        for (int i = 0; i < arr.length; i++) {
            arr[i]=cur;
            cur=cur.next;
        }
        NodePartition(arr,num);
        for (int i = 0; i < arr.length; i++) {
            arr[i].next=i+1< arr.length?arr[i+1]:null;
        }
        return arr[0];
    }

    public static Node partitionInLinkedList2(Node head,int num){
        if(head==null || head.next==null){
            return head;
        }
        Node small1=null;
        Node small2=null;
        Node equal1=null;
        Node equal2=null;
        Node big1=null;
        Node big2=null;
        Node cur=head;
        while (cur!=null){
            Node next=cur.next;
            cur.next=null;
            if(cur.val<num){
                if(small1==null && small2==null){
                    small1=cur;
                    small2=cur;
                }else{
                    small2.next=cur;
                    small2=cur;
                }
            }else if(cur.val>num){
                if(big1==null && big2==null){
                    big1=cur;
                    big2=cur;
                }else{
                    big2.next=cur;
                    big2=cur;
                }
            }else{
                if(equal1==null && equal2==null){
                    equal1=cur;
                    equal2=cur;
                }else{
                    equal2.next=cur;
                    equal2=cur;
                }
            }
            cur=next;
        }
        //各个部分已经组装完毕,现在需要将三个链表组合在一起,不排除有为空的可能性,所以需要注意合并策略
        if(small2!=null){
            small2.next=equal1;
            equal2=equal2==null?small2:equal2;
        }
        if(equal2!=null){
            equal2.next=big1;
        }
        return small1!=null?small1:(equal1!=null?equal1:big1);
    }

    /**
     * ### 4.克隆一个有next和rand指针的链表:
     *
     * 简单做法:利用哈希表,先遍历一遍,直接将所有的节点创建到哈希表里面.在遍历一遍,将哈希表中的节点串起来.
     *
     * 复杂做法:直接在原链表的基础上设置对应,将新节点创建在原来老节点的下一个.最后将新链表分离出来.
     */
    public static Node cloneLinkedList1(Node head){
        HashMap<Node,Node> map=new HashMap<>();
        Node cur=head;
        while (cur!=null){
            map.put(cur,new Node(cur.val));
            cur=cur.next;
        }
        cur=head;
        while (cur!=null){
            Node node = map.get(cur);
            node.next=map.get(cur.next);
            node.rand=map.get(cur.rand);
            cur=cur.next;
        }
        return map.get(head);
    }

    public static Node cloneLinkedList2(Node head){
        //1-1'-2-2'-3-3'
        Node cur=head;
        Node next=null;
        while (cur!=null){
            next=cur.next;
            cur.next=new Node(cur.val);
            cur.next.next=next;
            cur=next;
        }
        cur=head;
        while (cur!=null){
            next=cur.next.next;
//            cur.next.next=next==null?null:next.next;
            cur.next.rand=cur.rand==null?null:cur.rand.next;
            cur=next;
        }
        cur=head;
        Node node=cur.next;
        head=node;
        while (cur!=null){
            next=cur.next.next;
            node.next=next==null?null:next.next;
            cur.next=next;
            cur=next;
            node=node.next;
        }
        return head;
    }

    /**
     * ### 5.找到链表的环的入口:
     *
     * 简单做法:使用一个set,每次将节点放入其中,当找到第一个已经存在的节点时,说明这个节点就是入口.
     *
     * 复杂做法:使用快慢指针,首次相遇时让快指针返回,每次只走一步,这样两次节点相遇时即为环的入口.
     */
    public static Node getTheEntrance1(Node head){
        Node cur=head;
        HashSet<Node> set=new HashSet<>();
        while (cur!=null){
            set.add(cur);
            cur=cur.next;
            if(set.contains(cur)){
                return cur;
            }
        }
        return null;
    }

    public static Node getTheEntrance2(Node head){
        Node slow=head;
        Node fast=head;
        while (fast.next!=null && fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(slow==fast){
                fast=head;
                break;
            }
        }
        if(fast.next==null || fast.next.next==null){
            return null;
        }
        while (fast!=slow){
            fast=fast.next;
            slow=slow.next;
        }
        return fast;
    }

    /**
     * ### 6.两个无环链表相交求交点:
     *
     * 简单做法:若两个链表无环,将其中一个链表放入set,遍历另一个链表,哪一个在set中有就是交点
     *
     * 复杂做法:让两个链表走完,如果走到最后都不相等,那么一定不相交.然后得出两个链表长度的差值,让较长的链表先走多余的步数,然后一起走,相交的第一个节点即为交点.
     */
    public static Node getTheIntersection1(Node head1,Node head2){
        HashSet<Node> set=new HashSet<>();
        Node cur=head1;
        while (cur!=null){
            set.add(cur);
            cur=cur.next;
        }
        cur=head2;
        while (cur!=null){
            if(set.contains(cur)){
                return cur;
            }else{
                cur=cur.next;
            }
        }
        return null;
    }

    public static Node getTheIntersection2(Node head1,Node head2){
        int n=0;
        Node cur=head1;
        while (cur!=null){
            n++;
            cur=cur.next;
        }
        cur=head2;
        while (cur!=null){
            n--;
            cur=cur.next;
        }
        Node l=n>=0?head1:head2;//长链表
        Node s=l==head1?head2:head1;//短链表
        n=Math.abs(n);
        cur=l;
        while (n>0){
            cur=cur.next;
            n--;
        }
        while (cur!=s){
            cur=cur.next;
            s=s.next;
        }
        return cur;
    }


    /**
     * 添加结点
     * @param head
     * @param tail
     * @param node
     */
    public static Node addNode(Node head,Node tail,Node node){
        tail.next=node;
        tail=node;
        return head;
    }


    public static void NodePartition(Node[] arr,int num){
        int left=-1;//左边界
        int right=arr.length;//右边界
        int index=0;
        while (index<right){
            if(arr[index].val<num){
                nodeSwap(arr,index++,++left);
            }else if(arr[index].val>num){
                nodeSwap(arr,index,--right);
            }else{
                index++;
            }
        }
    }
    /**
     * 对数组进行partition的过程
     * 考虑到Num可能不是数组里的值,首先让左右边界就位
     * 如果数组当前值小于num,将其与小于区前面一个数交换,i++
     * 如果等于,直接i++
     * 如果大于,将其与大于区前面一个数交换,i不变
     */
    public static void arrayPartition(int[] arr,int num){
        int left=-1;//左边界
        int right=arr.length;//右边界
        int index=0;
        while (index<right){
            if(arr[index]<num){
                swap(arr,index++,++left);
            }else if(arr[index]>num){
                swap(arr,index,--right);
            }else{
                index++;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public static void nodeSwap(Node[] arr,int i,int j){
        Node temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }


}
