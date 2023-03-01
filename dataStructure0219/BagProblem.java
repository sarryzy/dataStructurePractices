package com.zhangyu.datastructure.dataStructure0219;

public class BagProblem {
    public static void main(String[] args){
        int[] w={1,2,3,4,2,1,2,3,4,5,4,3,2};
        int[] v={1,2,4,2,3,5,4,3,2,1,32,4,5};
        int bag=4;
        int maxValue = getMaxValue(w, v, bag);
        int maxValue1 = getMaxValue1(w, v, bag);
        System.out.println(maxValue);
        System.out.println(maxValue1);
    }

    /**
     * ### 7.给定两个长度为N的数组重量和价值,给定一个背包,返回你能够装下的最多的价值.
     *
     * 递归思路:对当前位置的货物进行尝试,要或者不要,返回要或者不要的最大值.
     * ### 7.给定两个长度为N的数组重量和价值,给定一个背包,返回你能够装下的最多的价值.
     *
     * 递归思路:对当前位置的货物进行尝试,要或者不要,返回要或者不要的最大值.如果当前的空间已经小于0了,
     * 说明这种方法不成立,是无效方案,直接返回-1.剩下有两种选择,一种是选择当前的货物.
     * 也可以不使用通用条件判断,如果当前值比当前货物的重量大的时候,才能够选择yes分支.
     * @param w
     * @param v
     * @param bag
     * @return
     */
    public static int getMaxValue(int[] w,int[] v,int bag){
        return process(w,v,0,bag);
    }

    public static int process(int[] w,int[] v,int index,int space){
        if(space<0){
            //是无效方案
            return -1;
        }
        if(index==w.length){
            //已经没有货物可以选择了
            return 0;
        }
        int no=process(w,v,index+1,space);
        int yes=Integer.MIN_VALUE;//先假设要当前货物是无效的
        if(process(w,v,index+1,space-w[index])!=-1){
            //方法有效
            yes=v[index]+process(w,v,index+1,space-w[index]);
        }
        return Math.max(yes,no);
    }

    public static int getMaxValue1(int[] w,int[] v,int bag){
        return process1(w,v,0,bag);
    }

    public static int process1(int[] w,int[] v,int i,int bag){
        //不使用-1作为特殊条件
        if(bag<=0){
            return 0;
        }
        if(i==w.length){
            return 0;
        }
        int no=process1(w,v,i+1,bag);
        int yes=Integer.MIN_VALUE;
        if(bag>=w[i]){
            yes=v[i]+process1(w,v,i+1,bag-w[i]);
        }
        return Math.max(yes,no);
    }
}
