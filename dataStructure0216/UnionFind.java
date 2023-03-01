package com.zhangyu.datastructure.dataStructure0216;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionFind {

    public static class Node<V>{
        V v;
        public Node(V v){
            this.v=v;
        }
    }

    public static class UnionSet<V>{
        public HashMap<V,Node<V>> nodes;//记录每个值对应的节点
        public HashMap<Node<V>,Node<V>> parents;//记录每个节点对应的父节点
        public HashMap<Node<V>,Integer> sizeMap;//记录父节点的大小

        public UnionSet(List<V> values){
            nodes=new HashMap<>();
            parents=new HashMap<>();
            sizeMap=new HashMap<>();
            for(V v:values){
                Node<V> node=new Node<>(v);
                nodes.put(v,node);
                parents.put(node,node);
                sizeMap.put(node,1);
            }
        }

        public Node<V> findFather(Node<V> cur){
            Stack<Node<V>> stack=new Stack<>();
            while (cur!=parents.get(cur)){
                //当当前节点不是自己的父节点的时候
                stack.push(cur);
                cur=parents.get(cur);
            }
            while (!stack.isEmpty()){
                parents.put(stack.pop(),cur);
            }
            return cur;
        }

        public boolean isSameSet(V a,V b){
            if(!nodes.containsKey(a) || !nodes.containsKey(b)){
                return false;
            }
            return findFather(nodes.get(a))==findFather(nodes.get(b));
        }

        public void union(V a,V b){
            if(!nodes.containsKey(a) || !nodes.containsKey(b)){
                return;
            }
            Node<V> f1 = findFather(nodes.get(a));
            Node<V> f2 = findFather(nodes.get(b));
            if(f1!=f2){
                if(sizeMap.get(f1)>=sizeMap.get(f2)){
                    parents.put(f2,f1);
                    sizeMap.put(f1,sizeMap.get(f1)+sizeMap.get(f2));
                    sizeMap.remove(f2);
                }else{
                    parents.put(f1,f2);
                    sizeMap.put(f2,sizeMap.get(f1)+sizeMap.get(f2));
                    sizeMap.remove(f1);
                }
            }
        }
    }
}
