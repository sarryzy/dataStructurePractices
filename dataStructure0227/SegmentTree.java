package com.zhangyu.datastructure.dataStructure0227;

import java.util.Arrays;

public class SegmentTree {
    private int[] arr;
    private int[] lazy;
    private int[] sum;
    private boolean[] update;
    private int[] change;

    public SegmentTree(int[] origin){
        int n=origin.length+1;
        arr=new int[n];
        System.arraycopy(origin,0,arr,1,origin.length);
        sum=new int[n<<2];
        lazy=new int[n<<2];
        change=new int[n<<2];
        update=new boolean[n<<2];
        System.out.println(origin.length);
        System.out.println(sum.length);
    }

    public void build(int l,int r,int index){
        //生成线段树的初始sum数组
        if(l==r){
            sum[index]=arr[l];
            return;
        }
        int mid=l+((r-l)>>1);
        build(l,mid,index<<1);
        build(mid+1,r,(index<<1)|1);
        pushUp(index);//将index这个位置的sum得到
    }

    public void pushUp(int index){
        sum[index]=sum[index<<1]+sum[(index<<1)|1];
    }

    public void add(int left,int right,int num,int l,int r,int index){
        //在left和right区间数组上的所有数增加num
        if(left<=l && right>=r){
            //完全包裹,直接接收任务
            sum[index]+=num*(r-l+1);
            lazy[index]+=num;
            return;
        }
        //需要下发任务,在下发任务之前,将积攒的任务进行发放
        int mid=l+((r-l)>>1);
        pushDown(index,mid-l+1,r-mid);
        if(left<=mid){
            //左边要考虑,下发任务
            add(left,right,num,l,mid,index<<1);
        }
        if(right>mid){
            //右边要考虑
            add(left,right,num,mid+1,r,index<<1|1);
        }
        pushUp(index);
    }

    //left表示左子树节点个数,right表示右子树节点个数
    private void pushDown(int index,int left,int right){
        //下发update任务要在lazy前面,因为update是直接修改值
        if(update[index]){
            update[index<<1]=true;
            update[index<<1|1]=true;
            change[index<<1]=change[index];
            change[index<<1|1]=change[index];
            lazy[index<<1]=0;
            lazy[index<<1|1]=0;
            sum[index<<1]=change[index]*left;
            sum[index<<1|1]=change[index]*right;
            update[index]=false;
        }
        //下发lazy任务
        if(lazy[index]!=0){
            lazy[index<<1]+=lazy[index];
            lazy[index<<1|1]+=lazy[index];
            sum[index<<1]+=lazy[index]*left;
            sum[index<<1|1]+=lazy[index]*right;
            lazy[index]=0;
        }
    }

    public void update(int left,int right,int num,int l,int r,int index){
        //更新操作
        if(left<=l && right>=r){
            //覆盖范围,直接进行修改
            update[index]=true;
            change[index]=num;
            sum[index]=num*(r-l+1);
            lazy[index]=0;
            return;
        }
        //运行到这里说明需要进行更新,在分发任务之前需要先进行pushdown
        int mid=l+((r-l)>>1);
        pushDown(index,mid-l+1,r-mid);
        if(left<=mid){
            update(left,right,num,l,mid,index<<1);
        }
        if(right>mid){
            update(left,right,num,mid+1,r,index<<1|1);
        }
        pushUp(index);
    }

    public int query(int left,int right,int l,int r,int index){
        //查询left到right上的和
        if(left<=l && right>=r){
            return sum[index];
        }
        int mid=l+((r-l)>>1);
        pushDown(index,mid-l+1,r-mid);
        int res=0;
        if(left<=mid){
            res+=query(left,right,l,mid,index<<1);
        }
        if(right>mid){
            res+=query(left,right,mid+1,r,index<<1|1);
        }
        return res;
    }

    public static class simpleTree{
        private int[] arr;
        public simpleTree(int[] origin){
            //将原数组的索引值进行修改
            arr=new int[origin.length+1];
            System.arraycopy(origin,0,arr,1,origin.length);
        }

        public void add(int left,int right,int num){
            //在left和right区间上的都加上num
            for (int i = 0; i < arr.length; i++) {
                if(i>=left && i<=right){
                    arr[i]+=num;
                }
            }
        }

        public void update(int left,int right,int num){
            //在left和right区间上的都变为num
            for (int i = 0; i < arr.length; i++) {
                if(i>=left && i<=right){
                    arr[i]=num;
                }
            }
        }

        public int query(int left,int right,int num){
            int sum=0;
            for (int i = 0; i < arr.length; i++) {
                if(i>=left && i<=right){
                    sum+=arr[i];
                }
            }
            return sum;
        }
    }

    public static void main(String[] args){
        if(t1()){
            System.out.println("成功啦");
        }
    }

    public static int[] generateRandomArray(int max,int len){
        int[] arr=new int[(int)(Math.random()*len)+1];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int)(Math.random()*max-Math.random()*max);
        }
        return arr;
    }

    public static boolean t1(){
        int testTimes=10000;
        int max=10000;
        int len=10000;
        int addOrUpdateTime=10000;
        int queryTimes=10000;
        for (int i = 0; i < testTimes; i++) {
            System.out.println("testtime"+i);
            int[] origin=generateRandomArray(max,len);
            SegmentTree seg=new SegmentTree(origin);
            int l=1;
            int r=origin.length;
            int index=1;
            seg.build(l,r,index);
            simpleTree sim=new simpleTree(origin);
            for (int j = 0; j < addOrUpdateTime; j++) {
                int num1=(int)(Math.random()*r)+1;
                int num2=(int)(Math.random()*r)+1;
                int left=Math.min(num1,num2);
                int right=Math.max(num1,num2);
                int num=(int)(Math.random()*max-Math.random()*max);
                if(Math.random()<0.5){
                    seg.add(left,right,num,l,r,index);
                    sim.add(left,right,num);
                }else{
                    seg.update(left,right,num,l,r,index);
                    sim.update(left,right,num);
                }
            }
            for(int j=0;j<queryTimes;j++){
                int num1=(int)(Math.random()*r)+1;
                int num2=(int)(Math.random()*r)+1;
                int left=Math.min(num1,num2);
                int right=Math.max(num1,num2);
                int ans1=seg.query(left,right,l,r,index);
                int ans2=sim.query(left,right,index);
                if(ans1!=ans2){
                    return false;
                }
            }
        }
        return true;
    }
}
