package com.zhangyu.datastructure.dataStructure0224;


import com.zhangyu.datastructure.dataStructure0216.Graph;
import com.zhangyu.datastructure.dataStructure0218.practice01;

import java.util.ArrayList;

public class BinaryTreeKMP {
    public static void main(String[] args){
        t1();
    }

    public static void t1(){
        ListNode node1=new ListNode(1);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(3);
        ListNode node4=new ListNode(4);
        ListNode node5=new ListNode(5);
        ListNode node6=new ListNode(6);
        ListNode node7=new ListNode(7);
        node1.left=node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.left=node6;
        node3.right=node7;
        boolean b = judge1(node1, node5);
        boolean c= judge2(node1, node5);
        System.out.println(b);
        System.out.println(c);
        ArrayList<String> strings = turnArr(node1);
        System.out.println(strings);
    }

    public static class ListNode{
        int val;
        ListNode left;
        ListNode right;

        public ListNode(int val){
            this.val=val;
        }
    }
    /**
     * ### 3.两个二叉树判断一个是不是另一个的子树.
     *
     * 简单方法;使用递归判断.每个子树进行判断.
     *
     * 复杂方法:使用先序遍历将两个二叉树遍历.然后将字符串kmp变为字符数组kmp.
     */
    public static boolean judge1(ListNode node1,ListNode node2){
        //判断当前这两颗树是否相同
        if(node1==null){
            return false;
        }
        if(node2==null){
            return false;
        }
        if(check1(node1,node2)){
            return true;
        }
        return judge1(node1.left,node2) || judge1(node1.right,node2);
    }

    public static boolean check1(ListNode node1,ListNode node2){
        //判断这两棵树是不是完全相同
        if(node1==null && node2==null){
            return true;
        }else if(node1==null && node2!=null){
            return false;
        }else if(node1!=null && node2==null){
            return false;
        }
        if(node1.val!=node2.val){
            return false;
        }
        return (check1(node1.left,node2.left) && check1(node1.right,node2.right));
    }

    public static boolean judge2(ListNode node1,ListNode node2){
        //思路:先按照先序遍历将两个变为一个字符串数组
        ArrayList<String> str1 = turnArr(node1);
        ArrayList<String> str2 = turnArr(node2);
        String[] arr1=new String[str1.size()];
        String[] arr2=new String[str2.size()];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i]=str1.get(i);
        }
        for(int i=0;i<arr2.length;i++){
            arr2[i]=str2.get(i);
        }
        int[] next = getNext(arr2);
        int x=0,y=0;
        while (x<arr1.length && y<arr2.length){
            if(check2(arr1[x],arr2[y])){
                x++;
                y++;
            }else if(y>0){
                y=next[y];
            }else{
                x++;
            }
        }
        return y==arr2.length;
    }

    public static boolean check2(String str1,String str2){
        if(str1==null && str2==null){
            return true;
        }else if(str1==null){
            return false;
        }else if(str2==null){
            return false;
        }
        return str1.equals(str2);
    }

    public static int[] getNext(String[] match){
        if(match.length==1){
            return new int[]{-1};
        }
        int[] arr=new int[match.length];
        arr[0]=-1;arr[1]=0;
        int p=0;
        int i=2;
        while (i<arr.length){
            if(match[i-1]==match[p]){
                arr[i++]=++p;
            }else if(p>0){
                p=arr[p];
            }else{
                i++;
            }
        }
        return arr;
    }

    public static ArrayList<String> turnArr(ListNode node){
        ArrayList<String> list=new ArrayList<>();
        preOrd(list,node);
        return list;
    }

    public static void preOrd(ArrayList<String> list,ListNode node){
        if(node==null){
            list.add(null);
            return;
        }
        preOrd(list,node.left);
        list.add(node.val+"");
        preOrd(list,node.right);
    }

    public static void pre(ListNode head){
        if(head==null){
            return;
        }
        System.out.println(head.val);
        if(head.left!=null){
            pre(head.left);
        }
        if(head.right!=null){
            pre(head.right);
        }
    }
}
