package com.zhangyu.datastructure.dataStructure0213;


import java.util.ArrayList;

public class practice01 {
    public static void main(String[] args){
        int[][] arr={{1,0,1,1},{1,1,1,1,},{1,1,1,0}};
        getTheMaxMatrix(arr);
    }

    /**
     * ### 6.求最大子矩阵的大小
     *
     * 程序员代码面试指南p26
     *
     * 思路:要求一个最大子矩阵的大小,每一层每一层的统计,如果这一层上的数为1则把上一层的数字累加下来,
     * 如果这一层的数字为0则直接置为0;这样求最大值的问题就变成了求每一层的这个最大值的大小.
     *
     * 在求每一层的最大值的时候,利用单调栈结构,求最大值.
     * @param num
     * @return
     */
    public static void getTheMaxMatrix(int[][] num){
        //首先求得每一层的
        int[] arr=new int[num[0].length];
        int max=0;
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num[0].length; j++) {
                arr[j]=num[i][j]==0?0:arr[j]+num[i][j];

            }
        }
    }

    /**
     * //现在需要计算每一层的最大值,利用单调栈结构来进行求解
     *                 Stack<Integer> stack=new Stack<>();
     *                 for (int k = 0; k < arr.length; k++) {
     *                     while (!stack.isEmpty() && arr[k])
     *                 }
     */
    public static void getTheMaxArea(int[] arr){

    }

    public static void t5(){
        Employee e1=new Employee(1);
        Employee e2=new Employee(2);
        Employee e3=new Employee(3);
        Employee e4=new Employee(4);
        Employee e5=new Employee(5);
        e1.list.add(e2);
        e1.list.add(e3);
        e3.list.add(e4);
        e3.list.add(e5);
        int theMaxHappy = getTheMaxHappy(e1);
        System.out.println(theMaxHappy);

    }

    public static void t3(){
        ListNode node1=new ListNode(2);
        ListNode node2=new ListNode(1);
        ListNode node3=new ListNode(4);
        ListNode node4=new ListNode(3);
        ListNode node5=new ListNode(5);
        node1.left=node2;
        node1.right=node3;
        node3.left=node4;
        node3.right=node5;
        ListNode theMostBSTNode = getTheMostBSTNode(node1);
        System.out.println(theMostBSTNode);
    }

    public static void t1(){
        ListNode node1=new ListNode(1);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(3);
        ListNode node4=new ListNode(4);
        ListNode node5=new ListNode(5);
        node1.left=node2;
        node1.right=node3;
        node3.left=node4;
        node3.right=node5;
        boolean b = IsBST(node1);
        System.out.println(b);
    }

    public static void t2(){
        ListNode node1=new ListNode(1);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(3);
        ListNode node4=new ListNode(4);
        ListNode node5=new ListNode(5);
        node1.left=node2;
        node1.right=node3;
        node3.left=node4;
        node3.right=node5;
        int theMaxDistance = getTheMaxDistance(node1);
        System.out.println(theMaxDistance);
    }

    public static class ListNode{
        int val;
        ListNode left;
        ListNode right;

        public ListNode (int val){
            this.val=val;
        }
    }

    /**
     * ### 1.一张纸条对折出现折痕
     *
     * 对折之后出现一个凹折痕,再次对折,在这个凹折痕的上面出现凹折痕,下面出现凸折痕,在凸折痕的上方出现凹折痕,下方出现凸折痕.
     *
     * 解决办法:看做二叉树的中序遍历
     * @param times 为对折次数
     */
    public static void paperFolded(int times){
        process1(1,times,0);
    }

    //num为1表示凸,为0表示凹
    public static void process1(int n,int times,int num){
        if(n==times){
            return;
        }
        process1(n+1,times,0);
        System.out.println(num==0?"凹":"凸");
        process1(n+1,times,1);
    }


    /**思路:二叉树头结点向其两个节点要信息,如果两个都是平衡二叉树,并且两个的高度之差小于等于1,那么这个节点也满足平衡二叉树的条件.
     *
     * @param head
     * @return
     */
    public static boolean IsBST(ListNode head){
        return process2(head).isbst;
    }

    public static Info1 process2(ListNode node){
        if(node==null){
            return new Info1(true,0);
        }
        Info1 info1 = process2(node.left);
        Info1 info2 = process2(node.right);
        boolean isbst= info1.isbst&& info2.isbst && (Math.abs(info2.height-info1.height)<2);
        int height=Math.max(info1.height,info2.height);
        return new Info1(isbst,height);
    }

    /**
     * 需要的信息:是否是平衡二叉树,高度是多少
     */
    public static class Info1{
        boolean isbst;
        int height;

        public Info1(boolean isbst, int height) {
            this.isbst = isbst;
            this.height = height;
        }
    }

    public static class Info2{
        int height;
        int maxDistance;

        public Info2(int height, int maxDistance) {
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }

    /**
     * ### 3.给定一颗二叉树的头结点,任何两个节点之间都存在距离,返回整颗二叉树的最大距离
     *
     * 思路:从头结点开始向两颗子树要信息.距离存在两种情况,一种是和头结点没有关系,另一种是和头结点有关系.
     *
     * 如果和头结点没有关系,那么说明最大值就是子节点的最大值.
     *
     * 如果和头结点有关系,那么说明最大值就是两颗子树的高度之和+1.
     *
     * 综上所述,最大值就是在数学上求左子树的最大值,右子树的最大值和二者高度之和再加一这三者的最大值.
     *
     * 头结点需要向子树获取的信息有:子树的高度,子树的距离最大值.
     * @param head
     * @return
     */
    public static int getTheMaxDistance(ListNode head){
        return process3(head).maxDistance;
    }

    public static Info2 process3(ListNode head){
        if(head==null){
            return new Info2(0,0);
        }
        Info2 info1 = process3(head.left);
        Info2 info2 = process3(head.right);
        int maxDist=Math.max(Math.max(info1.maxDistance, info2.maxDistance),info1.height+info2.height+1);
        int height=Math.max(info1.height, info2.height)+1;
        return new Info2(height,maxDist);
    }

    /**
     * ### 4.给定一颗二叉树的头结点head,返回这颗二叉树中最大的二叉搜索子树的头结点
     *
     * 思路:搜索二叉树指的就是中序遍历严格递增的二叉树.
     *
     * 给定头结点,要求其左子树是搜索二叉树,右子树是搜索二叉树,并且左子树的最大值<头结点的值<右子树的最小值.
     *
     * 头结点需要向子树要的信息:是否是搜索二叉树,,要左子树的最大值,右子树的最小值,以及总共的大小是多少.
     */
    public static int getTheMostBSTNodeSize(ListNode head){
        return process4(head).maxSize;
    }

    /**
     * 现在已经知道了整个二叉树的最大的size是多少,现在只需要找到哪一个的size是这么多,并且其左右子树都是bst,并且它的值也满足要求就可以了
     * @param head
     * @return
     */
    public static ListNode getTheMostBSTNode(ListNode head){
        int theMostBSTNodeSize = getTheMostBSTNodeSize(head);
        return null;
    }

    public static int process5(ListNode head){
        return 0;
    }

    public static class Info4{
        boolean isSearchTree;
        int maxSize;
        int min;
        int max;

        public Info4(boolean isSearchTree, int maxSize, int min, int max) {
            this.isSearchTree = isSearchTree;
            this.maxSize = maxSize;
            this.min = min;
            this.max = max;
        }
    }


    public static Info4 process4(ListNode head){
        if(head==null){
            return null;//如果决定为Null之后,之后的判断需要特别注意
        }
        Info4 info1 = process4(head.left);
        Info4 info2 = process4(head.right);
        int min=head.val;//首先让最小值等于自己,防止左右树为空
        int max=head.val;
        int maxSize=0;
        if(info1!=null){
            min=Math.min(min,info1.min);
            max=Math.max(max,info1.max);
            maxSize=Math.max(maxSize,info1.maxSize);
        }
        if(info2!=null){
            min=Math.min(min,info2.min);
            max=Math.max(max,info2.max);
            maxSize=Math.max(maxSize,info2.maxSize);
        }
        boolean isSearchtree=false;
        if((info1==null?true:(info1.isSearchTree && info1.max<head.val)) &&
                (info2==null?true:(info2.isSearchTree&& info2.min>head.val))
        ){
            isSearchtree=true;
            maxSize=(info1==null?0:info1.maxSize)+(info2==null?0: info2.maxSize)+1;
        }
        return new Info4(isSearchtree,maxSize,min,max);
    }

    /**
     * ### 5.派对的最大快乐值问题
     *
     * 一个人可以管理很多的员工,每个员工有给定的快乐值.如果给一个员工的上级发了请帖,那么这个上级的员工就都不会参加派对.求员工们的最大快乐值.
     * @param
     * @return
     */
    public static class Employee{
        int happyVal;
        ArrayList<Employee> list;

        public Employee(int happyVal) {
            this.happyVal = happyVal;
            list=new ArrayList<>();
        }
    }

    /**
     * 思路:准备一个信息类,这个类记录员工来或者不来的快乐值.当现在需要得到一个人的快乐值的时候,
     * 他来的时候的快乐值等于他自己的快乐值加上他的员工都不来的快乐值,
     * 当他不来的时候的快乐值等于它的员工来或者不来的快乐值中较大的那一个相加起来,这样就能够得到最终的最大的快乐值.
     * @param boss
     * @return
     */
    public static int getTheMaxHappy(Employee boss){
        return Math.max(process5(boss).yes,process5(boss).no);
    }

    public static Info5 process5(Employee boss){
        if(boss==null){
            return new Info5(0,0);
        }
        if(boss.list==null){
            return new Info5(boss.happyVal,0);
        }
        int yes=boss.happyVal;
        int no=0;
        for (Employee employee : boss.list) {
            Info5 info = process5(employee);
            yes+=info.no;
            no+= Math.max(info.no,info.yes);
        }
        return new Info5(yes,no);
    }

    public static class Info5{
        int yes;//表示这个节点来的情况下的最大值
        int no;//表示这个节点不来的情况下的最大值

        public Info5(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }



}
