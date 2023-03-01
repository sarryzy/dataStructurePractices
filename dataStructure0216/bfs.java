package com.zhangyu.datastructure.dataStructure0216;

import java.util.*;

public class bfs {
    //实现宽度优先遍历
    public static class Node{
        int val;
        int out;
        int in;
        ArrayList<Node> nexts;

        public Node(int val) {
            this.val = val;
            this.out=out;
            this.in=in;
            nexts=new ArrayList<>();
        }
    }

    /**
     * 宽度优先遍历,弹出再打印
     * @param node
     */
    public static void bfs1(Node node){
        //准备一个队列和一个集合,集合中记录已经存放过的节点
        Queue<Node> queue=new LinkedList<>();
        HashSet<Node> set=new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()){
            Node n = queue.poll();
            System.out.println(n.val);
            for (Node next : n.nexts) {
                if(!set.contains(next)){
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }

    /**
     * 深度优先遍历,准备一个栈和一个集合,首先将头结点放入栈
     * 把栈顶元素弹出并记录,将其后代节点依次压入,如果这个节点没有被存进过,那么直接压入弹出的元素和这个,直接break,继续这个节点的深度
     * 深度优先搜索,进去就打印
     * @param head
     */
    public static void dfs1(Node head){
        if(head==null){
            return;
        }
        Stack<Node> stack=new Stack<>();
        HashSet<Node> set=new HashSet<>();
        stack.add(head);
        set.add(head);
        System.out.println(head.val);
        while (!stack.isEmpty()){
            Node node = stack.pop();//先把这个节点记下来
            for (Node next : node.nexts) {
                if(!set.contains(next)){
                    stack.push(node);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.val);
                    break;
                }
            }
        }
    }

    public static List sortedTopology(Graph graph){
        List<Graph.Node> list=new ArrayList<>();
        HashMap<Graph.Node,Integer> map=new HashMap<>();
        Queue<Graph.Node> queue=new LinkedList<>();
        for (Graph.Node value : graph.nodes.values()) {
            //将所有节点的入度添加到哈希表里面
            map.put(value, value.in);
            if(map.get(value)==0){
                queue.add(value);
                list.add(value);
            }
        }
        while (!queue.isEmpty()){
            //当队列不为空的时候,弹出里面的所有节点,并且删除他们对应的出度对应节点的入度
            Graph.Node node = queue.poll();
            for (Graph.Node next : node.nexts) {
                map.put(next, next.in-1);
                if(map.get(next)==0){
                    queue.add(next);
                    list.add(node);
                }
            }
        }
        return list;
    }
}
