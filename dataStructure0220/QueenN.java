package com.zhangyu.datastructure.dataStructure0220;

public class QueenN {
    public static void main(String[] args){
        int n=13;
        long start=System.currentTimeMillis();
        long end=System.currentTimeMillis();
        start=System.currentTimeMillis();
        int queen1 = getQueen1(n);
        end=System.currentTimeMillis();
        System.out.println("queen1:"+(end-start));
        start=System.currentTimeMillis();
        int queen2 = getQueen2(n);
        end=System.currentTimeMillis();
        System.out.println("queen2:"+(end-start));
        System.out.println(queen1);
        System.out.println(queen2);
    }

    /**
     * ### 2.n皇后
     *
     * 简单思路:考虑当前位置有多少种放置的方法,
     * 如果索引到达了最后一个位置,显然只有一种.否则返回res加上后面的种类.
     *
     * 复杂思路:利用位运算,进行优化.用一个列限制符,左斜线限制符和右斜线限制符进行限制.
     */
    public static int getQueen1(int n){
        int num=n==32?-1:(1<<n)-1;
        return process1(n,0,num,0,0,0);
    }

    /**
     *
     * @param n
     * @param index
     * @param column 列占位符
     * @param leftLine
     * @param rightLine
     * @return
     */
    public static int process1(int n,int index,int num,int column,int leftLine,int rightLine){
        if(n==index){
            return 1;
        }
        int res=0;
        int pos=column | leftLine|rightLine;
        pos=num&(~pos);
        while (pos!=0){
            int rightOne=pos&(~pos+1);
            pos-=rightOne;
            res+=process1(n,index+1,num,
                    (column|rightOne),
                    (leftLine|rightOne)<<1,
                    (rightLine|rightOne)>>>1);

        }
        return res;
    }

    public static int getQueen2(int n){
        int[] arr=new int[n];
        return process2(n,arr,0);
    }

    public static int process2(int n,int[] arr,int index){
        //index表示当前来到的位置
        if(index==arr.length){
            return 1;
        }
        int res=0;
        for (int i = 0; i < n; i++) {
            arr[index]=i;
            if(check(arr,index)){
                res+=process2(n,arr,index+1);
            }
        }
        return res;
    }

    public static boolean check(int[] arr,int k){
        //检测数组第k位上放置的数字是否正确
        for (int i = 0; i < k; i++) {
            if(arr[k]==arr[i] || Math.abs(k-i)==Math.abs(arr[k]-arr[i])){
                return false;
            }
        }
        return true;
    }
}
