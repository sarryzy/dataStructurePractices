package com.zhangyu.datastructure.dataStructure0217;


import com.zhangyu.datastructure.dataStructure0216.Graph.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class dijkstra {
    /**
     * ### 1.迪杰特斯拉算法
     *
     * 给定一个点,求这个点到其他节点的最短距离.
     *
     * 解法:从头结点出发,找到距离自己所有的点,再找到距离最近的点,并且没有选择过的点,继续通过这个点的边进行寻找.
     */
    public static HashMap<Node,Integer> getTheMinDistance(Node head){
        HashSet<Node> set=new HashSet<>();
        HashMap<Node,Integer> map=new HashMap<>();
        map.put(head,0);
        Node node = getTheNode(map, set);
        while (node!=null){
            //当节点不为空的时候
            set.add(node);
            for (Edge edge : node.edges) {
                if(map.containsKey(edge.to)){
                    map.put(edge.to, map.get(node)+edge.weight);
                }else{
                    map.put(edge.to,Math.min(edge.weight, map.get(node)+map.get(edge.to)));
                }
            }
            node=getTheNode(map,set);
        }
        return map;
    }

    public static Node getTheNode(HashMap<Node,Integer> map,HashSet<Node> set){
        //找到哈希表中距离最小的节点,并且其在set中没有出现过
        Node node=null;
        int value=Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : map.entrySet()) {
            Node node1=entry.getKey();
            Integer val = entry.getValue();
            if(val<value && !set.contains(node1)){
                node=node1;
                value=val;
            }
        }
        return node;
    }
}
