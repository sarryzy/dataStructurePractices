package com.zhangyu.datastructure.dataStructure0225;

import java.util.LinkedList;
import java.util.Queue;

public class unleashBinaryTree {
    public static void main(String[] args){

    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

    public static void unleash(TreeNode root){
        Queue<TreeNode> queue=new LinkedList<>();
        preOrder(queue,root);
        System.out.println(queue);
        TreeNode head=queue.poll();
        TreeNode cur=head;
        while (!queue.isEmpty()){
            cur.left=null;
            cur.right=queue.poll();
            cur=cur.right;
        }
    }

    public static void preOrder(Queue<TreeNode> queue,TreeNode root){
        if(root==null){
            return;
        }
        queue.add(root);
        preOrder(queue,root.left);
        preOrder(queue,root.right);
    }
}
