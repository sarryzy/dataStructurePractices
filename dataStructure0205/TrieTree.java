package com.zhangyu.datastructure.dataStructure0205;

public class TrieTree {
    public static void main(String[] args){
        TrieTree tree=new TrieTree();
        tree.add("as");
        tree.add("ss");
        tree.add("s");
        tree.add("s");
        tree.add("sb");
        tree.add("sb");
        tree.add("sa");
        int s = tree.search("sss");
        tree.delete("s");
        System.out.println(s);
    }
    private Node root;

    public TrieTree(){
        root=new Node();
    }

    public void add(String s){
        if(s==null){
            return ;
        }
        char[] chars = s.toCharArray();
        int index=0;
        Node node=root;
        for (int i = 0; i < chars.length; i++) {
            index=chars[i]-'a';
            if(node.nodes[index]==null){
                node.nodes[index]=new Node();
            }
            node=node.nodes[index];
            node.pass++;
        }
        node.end++;
    }

    /**
     * 查找一个字符串出现了几次,
     * @param s
     * @return
     */
    public int search(String s){
        if(s==null){
            return 0;
        }
        char[] chars = s.toCharArray();
        int index=0;
        Node node=root;
        for (int i = 0; i < chars.length; i++) {
            index=chars[i]-'a';
            if(node.nodes[index]==null){
                return 0;
            }
            node=node.nodes[index];
        }
        return node.end;
    }

    public boolean delete(String s){
        if(s==null || search(s)==0){
            return false;
        }
        char[] chars = s.toCharArray();
        int index=0;
        Node node=root;
        for (int i = 0; i < chars.length; i++) {
            index=chars[i]-'a';
            if(--node.nodes[index].pass==0){
                node.nodes[index]=null;
                return true;
            }
//            if(i== chars.length-1){
//                if(--node.nodes[index].end==0){
//                    node.nodes[index]=null;
//                }
//            }
            node=node.nodes[index];
        }
        node.end--;
        return true;
    }
}

class Node{
    public int pass;
    public int end;
    public Node[] nodes;

    public Node() {
        nodes=new Node[26];
    }
}
