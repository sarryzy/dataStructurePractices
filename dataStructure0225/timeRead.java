package com.zhangyu.datastructure.dataStructure0225;

import java.util.ArrayList;
import java.util.List;

public class timeRead {
    public static void main(String[] args){
        List<String> watch = watch(1);
        System.out.println(watch);
    }
    //直接暴力
    public static List<String> watch(int n){
        List<String> list=new ArrayList<>();
        int count=0;
        for(int i=0;i<12;i++){
            for(int j=0;j<60;j++){
                if(byteCount(i)+byteCount(j)==n){
                    String s=i+":"+(j>=10?j:"0"+j);
                    list.add(s);
                }
            }
        }
        return list;
    }

    public static int byteCount(int n){
        //看看n的二进制数有几位
        int count=0;
        while (n>0){
            if((n&1)==1){
                count++;
            }
            n>>=1;
        }
        return count;
    }


}
