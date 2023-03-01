package com.zhangyu.datastructure.dataStructure0228;

import com.zhangyu.datastructure.dataStructure0216.Graph;

public class isFullBinaryTree {
    public static class Node{
        int val;
        Node left;
        Node right;

        public Node(int val){
            this.val=val;
        }
    }

    public static class Info{
        int height;
        int node;

        public Info(int height,int node){
            this.height=height;
            this.node=node;
        }
    }

    public static boolean judge(Node node){
        if(node==null){
            return true;
        }
        Info info = process(node);
        return (1<<info.height)-info.node==1;
    }

    public static Info process(Node node){
        if(node==null){
            return new Info(0,0);
        }
        Info info1=process(node.left);
        Info info2=process(node.right);
        int height=Math.max(info1.height,info2.height)+1;
        int nodes=info1.node+info2.node+1;
        return new Info(height,nodes);
    }
}
