package com.zhangyu.datastructure.dataStructure0227;

public class SegPractice01 {
    public static class SegPrac01{
        private int[] arr;
        private int[] sum;
        private int[] lazy;
        private boolean[] update;
        private int[] change;

        public SegPrac01(int[] origin){
            int size=origin.length+1;
            arr=new int[size];
            sum=new int[size];
            lazy=new int[size];
            change=new int[size<<2];
            update=new boolean[size<<2];
            System.arraycopy(origin,0,arr,1,origin.length);
        }

        public void build(int l,int r,int index){
            if(l==r){
                sum[index]=arr[l];
                return;
            }
            int mid=l+((r-l)>>1);
            build(l,mid,index<<1);
            build(mid+1,r,index<<1|1);
            pushUp(index);
        }

        public void pushUp(int index){
            sum[index]=sum[index<<1]+sum[index<<1|1];
        }

        public void add(int L,int R,int num,int l,int r,int index){
            if(L<=l && R>=r){
                sum[index]+=(r-l+1)*num;
                lazy[index]+=num;
                return;
            }
            int mid=l+((r-l)>>1);
            pushDown(index,mid-l+1,r-mid);
        }

        public void pushDown(int index,int left,int right){
            if(update[index]){
                update[index<<1]=true;
                update[index<<1|1]=true;
                sum[index<<1]=change[index]*left;
                sum[index<<1|1]=change[index]*right;
                change[index<<1]=change[index];
                change[index<<1|1]=change[index];
                lazy[index<<1]=0;
                lazy[index<<1|1]=0;
                update[index]=false;
            }
            if(lazy[index]!=0){
                lazy[index<<1]+=lazy[index];
                lazy[index<<1|1]+=lazy[index];
                sum[index<<1]+=lazy[index]*left;
                sum[index<<1|1]+=lazy[index]*right;
                lazy[index]=0;
            }
        }

    }
}
