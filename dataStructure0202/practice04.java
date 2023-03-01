package com.zhangyu.datastructure.dataStructure0202;

import java.util.ArrayList;
import java.util.Scanner;

public class practice04 {
    public static void main(String[] args){
        System.out.println(Integer.parseInt("11000000000000000000000000000001",2));
    }
    /**
     * 求解逆序对的个数
     * @param arr
     * @param l
     * @param m
     * @param r
     * @return
     */
    public static int merge(int[] arr,int l,int m,int r){
        //注意相等的时候左边要先移动
        //按照从小到大的顺序进行排序
        int i=0;
        int[] help=new int[r-l+1];
        int p1=l,p2=m+1;
        int res=0;
        while (p1<=m && p2<=r){
            res+=arr[p1]>arr[p2]?(m-p1+1):0;
            help[i++]=arr[p1]<=arr[p2]?arr[p1++]:arr[p2++];
        }
        while(p1<=m){
            help[i++]=arr[p1++];
        }
        while(p2<=r){
            help[i++]=arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[l+j]=help[j];
        }
        return res;
    }

    public static int reversePairs(int[] arr){
        int n=arr.length;
        int mergeSize=1;
        int res=0;
        while (mergeSize<n){
            int l=0;
            while (l+mergeSize<n){
                int m=l+mergeSize-1;
                if(m>=n){
                    break;
                }
                int r=Math.min(m+mergeSize,n-1);
                res+=merge(arr,l,m,r);
                l=r+1;
            }
            if(mergeSize>n/2){
                break;
            }
            mergeSize<<=1;
        }
        return res;
    }

    //背包问题

    /**
     * 首先计算出每一个物品的价值,将价值从高到低排序,由高到低来进行放入
     */
    public static int bag(){
        Scanner sc=new Scanner(System.in);
        int maxValue=0;
        int N=sc.nextInt();//数量
        int V=sc.nextInt();//背包容积
        int[] arr1=new int[N];//存放物品占用的体积
        int[] arr2=new int[N];//存放物品的价值
        for (int i = 0; i < N; i++) {
            arr1[i]=sc.nextInt();
            arr2[i]=sc.nextInt();
        }
        while (V>0){
            int i = getVal(arr1, arr2);
            if(arr1[i]>V){
                //装不下
                arr2[i]=0;
                continue;
            }else{
                maxValue+=arr2[i];
                V-=arr1[i];
                arr2[i]=0;
            }
            if(flag(arr2)){
                break;
            }
        }
        return maxValue;
    }

    public static int getVal(int[] arr1,int[] arr2){
        int max=0;
        int index=-1;
        for (int i=0; i < arr1.length; i++) {
            index=arr2[i]/arr1[i]>max?i:index;
        }
        return index;
    }

    public static boolean flag(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]!=0){
                return false;
            }
        }
        return true;
    }

    /**
     * 计算有多少位1
     * @param n
     * @return
     */
    public static int hammingWeight(int n) {
        int res=0;
        while (n!=0){
            n=n&(n-1);
            res++;
        }
        return res;
    }

    public int singleNumber(int[] nums) {
        int eor=0;
        for (int i = 0; i < nums.length; i++) {
            eor^=nums[i];
        }
        return eor;
    }

    public static boolean isPalindrome(String s) {
        ArrayList<Character> list=new ArrayList<>();
        for (int k = 0; k < s.length(); k++) {
            if(check(s.charAt(k))){
                list.add(s.charAt(k));
            }
        }
        int i=0;
        int j=list.size()-1;
        while (i<j){
            if(!judge(list.get(i++),list.get(j--))){
                return false;
            }
        }
        return true;
    }

    public static boolean judge(char a,char b){
        return a==b || (checka(a) && checka(b)&&Math.abs(a-b)==97-65 );
    }

    //判断是否是字符或者数字
    public static boolean check(char a){
        return (a>='a' && a<='z') || (a>='A' && a<='Z') || (a>='0' && a<='9');
    }

    public static boolean checka(char a){
        return (a>='a' && a<='z') || (a>='A' && a<='Z');
    }

    public int reverseBits(int n) {
        StringBuilder sb=new StringBuilder();
        for (int j = 0; j < 32; j++) {
            int i=n&(1<<j);
            sb.append(i==0?0:1);
        }
        return Integer.parseInt(sb.toString(),2);
    }
}
