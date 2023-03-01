package com.zhangyu.datastructure.dataStructure0211;

import java.util.LinkedList;
import java.util.Queue;

public class practice01 {
    public static class ListNode{
        int val;
        ListNode left;
        ListNode right;

        public ListNode(int val) {
            this.val = val;
        }

        public static void main(String[] args){
            t1();
        }

        public static void t1(){
            ListNode head = generateListNode(10000, 10000);
            printOrder(head,0,"H",17);
        }



        /**
         * 从上至下打印一颗二叉树
         * height表示当前节点的高度,emo表示填充的符号,len表示总的长度
         * @param head
         */
        public static void printOrder(ListNode head,int height,String emo,int len){
            if(head==null){
                return;
            }
            printOrder(head.right,height+1,">",len);
            String val=emo+head.val+emo;
            String left=getSpace((len-val.length())/2);
            String right=getSpace(len-left.length()-val.length());
            val=left+val+right;
            System.out.println(getSpace(len*height)+val);
            printOrder(head.left,height+1,"<",len);
        }

        public static String getSpace(int len){
            String s="";
            for (int i = 0; i < len; i++) {
                s+=" ";
            }
            return s;
        }

        /**
         * ### 3.随机构造出一颗二叉树.
         *
         * 随机建二叉树，随机概率如果小于0.3则为空节点，剩下就为一个结点，使用层级序列化
         */
        public static ListNode generateListNode(int maxLen,int maxVal){
            int len=(int)(Math.random()*maxLen);
            if(len==0){
                return null;
            }
            Queue<Integer> queue=new LinkedList<>();
            queue.add(((int)(Math.random()*maxVal)));//头结点不能为空
            for (int i = 0; i < len; i++) {
                if(Math.random()<0.05){
                    queue.add(-1);//约定-1为空节点
                }else{
                    queue.add(((int)(Math.random()*maxVal)));
                }
            }
            return getListNodefromQueue(queue);
        }

        public static ListNode getListNodefromQueue(Queue<Integer> queue){
            ListNode head=new ListNode(queue.poll());
            Queue<ListNode> help=new LinkedList<>();
            help.add(head);
            while (!help.isEmpty()){
                ListNode node=help.poll();
                if(queue.isEmpty() || queue.peek()==-1){
                    node.left=null;
                }else{
                    node.left=new ListNode(queue.poll());
                    help.add(node.left);
                }
                if(queue.isEmpty() || queue.peek()==-1){
                    node.right=null;
                }else{
                    node.right=new ListNode(queue.poll());
                    help.add(node.right);
                }
            }
            return head;
        }
    }




}
