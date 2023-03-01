package com.zhangyu.datastructure.dataStructure0225;

public class drinkWater {
    public static void main(String[] args){
        int drink = drink(15, 4);
        int drink1 = drink(15, 4);
        System.out.println(drink1);
    }


    public static int drink(int n,int cost){
        //思路,用一个变量记录现有的空瓶子的个数
        int cur=n;
        //用一个变量记录总共喝过的
        int sum=0;
        while (cur>=cost){
            sum+=cur-(cur%cost);
            cur=cur/cost+(cur%cost);
        }
        System.out.println(sum+cur);
        return sum+cur;
    }

    public static int drink1(int n,int cost){
        return n>=cost?(n-cost)/(cost-1)+1+n:n;
    }
}
