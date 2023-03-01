package com.zhangyu.datastructure.dataStructure0219;

public class TakeCards {
    public static void main(String[] args){
        int[] arr={70,100,20,50};
        int theWinnerScores = getTheWinnerScores(arr);
        System.out.println(theWinnerScores);
    }

    /**
     * ### 8.给定一个整型数组arr,代表数值不同的纸牌排成一条线.
     * 玩家A和玩家B依次拿走每张纸牌,A先拿,B后拿.但是每个玩家每次只能拿走最左或者最右的纸牌,返回最后获胜者的分数.
     *
     * 递归方法:构造两个函数,一个是在l到r上先手能够获得的最大值,一个是在l到r上后手能够获得的最小值
     *
     * 先手函数:首先考虑最基本的情况,如果左右边界相等,则直接返回这一个值.否则返回左边的值加上在剩下的区间中后手和右边的值加上剩下的区间中后手的最大值.
     *
     * 后手函数:首先考虑最基本的情况,如果左右边界相等,则直接返回0. 否则返回两者中的最小值.
     */
    public static int getTheWinnerScores(int[] arr){
        int f=f(arr,0, arr.length-1);
        int s=s(arr,0, arr.length-1);
        System.out.println(f);
        System.out.println(s);
        return Math.max(f,s);
    }

    public static int f(int[] arr,int l,int r){
        if(l==r){
            return arr[l];
        }
        return Math.max(arr[l]+s(arr,l+1,r),arr[r]+s(arr,l,r-1));
    }

    public static int s(int[] arr,int l,int r){
        if(l==r){
            return 0;
        }
        return Math.min(f(arr,l+1,r),f(arr,l,r-1));
    }
}
