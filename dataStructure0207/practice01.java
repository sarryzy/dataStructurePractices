package com.zhangyu.datastructure.dataStructure0207;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class practice01 {

    public static void main(String[] args){
        t1();
    }

    public static void t1(){
        Node node1=new Node(1);
        Node node2=new Node(2);
        Node node3=new Node(3);
        Node node4=new Node(4);
        Node node5=new Node(5);
        Node node6=new Node(6);
        Node node7=new Node(7);
        node1.left=node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.left=node6;
        node3.right=node7;
        Queue<String> queue = floorSerialize1(node1);
        Node node = floorSerialize2(queue);
        preOrder2(node);
    }

    public static class Node{
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return this.val+"";
        }
    }

    /**
     * ### 1.不使用递归实现二叉树先序中序后序:
     *
     * 先序:利用栈实现,弹出就打印,如果有右,压入右,如果有左,压入左.
     *
     * 后序:先使用头右左实现,再倒入一个栈中,这样就实现左右头.使用两个栈
     *      * 后序不使用两个栈的方法:使用一个变量记住当前打印的值,判断这个值是不是当前栈弹出的值的左节点或者右节点,如果
     *
     * 中序:整条左边界依次入栈,弹出就打印,再来到右结点
     *
     */
    public static void preOrder1(Node head){
        if(head==null){
            return ;
        }
        System.out.print(head.val+" ");
        preOrder1(head.left);
        preOrder1(head.right);
    }

    public static void preOrder2(Node head){
        if(head==null){
            return ;
        }
        Stack<Node> stack=new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()){
            Node node = stack.pop();
            System.out.print(node.val+" ");
            if(node.right!=null){
                stack.push(node.right);
            }
            if(node.left!=null){
                stack.push(node.left);
            }
        }
    }

    public static void posOrder1(Node head){
        if(head==null){
            return ;
        }
        Stack<Node> stack1=new Stack<>();
        Stack<Node> stack2=new Stack<>();
        stack1.push(head);
        while (!stack1.isEmpty()){
            Node node=stack1.pop();
            stack2.push(node);
            if(node.left!=null){
                stack1.push(node.left);
            }
            if(node.right!=null){
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()){
            System.out.print(stack2.pop()+" ");
        }
    }

    public static void posOrder2(Node head){
        if(head==null){
            return;
        }
        Stack<Node> stack=new Stack<>();
        stack.push(head);
        Node p1=head;
        Node p2=null;//记录上一个打印的节点
        while (!stack.isEmpty()){
            p1=stack.peek();
            if(p1.left!=null && p2!=p1.left && p2!=p1.right){
                //想要添加左节点
                stack.push(p1.left);
            }else if(p1.right!=null && p2!=p1.right){
                stack.push(p1.right);
            }else{
                System.out.print(stack.pop()+" ");
                p2=p1;
            }
        }
    }

    public static void infixOrder(Node head){
        if(head==null){
            return;
        }
        Stack<Node> stack=new Stack<>();
        Node cur=head;
        while (!stack.isEmpty() || cur!=null){
            if(cur!=null){
                stack.push(cur);
                cur=cur.left;
            }else{
                cur=stack.pop();
                System.out.print(cur.val+" ");
                cur=cur.right;
            }
        }
    }

    /**
     * ### 2.二叉树的按层遍历:
     *
     * 使用一个队列,
     */
    public static void floorOrder(Node head){
        Queue<Node> queue=new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            Node cur=queue.poll();
            System.out.print(cur.val+" ");
            if(cur.left!=null){
                queue.add(cur.left);
            }
            if(cur.right!=null){
                queue.add(cur.right);
            }
        }
    }

    /**
     * ### 3.二叉树的最大宽度:
     *
     * 简单做法:使用一个map,存入结点和其对应的等级.
     *
     * 复杂做法:不使用map,每次记录每一层的最后一个结点.
     */

    public static int getTheWidth1(Node head){
        if(head==null){
            return 0;
        }
        Queue<Node> queue=new LinkedList<>();
        HashMap<Node,Integer> map=new HashMap<>();
        map.put(head,1);
        queue.add(head);
        int curLevel=1;//记录当前来到的等级
        int curLevelNodes=0;//记录当前等级的节点的个数
        int max=0;
        while (!queue.isEmpty()){
            Node cur=queue.poll();
            int level=map.get(cur);
            if(cur.left!=null){
                map.put(cur.left,level+1);
                queue.add(cur.left);
            }
            if(cur.right!=null){
                map.put(cur.right,level+1);
                queue.add(cur.right);
            }
            //判定结束条件
            if(level==curLevel){
                curLevelNodes++;
            }else{
                //结算
                max=Math.max(max,curLevelNodes);
                curLevel++;
                curLevelNodes=1;
            }
        }
        max=Math.max(max,curLevelNodes);
        return max;
    }

    public static int getTheWidth2(Node head){
        //使用一个变量记住上一层的最后一个节点
        //还有一个记住这一层的最后一个节点
        Queue<Node> queue=new LinkedList<>();
        Node curEnd=head;
        Node nextEnd=null;
        queue.add(head);
        int max=0;
        int curNodes=0;
        while (!queue.isEmpty()){
            Node cur=queue.poll();
            if(cur.left!=null){
                queue.add(cur.left);
                nextEnd=cur.left;
            }
            if(cur.right!=null){
                queue.add(cur.right);
                nextEnd=cur.right;
            }
            curNodes++;
            if(cur==curEnd){
                //到达这一行的最后一个节点,清算
                curEnd=nextEnd;
                max=Math.max(max,curNodes);
                curNodes=0;
            }
        }
        return max;
    }

    /**
     * ### 4.二叉树的递归序序列化
     *
     * 利用递归和队列,每次将头结点的值加进去,null也需要加入.
     *
     * 反序列化:利用递归建左树右树.
     */
    public static Queue<String> treeSerialize1(Node head){
        if(head==null){
            return null;
        }
        Queue<String> queue=new LinkedList<>();
        preProcess(head,queue);
        return queue;
    }

    public static void preProcess(Node head,Queue<String> queue){
        if(head==null){
            queue.add(null);
        }else{
            queue.add(String.valueOf(head.val));
            preProcess(head.left,queue);
            preProcess(head.right,queue);
        }
    }

    //反序列化
    public static Node treeSerialize2(Queue<String> queue){
        Node head=generateNode(queue.poll());
        preProcess2(head,queue);
        return head;
    }

    public static void preProcess2(Node head,Queue<String> queue){
        Node node = generateNode(queue.poll());
        if(node!=null){
            head.left=node;
            preProcess2(head.left,queue);
        }
        node=generateNode(queue.poll());
        if(node!=null){
            head.right=node;
            preProcess2(head.right,queue);
        }
    }

    public static Node treeSerialize3(Queue<String> queue){
        return preProcess3(queue);
    }

    public static Node preProcess3(Queue<String> queue){
        String s = queue.poll();
        if(s==null){
            return null;
        }
        Node head=generateNode(s);
        head.left=preProcess3(queue);
        head.right=preProcess3(queue);
        return head;
    }

    //生成节点
    public static Node generateNode(String s){
        if(s==null){
            return null;
        }else{
            return new Node(Integer.parseInt(s));
        }
    }

    /**
     * ### 5.二叉树的层序列化
     *
     * 利用一个队列装入序列化结果,另一个装入结点.如果左树不为空,则既序列也加入队列,右树同理.
     *
     * 反序列化:
     */
    public static Queue<String> floorSerialize1(Node head){
        Queue<String> queue1=new LinkedList<>();//装入序列化结果
        Queue<Node> queue2=new LinkedList<>();//装入节点
        queue1.add(String.valueOf(head.val));
        queue2.add(head);
        while (!queue2.isEmpty()){
            Node cur=queue2.poll();
            if(cur.left!=null){
                queue1.add(String.valueOf(cur.left.val));
                queue2.add(cur.left);
            }else{
                queue1.add(null);
            }
            if(cur.right!=null){
                queue1.add(String.valueOf(cur.right.val));
                queue2.add(cur.right);
            }else{
                queue1.add(null);
            }
        }
        return queue1;
    }

    public static Node floorSerialize2(Queue<String> queue){
        Node head=generateNode(queue.poll());
        Queue<Node> queue1=new LinkedList<>();
        queue1.add(head);
        while (!queue1.isEmpty()){
            Node cur=queue1.poll();
            Node p1=generateNode(queue.poll());
            if(p1!=null){
                cur.left=p1;
                queue1.add(p1);
            }
            p1=generateNode(queue.poll());
            if(p1!=null){
                cur.right=p1;
                queue1.add(p1);
            }
        }
        return head;
    }
}
