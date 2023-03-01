package com.zhangyu.datastructure.dataStructure0216;

import com.zhangyu.datastructure.dataStructure0216.Graph.*;

import java.util.*;

public class Kruskal {
//    public static class Node{
//        int val;
//        int in;
//        int out;
//        ArrayList<Node> nexts;
//        ArrayList<Graph.Edge> edges;
//    }

    public static class UnionFind{
        public HashMap<Node,Node> fatherMap;
        public HashMap<Node,Integer> sizeMap;

        public UnionFind(){
            fatherMap=new HashMap<>();
            sizeMap=new HashMap<>();
        }

        public void makeSets(Collection<Node> nodes){
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node,node);
                sizeMap.put(node,1);
            }
        }

        public Node findFather(Node node){
            Stack<Node> stack=new Stack<>();
            while (node!=fatherMap.get(node)){
                stack.push(node);
                node=fatherMap.get(node);
            }
            while (!stack.isEmpty()){
                fatherMap.put(stack.pop(),node);
            }
            return node;
        }

        public boolean isSameSet(Node node1,Node node2){
            return findFather(node1)==findFather(node2);
        }

        public void union(Node node1,Node node2){
            if(node1==null || node2==null){
                return;
            }
            Node f1 = findFather(node1);
            Node f2 = findFather(node2);
            while (f1!=f2){
                if(sizeMap.get(f1)>=sizeMap.get(f2)){
                    //当f1的size较大的时候,将f2并入f1
                    fatherMap.put(f2,f1);
                    sizeMap.put(f1,sizeMap.get(f1)+sizeMap.get(f2));
                    sizeMap.remove(f2);
                }else{
                    fatherMap.put(f1,f2);
                    sizeMap.put(f2,sizeMap.get(f1)+sizeMap.get(f2));
                    sizeMap.remove(f1);
                }
            }
        }
    }

    /**
     * ### 5.最小生成树Kruskal算法
     *
     * 按照边的权值将所有的边排序,每次弹出最小的边看看有没有将点连起来,如果是则直接加入.使用并查集判断是否连通.
     *
     * 并查集擅长判断两个集合是否是同一集合.
     * @param graph
     */
    public static List<Graph.Edge> kruskal(Graph graph){
        List<Graph.Edge> list=new ArrayList<>();
        PriorityQueue<Graph.Edge> heap=new PriorityQueue<>(new Comparator<Graph.Edge>() {
            @Override
            public int compare(Graph.Edge o1, Graph.Edge o2) {
                return o1.weight-o2.weight;
            }
        }) ;
        UnionFind uf=new UnionFind();
        uf.makeSets(graph.nodes.values());
        for (Graph.Edge edge : graph.edges) {
            heap.add(edge);
        }
        while (!heap.isEmpty()){
            Graph.Edge edge = heap.poll();
            if(!uf.isSameSet(edge.from,edge.to)){
                //如果不是一个集合就加入
                list.add(edge);
                uf.union(edge.from, edge.to);
            }
        }
        return list;
    }

    /**
     * ### 6.Prim算法
     *
     * 从一个节点出发开始解锁,解锁与其相邻的边,从已经解锁的边里面选择一个最小的,
     * 看其有没有解锁新的节点.解锁了新的节点后,再看有没有解锁新的边.
     * @param graph
     */
    public static List<Edge> primMST(Graph graph){
        List<Edge> list=new ArrayList<>();
        HashSet<Edge> edgeSet=new HashSet<>();//用于标记边是否放入过
        PriorityQueue<Edge> heap=new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight-o2.weight;
            }
        });
        HashSet<Node> nodeSet=new HashSet<>();
        for (Node node : graph.nodes.values()) {//防森林
            if(!nodeSet.contains(node)){
                nodeSet.add(node);
                for (Edge edge : node.edges) {
                    if(!edgeSet.contains(edge)){
                        //不存在才加
                        edgeSet.add(edge);
                        heap.add(edge);
                        list.add(edge);
                    }
                }
                while (!heap.isEmpty()){
                    Edge edge = heap.poll();//当前最小的边
                    if(!nodeSet.contains(edge.to)){
                        //如果有新节点
                        nodeSet.add(edge.to);
                        list.add(edge);
                        for (Edge edge1 : edge.to.edges) {
                            if(!edgeSet.contains(edge1)){
                                heap.add(edge1);
                            }
                        }
                    }
                }
            }
        }
        return list;
    }
}
