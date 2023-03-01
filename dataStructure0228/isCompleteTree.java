package com.zhangyu.datastructure.dataStructure0228;
import com.zhangyu.datastructure.dataStructure0228.isFullBinaryTree;

import java.util.LinkedList;

public class isCompleteTree {
    public static void main(String[] args){
        t1();
    }

    public static boolean isComplete1(TreeNode head){
        if(head==null){
            return true;
        }
        LinkedList<TreeNode> list=new LinkedList<>();
        list.add(head);
        boolean flag=false;
        TreeNode left=null;
        TreeNode right=null;
        while (!list.isEmpty()){
            TreeNode node=list.poll();
            left=node.left;
            right=node.right;
            if((left==null && right!=null)||(flag&&(left!=null||right!=null))){
                return false;
            }
            if(left!=null){
                list.add(left);
            }
            if(right!=null){
                list.add(right);
            }
            if(left==null || right==null){
                flag=true;//开关开启,之后的节点都必须要为叶节点
            }
        }
        return true;
    }

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val){
            this.val=val;
        }
    }

    public static boolean isComplete2(TreeNode TreeNode){
        return process(TreeNode).isAns;
    }

    public static class Info{
        int height;
        boolean isFull;
        boolean isAns;

        public Info(int height, boolean isFull, boolean isAns) {
            this.height = height;
            this.isFull = isFull;
            this.isAns = isAns;
        }
    }

    public static Info process(TreeNode TreeNode){
        if(TreeNode==null){
            return new Info(0,true,true);
        }
        Info info1 = process(TreeNode.left);
        Info info2 = process(TreeNode.right);
        int height=Math.max(info1.height,info2.height)+1;
        boolean isFull= info1.isFull && info2.isFull && info1.height== info2.height;
        boolean isAns= isFull;
        if(info1.isAns && info2.isAns){
            if((info1.isAns && info2.isFull && info1.height-info2.height==1)||
                    (info1.isFull && info2.isFull && info1.height-info2.height==1)||
                    (info1.isFull && info2.isAns && info1.height==info2.height)){
                isAns=true;
            }
        }
        return new Info(height,isFull,isAns);
    }

    //随机生成二叉树
    public static TreeNode generateBinaryTree(int level,int maxVal,int maxLevel){
        if(level>maxLevel){
            return null;
        }
        int val=(int)(Math.random()*maxVal);
        TreeNode cur=new TreeNode(val);
        cur.left=generateBinaryTree(level+1,maxVal,maxLevel);
        cur.right=generateBinaryTree(level+1,maxVal,maxLevel);
        return cur;
    }

    public static void t1(){
        int testTimes=10000;
        int maxVal=100;
        int maxLevel=15;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateBinaryTree(1, maxVal, maxLevel);
            if(isComplete1(head)!=isComplete2(head)){
                System.out.println("出错了");
            }
            System.out.println("i:"+i);
        }
        System.out.println("成功了");
    }
}
