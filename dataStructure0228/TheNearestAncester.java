package com.zhangyu.datastructure.dataStructure0228;

import java.util.HashMap;
import java.util.HashSet;

public class TheNearestAncester {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val){
            this.val=val;
        }
    }
    /**
     * 简单思路:首先根据二叉树生成一颗有父节点对应的哈希表.
     * 然后将题目给定的第一个节点的所有父节点放到一个集合中,
     * 再将第二个的父节点依次取出,第一个存在的父节点即为两者的最低公共祖先.
     */

    public static TreeNode getTheNearest(TreeNode head,TreeNode node1,TreeNode node2){
        HashMap<TreeNode,TreeNode> map=new HashMap<>();
        map.put(head,null);
        fillMap(map,head);
        HashSet<TreeNode> set=new HashSet<>();
        while (node1!=null){
            set.add(node1);
            node1=map.get(node1);
        }
        //出来之后说明node1已经达到了头结点,集合中已经包含了所有的父节点
        while (node2!=null){
            if(set.contains(node2)){
                return node2;
            }else{
                node2=map.get(node2);
            }
        }
        return null;
    }

    public static void fillMap(HashMap<TreeNode,TreeNode> map,TreeNode node){
        if(node.left!=null){
            map.put(node.left,node);
            fillMap(map,node.left);
        }
        if(node.right!=null){
            map.put(node.right,node);
            fillMap(map,node.right);
        }
    }



    public static class Info{
        TreeNode ans;//找到答案的最低节点
        boolean findO1;//找到o1了吗
        boolean findO2;//找到o2了吗

        public Info(TreeNode ans,boolean findO1,boolean findO2){
            this.ans=ans;
            this.findO1=findO1;
            this.findO2=findO2;
        }
    }

    /**
     * 复杂思路:利用二叉树的递归套路.讨论在节点上有没有发现两个节点.
     *
     * 需要三个参数,最低相遇的节点,是否发现O1,是否发现O2.
     * @return
     */
    public static TreeNode find2(TreeNode node,TreeNode node1,TreeNode node2){
        return process2(node,node1,node2).ans;
    }

    public static Info process2(TreeNode node,TreeNode node1,TreeNode node2){
        if(node==null){
            return new Info(null,false,false);
        }
        Info info1 = process2(node.left,node1,node2);
        Info info2 = process2(node.right,node1,node2);
        TreeNode ans=null;
        boolean findO1=false,findO2=false;
        if(info1.ans!=null){
            ans=info1.ans;
        }
        if(info2.ans!=null){
            ans=info2.ans;
        }
        findO1=node==node1||info1.findO1||info2.findO1;
        findO2=node==node2||info1.findO2||info2.findO2;
        //如果ans没有更新,isleft和isright都为treu,则说明当前节点即是最低节点
        if(ans==null){
            if(findO1&&findO2){
                ans=node;
            }
        }
        return new Info(ans,findO1,findO2);
    }
}
