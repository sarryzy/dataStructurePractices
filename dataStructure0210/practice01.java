package com.zhangyu.datastructure.dataStructure0210;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class practice01 {

    public static void main(String[] args){
        int[] arr={3,4,1,5,6,2,7};
        int[][] ans = simpleStack(arr);
        printArr(ans);
    }

    public static void printArr(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode detectCycle(ListNode head) {
        if(head==null || head.next==null || head.next.next==null){
            return null;
        }
        ListNode slow=head;
        ListNode fast=head;
        while (fast.next!=null && fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(slow==fast){
                break;
            }
        }
        if(fast.next==null || fast.next.next==null){
            return null;
        }
        fast=head;
        while (fast!=slow){
            fast=fast.next;
            slow=slow.next;
        }
        return fast;
    }

    public ListNode oddEvenList(ListNode head) {
        if(head==null || head.next==null || head.next.next==null){
            return head;
        }
        ListNode oddHead=head;
        ListNode cur1=oddHead;
        ListNode evenHead=head.next;
        ListNode cur2=evenHead;
        ListNode cur=evenHead.next;
        while (cur!=null){
            ListNode next=cur.next;
            cur1.next=cur;
            cur2.next=next;
            cur1=cur1.next;
            cur2=cur2.next;
            if(next==null){
                break;
            }
            if(next.next==null){
                break;
            }
            cur=next.next;
        }
        cur.next=evenHead;
        return oddHead;
    }

    int len;
    ListNode head;


    public int getRandom() {
        ListNode cur=head;
        int r=(int)(Math.random()*len);
        while (r>0){
            cur=cur.next;
            r--;
        }
        return cur.val;
    }

    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode forehead=new ListNode(0);
        forehead.next=head;
        HashMap<Integer,ListNode> map=new HashMap<>();
        ListNode cur=forehead;
        int sum=0;
        while (cur!=null){
            sum+=cur.val;
            map.put(sum,cur);
            cur=cur.next;
        }
        cur=forehead;
        sum=0;
        while (cur!=null){
            sum+=cur.val;
            cur.next=map.get(sum).next;
            cur=cur.next;
        }
        return forehead.next;
    }

    //求出每个滑动窗口的最大值

    /**
     * 思路:维持一个双向队列,遍历数组向其中添加值
     * 如果队列不为空,并且要添加的值比队列里后面的值要大,直接弹出.//相等弹不弹可以考虑
     * 如果比后面的值要小则直接加入
     * 判断队列里的第一个数字满不满足要求,如果超出了索引限制直接弹出,i-w>=队列头的索引则直接弹出
     * 辅助数组填入值,从i-w+1>=0开始
     * @param arr
     * @param w
     * @return
     */
    public static int[] slideWindows(int[] arr,int w){
        if(arr==null || w<1){
            return null;
        }
        int[] help=new int[arr.length-w+1];
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            while (!list.isEmpty() && arr[list.peekLast()]<arr[i]){
                list.pollLast();
            }
            list.addLast(i);
            if(list.peekFirst()<=i-w){
                list.pollFirst();
            }
            if(i-w+1>=0){
                help[i-w+1]=arr[list.peekFirst()];
            }
        }
        return help;
    }

    public static int[] slideWindows2(int[] arr,int w){
        if(arr==null || w<1){
            return null;
        }
        int[] help=new int[arr.length-w+1];
        for (int i = 0; i < arr.length; i++) {
            if(i-w+1>=0){
                int max=arr[i];
                for (int j = i; j >=i-w+1; j--) {
                    max=Math.max(max,arr[j]);
                }
                help[i-w+1]=max;
            }
        }
        return help;
    }

    public static void t1(){
        int times=10000;
        int maxVal=1000;
        int maxLen=1000;
        for (int j = 0; j < times; j++) {
            int[] arr=new int[(int)(maxLen*Math.random())];
            int len=arr.length;
            for (int i = 0; i < arr.length; i++) {
                arr[i]=(int)(Math.random()*maxVal);
            }
            int w=(int)(Math.random()*len);
            int[] arr1 = slideWindows(arr, w);
            int[] arr2 = slideWindows2(arr, w);
            if(!judge(arr1,arr2)){
                System.out.println("出错了...");
                System.out.println(Arrays.toString(arr));
                System.out.println(Arrays.toString(arr1));
                System.out.println(Arrays.toString(arr2));

                break;
            }
            System.out.println(j);
        }
    }


    //true表示正确
    public static boolean judge(int[] arr1,int[] arr2){
        if(arr1==null && arr2==null){
            return true;
        }
        for (int i = 0; i < arr1.length; i++) {
            if(arr1[i]!=arr2[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * 单调栈问题
     * 首先将数组中的数倒入到栈中,如果当前值比栈顶的元素要大,直接加入,如果比栈顶的值要小,弹出,并且为该点的左右边界赋值
     * @param arr
     */
    public static int[][] simpleStack(int[] arr){
        int[][] ans=new int[arr.length][2];
        Stack<Integer> stack=new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while(!stack.isEmpty()&&arr[i]<arr[stack.peek()]){
                int res=stack.pop();
                ans[res][1]=i;
                ans[res][0]=stack.isEmpty()?-1:stack.peek();
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            int index=stack.pop();
            ans[index][1]=-1;
            ans[index][0]=stack.isEmpty()?-1:stack.peek();
        }
        return ans;
    }




}
