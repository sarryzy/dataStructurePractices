package com.zhangyu.datastructure.dataStructure0216;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class getTheTrueUsers {
    public static void main(String[] args){
        User u1=new User("1","1","1");
        User u2=new User("2","2","2");
        User u3=new User("3","3","3");
        ArrayList<User> list=new ArrayList<>();
        list.add(u1);
        list.add(u2);
        list.add(u3);
        int theNumbers = getTheNumbers(list);
        System.out.println(theNumbers);
    }
    public static class User{
        String a;
        String b;
        String c;

        public User(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    //get the number of the true users

    /**
     * @list contains the information of the users,which contains a,b,and c
     * if one of a,b,c is the same, then treat them as the same
     * @return
     */
    public static int getTheNumbers(List<User> list){
        UnionSet<User> set=new UnionSet<>(list);
        //use 3 maps to main the 3 strings
        HashMap<String,User> map1=new HashMap<>();
        HashMap<String,User> map2=new HashMap<>();
        HashMap<String,User> map3=new HashMap<>();
        for (User user : list) {
            if(map1.containsKey(user.a)){
                set.union(user,map1.get(user.a));
            }else{
                map1.put(user.a,user);
            }
            if(map2.containsKey(user.b)){
                set.union(user,map2.get(user.b));
            }else{
                map2.put(user.b,user);
            }
            if(map3.containsKey(user.c)){
                set.union(user,map3.get(user.c));
            }else{
                map3.put(user.c,user);
            }
        }
        return set.sizeMap.size();
    }

    public static class Node<V>{
        V v;
        public Node(V v){
            this.v=v;
        }
    }

    public static class UnionSet<V>{
        public HashMap<V, Node<V>> nodes;//记录每个值对应的节点
        public HashMap<Node<V>, Node<V>> parents;//记录每个节点对应的父节点
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
