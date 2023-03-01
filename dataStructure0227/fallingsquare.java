package com.zhangyu.datastructure.dataStructure0227;

import java.util.*;

public class fallingsquare {
    public static void main(String[] args){

    }

    public static class SegTree{
        //需要完成的操作,更新,求区间最大值
        private int[] max;
        private boolean[] update;
        private int[] change;

        public SegTree(int n){
            //n表示总共的节点的个数
            int size=n+1;
            max=new int[size<<2];
            update=new boolean[size<<2];
            change=new int[size<<2];
        }

        public void pushUp(int index){
            max[index]=Math.max(max[index<<1],max[index<<1|1]);
        }

        public void pushDown(int index,int left,int right){
            if(update[index]){
                change[index<<1]=change[index];
                change[index<<1|1]=change[index];
                max[index<<1]=change[index];
                max[index<<1|1]=change[index];
                update[index<<1]=true;
                update[index<<1|1]=true;
                update[index]=false;
            }
        }

        public void update(int L,int R,int C,int l,int r,int index){
            if(L<=l && R>=r){
                //区间完全覆盖住了
                update[index]=true;
                change[index]=C;
                max[index]=C;
                return;
            }
            int mid=l+((r-l)>>1);
            pushDown(index,mid-l+1,r-mid);
            if(L<=mid){
                update(L,R,C,l,mid,index<<1);
            }
            if(R>mid){
                update(L,R,C,mid+1,r,index<<1|1);
            }
            pushUp(index);
        }

        public int query(int L,int R,int l,int r,int index){
            if(L<=l && R>=r){
                return max[index];
            }
            int mid=l+((r-l)>>1);
            pushDown(index,mid-l+1,r-mid);
            int ans=0;
            if(L<=mid){
                ans=Math.max(ans,query(L,R,l,mid,index<<1));
            }
            if(R>mid){
                ans=Math.max(ans,query(L,R,mid+1,r,index<<1|1));
            }
            return ans;
        }
    }

    public static HashMap<Integer,Integer> getIndex(int[][] position){
        TreeSet<Integer> set=new TreeSet<>();
        for (int[] arr : position) {
            set.add(arr[0]);
            set.add(arr[0]+arr[1]-1);
        }
        HashMap<Integer,Integer> map=new HashMap<>();
        int count=0;
        for (Integer integer : set) {
            map.put(integer,++count);
        }
        return map;
    }

    public static List<Integer> falling(int[][] position){
        //首先得到位置表
        HashMap<Integer, Integer> map = getIndex(position);
        int n=map.size();
        SegTree seg=new SegTree(n);
        int max=0;
        List<Integer> list=new ArrayList<>();
        int l=1;
        int r=n;
        int index=1;
        for (int[] arr : position) {
            //首先得到当前两点的坐标对应的高度
            int num1=map.get(arr[0]);
            int num2=map.get(arr[0]+arr[1]-1);
            int height= seg.query(num1,num2,l,r,index)+arr[1];
            max=Math.max(height,max);
            list.add(max);
            //最后更新值
            seg.update(num1,num2,height,l,r,index);
        }
        return list;
    }
}
