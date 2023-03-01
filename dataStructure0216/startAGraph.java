package com.zhangyu.datastructure.dataStructure0216;

public class startAGraph {

    public static void t1(){
        //生成一张图根据给定的矩阵
        //第一个表示权值,第二个表示from,第三个表示to
        int[][] matrix={{1,1,3},{4,2,3},{7,1,2}};

    }

    public static Graph getGraph1(int[][] matrix){
        Graph graph=new Graph();
        for (int i = 0; i < matrix.length; i++) {
            int weight=matrix[i][0];
            int from=matrix[i][1];
            int to=matrix[i][2];
            if(!graph.nodes.containsKey(from)){
                graph.nodes.put(from,new Graph.Node(from));
            }
            if(!graph.nodes.containsKey(to)){
                graph.nodes.put(to,new Graph.Node(to));
            }
            Graph.Node node1 = graph.nodes.get(from);
            Graph.Node node2 = graph.nodes.get(to);
            Graph.Edge edge=new Graph.Edge(weight,node1,node2);
            node1.nexts.add(node2);
            node1.out++;
            node2.in++;
            node1.edges.add(edge);
            graph.edges.add(edge);
        }
        return graph;
    }

}
