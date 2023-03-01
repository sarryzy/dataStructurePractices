package com.zhangyu.datastructure.dataStructure0219;

public class StringSwapNumbers {
    public static void main(String[] args){
        String s="111";
        System.out.println(getStringNum(s));
    }
    /**
     * ### 6.将字符串按照条件转化为指定的字符,1对应A,2对应B,以此类推.
     *
     * 递归思路:使用索引i,如果i到达最后则返回1,如果当前值为0,直接返回0.
     * 如果当前值为1,说明这个位置的结果有两种,一个是单独成一个值,
     * 还有一种是和右边的一个成一个值.如果为2,也有两种情况.如果为其他的,
     * 说明不能单独成值,直接看后面的结果.
     */
    public static int getStringNum(String s){
        return process(s.toCharArray(),0);
    }

    public static int process(char[] c,int i){
        if(i==c.length){
            return 1;
        }
        if(c[i]=='0'){
            return 0;
        }
        if(c[i]=='1'){
            int res=process(c,i+1);
            if(i+1<c.length){
                res+=process(c,i+2);
            }
            return res;
        }
        if(c[i]=='2'){
            int res=process(c,i+1);
            if(i+1<c.length && c[i+1]>='1' && c[i+1]<='6'){
                res+=process(c,i+2);
            }
            return res;
        }
        return process(c,i+1);
    }
}
