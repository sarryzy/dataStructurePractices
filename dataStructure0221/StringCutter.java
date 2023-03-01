package com.zhangyu.datastructure.dataStructure0221;

import java.util.HashMap;

public class StringCutter {
    public static void main(String[] args){
        String target="abcde";
        String[] arr={"a","b","c","d","e"};
        int num1 = getNum1(target, arr);
        int num2 = getNum2(target, arr);
        System.out.println(num1);
        System.out.println(num2);

    }

    /**
     * ### 1.贴纸问题
     *
     * 给定一个字符串,需用用给定的贴纸去凑够,求最少的贴纸张数.
     * @param target
     * @param arr
     * @return
     */
    public static int getNum1(String target,String[] arr){
        int n=arr.length;
        int[][] map=new int[n][26];
        int index=0;
        for (String s : arr) {
            for (int i = 0; i < s.length(); i++) {
                map[index][s.charAt(i)-'a']++;
            }
            index++;
        }
        return process1(target,map);
    }

    public static int process1(String rest,int[][] map){
        if(rest.equals("")){
            return 0;
        }
        int[] curMap=new int[26];
        for (int i = 0; i < rest.length(); i++) {
            curMap[rest.charAt(i)-'a']++;
        }
        //实现字符串相减,然后将其合并为一个字符串
        int ans=Integer.MAX_VALUE;
        for (int i = 0; i < map.length; i++) {
            //每一行
            //如果当前字符串包含需要的信息的时候才考虑,将两个字符串进行对比,如果没有相同的则直接返回.
//            for (int h = 0; h < 26; h++) {

             if(map[i][rest.charAt(0)-'a']==0){
                 continue;
             }
                    StringBuffer sb=new StringBuffer();
                    for(int j=0;j<map[0].length;j++){
                        for(int k=0;k<Math.max(curMap[j] - map[i][j],0);k++){
                            sb.append((char)(j+'a'));
                            System.out.println("sb:"+sb);
                        }
                    }
                    int next=process1(new String(sb),map);
                    if(next!=-1){
                        ans=Math.min(ans,next+1);
                    }
//                    break;

//            }
        }
        return ans==Integer.MAX_VALUE?-1:ans;
    }

    public static int getNum2(String target,String[] arr){
        int n=arr.length;
        int[][] map=new int[n][26];
        int index=0;
        for (String s : arr) {
            for (int i = 0; i < s.length(); i++) {
                map[index][s.charAt(i)-'a']++;
            }
            index++;
        }
        HashMap<String,Integer> hashmap=new HashMap<>();
        hashmap.put("",0);
        return process2(target,map,hashmap);
    }

    public static int process2(String rest,int[][] map,HashMap<String,Integer> hashMap){
        if(hashMap.containsKey(rest)){
            return hashMap.get(rest);
        }
        int[] curMap=new int[26];
        for (int i = 0; i < rest.length(); i++) {
            curMap[rest.charAt(i)-'a']++;
        }
        //实现字符串相减,然后将其合并为一个字符串
        int ans=Integer.MAX_VALUE;
        for (int i = 0; i < map.length; i++) {
            if(map[i][rest.charAt(0)-'a']==0){
                continue;
            }
            StringBuffer sb=new StringBuffer();
            for(int j=0;j<map[0].length;j++){
                for(int k=0;k<Math.max(curMap[j] - map[i][j],0);k++){
                    sb.append((char)(j+'a'));
                    System.out.println("sb:"+sb);
                }
            }
            int next=process1(new String(sb),map);
            if(next!=-1){
                ans=Math.min(ans,next+1);
            }
        }
        if(ans!=-1){
            hashMap.put(rest,ans);
        }
        return ans;
    }
}
