package com.zhangyu.datastructure.dataStructure0205;

import java.util.Arrays;

public class practice03 {
    public static void main(String[] args){
        int[] arr={1,2,5,6,3,2,1,100,2003,3422,2313132,Integer.MAX_VALUE};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 基数排序
     * 每次准备一个辅助数组,数组中需要记录<=num出现的次数,这样就可以把数字放到当前的最后一个
     * 例如个位上<=5的有5个,那么当前数字需要放到help数组的4索引上,因为小于等于5的有5个,
     * 并且需要从原数组逆序放,因为原数组进桶的过程是先进先出的,数组偏后的位置肯定后出,所以可以放在后面
     *
     * @param arr
     */
    public static void radixSort(int[] arr){
        int maxDigit = getMaxDigit(arr);
        for (int i = 1; i <=maxDigit; i++) {
            int[] help=new int[10];
            int[] count=new int[arr.length];
            for (int j = 0; j < arr.length; j++) {
                int digit = getDigit(arr[j], i);
                help[digit]++;
            }
            System.out.println("help1"+Arrays.toString(help));
            for(int j=1;j< help.length;j++){
                help[j]=help[j-1]+help[j];
            }
            System.out.println("help2"+Arrays.toString(help));

            for (int j = arr.length - 1; j >= 0; j--) {
                int digit = getDigit(arr[j], i);
//                help[digit]--;
                count[--help[digit]]=arr[j];
            }
            for (int j = 0; j < arr.length; j++) {
                arr[j]=count[j];
            }
        }
    }

    public static int getDigit(int num,int i){
        int res=0;
        while (i>0){
            res=num%10;
            num/=10;
            i--;
        }
        return res;
    }

    //得到最高位数
    public static int getMaxDigit(int[] arr){
        int max=0;
        for (int i = 0; i < arr.length; i++) {
            int res=0;
            int num=arr[i];
            while (num>0){
                res++;
                num/=10;
            }
            max=Math.max(max,res);
        }
        return max;
    }
}
