package com.zhangyu.datastructure.dataStructure0225;

public class addString {
    public static void main(String[] args){
        String str1="111";
        String str2="111";
        String add = add(str1, str2);
        System.out.println(add);
    }

    public static String add(String str1,String str2){
        char[] char1=str1.length()>str2.length()?str1.toCharArray():str2.toCharArray();//较长
        char[] char2=str1.length()>str2.length()?str2.toCharArray():str1.toCharArray();//较短
        int carry=0;
        int i=0;//表示位数
        while (i<char2.length){
            int num=char1[char1.length-i-1]-'0'+char2[char2.length-i-1]-'0'+carry;
            carry=num/2;
            char1[char1.length-i-1]=(char)((num%2)+'0');
            i++;
        }
        while (i<char1.length){
            int num=char1[char1.length-i-1]-'0'+carry;
            carry=num/2;
            char1[char1.length-i-1]=(char)((num%2)+'0');
            i++;
        }
        return carry==1?"1"+String.valueOf(char1):String.valueOf(char1);
    }
}
