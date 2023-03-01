package com.zhangyu.datastructure.dataStructure0216;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    public HashMap<Integer,Node> nodes;
    public HashSet<Edge> edges;

    public Graph(){
        nodes=new HashMap<>();
        edges=new HashSet<>();
    }


    public static class Node{
        public int val;
        public int in;
        public int out;
        public ArrayList<Node> nexts;
        public ArrayList<Edge> edges;

        public Node(int val) {
            this.val = val;
            this.in = 0;
            this.out = 0;
            this.nexts=new ArrayList<>();
            this.edges=new ArrayList<>();
        }
    }

    public static class Edge{
        public int weight;
        public Node from;
        public Node to;

        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }
}
