package com.zhangyu.datastructure.dataStructure0227;

import java.util.*;

public class FallingViolence {
    public static void main(String[] args){
        int[][] positions={};
        fallingSquares(positions);
    }

    public static List<Integer> fallingSquares (int[][] positions){
        List<Integer> list=new ArrayList<>();
        int max=0;
        HashMap<Integer, Integer> map = getIndex(positions);
        int[] ans=new int[map.size()+1];
        for (int[] position : positions) {
            int num1=map.get(position[0]);
            int num2=map.get(position[0]+position[1]-1);
            int height=getMax(ans,num1,num2);
            for (int i = 1; i <=ans.length-1; i++) {
                if(i>=num1 && i<=num2){
                    //应该是当前这个区间上的最大值再加上position[1]
                    ans[i]=height+position[1];
                }
            }
            for (int i = 1; i <=ans.length-1; i++) {
                max=Math.max(max,ans[i]);
            }
            list.add(max);
        }
        return list;
    }

    public static int getMax(int[] arr,int i,int j){
        int max=0;
        for (int k = i; k <=j; k++) {
            max=Math.max(max,arr[k]);
        }
        return max;
    }

    public static HashMap<Integer,Integer> getIndex(int[][] positions){
        HashMap<Integer,Integer> map=new HashMap<>();
        TreeSet<Integer> set=new TreeSet<>();
        int count=0;
        for (int[] position : positions) {
            set.add(position[0]);
            set.add(position[0]+position[1]-1);
        }
        for (int position : set) {
            map.put(position,++count);
        }
        return map;
    }

    public static List<Integer> falling(int[][] position){
        List<Integer> list=new ArrayList<>();
        //每次一个方块落下的时候,他可能会与底下的方块有重合的,这样需要一一叠加起来
        for (int i = 0; i < position.length; i++) {
            int height=position[i][1];
            int left1=position[i][0],right1=position[i][1]+position[i][0]-1;
            for (int j = 0; j < i; j++) {
                int left2=position[j][0],right2=position[j][1];
                if(right1>=left2 && right2>=left1){
                    //说明相交
                    height=Math.max(height,list.get(j)+position[i][1]);
                }
            }
            list.add(height);
        }
        for(int i=1;i<list.size();i++){
            list.set(i,Math.max(list.get(i),list.get(i-1)));
        }
        return list;
    }


}
