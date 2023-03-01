package com.zhangyu.datastructure.dataStructure0214;

import java.util.Scanner;

public class practice01 {
    public static void main(String[] args){
        process4();
    }

    /**
     * 古代先民认为，天下万物皆由五类元素组成，分别是金、木、水、火、土，彼此之间存在相生相克的关系。
     *
     * 相生关系为：木生火，火生土，土生金，金生水，水生木。
     *
     * 相克关系为：金克木，木克土，土克水，水克火，火克金。
     *
     * 本题就请你编写程序，判断任意一对给定的元素之间的关系。
     *
     * 输入格式：
     * 输入在一行中给出一个正整数 N（≤10），随后 N 行，每行给出
     * 2 个正整数 A 和 B，为两种元素的编号。这里假设金、木、水、火、土的编号顺次为 1、2、3、4、5。
     *
     * 输出格式：
     * 对输入的每一对 A 和 B，如果 A 生 B，则输出 A sheng B；如果 A 克 B，则输出 A ke B。反之亦然。
     * 输入样例：
     * 2
     * 4 2
     * 5 3
     * 输出样例：
     * 2 sheng 4
     * 5 ke 3
     */
    //import java.util.Scanner
    public static void process4(){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        String[] strs={"","金","木","水","火","土"};
        int[] birth={0,3,4,2,5,1};
        int[] die={0,2,5,4,1,3};
        for(int i=0;i<n;i++){
            int num1=sc.nextInt();
            int num2=sc.nextInt();
            if(birth[num1]==num2){
                System.out.println(num1+" sheng "+num2);
            }else if(birth[num2]==num1){
                System.out.println(num2+" sheng "+num1);
            }else if(die[num1]==num2){
                System.out.println(num1+" ke "+num2);
            }else if(die[num2]==num1){
                System.out.println(num2+" ke "+num1);

            }
        }
    }

    public static void process3(){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        String[] strs={"","金","木","水","火","土"};
        String birth="245132";
        String die="125341";
        for(int i=0;i<n;i++){
            String s1=String.valueOf(sc.nextInt());
            String s2=String.valueOf(sc.nextInt());
            for(int j=0;j<birth.length()-2;j++){
                if((s1+s2).equals(birth.substring(j,j+2))){
                    System.out.println(s1+" sheng "+s2);
                }else if((s2+s1).equals(birth.substring(j,j+2))){
                    System.out.println(s2+" sheng "+s1);
                }else if((s1+s2).equals(die.substring(j,j+2))){
                    System.out.println(s1+" ke "+s2);
                }else if((s2+s1).equals(die.substring(j,j+2))){
                    System.out.println(s2+" ke "+s1);
                }
            }
        }
    }

    public static void t2(){
        int[][] arr={{1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}};
        swapMatrix(arr);
        printArr(arr);
    }


    /**
     * ### 6.旋转正方形矩阵
     *
     * 思路:同样使用边缘的四个节点,每次只旋转外圈的数字,然后旋转内圈的数据.
     */
    public static void swapMatrix(int[][] arr){
        int a=0,b=arr.length-1,c=0,d=arr[0].length-1;
        while (d>=c && b>=a){
            process2(arr,a,b,c,d);
            a++;
            c++;
            d--;
            b--;
        }

    }

    public static void process2(int[][] arr,int a,int b,int c,int d){
        for (int i = 0; i < d-c; i++) {
            int temp=arr[a][c+i];
            arr[a][c+i]=arr[b-i][c];
            arr[b-i][c]=arr[b][d-i];
            arr[b][d-i]=arr[a+i][d];
            arr[a+i][d]=temp;
        }
    }

    public static void printArr(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void t1(){
        int[][] arr={{1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}};
        int[][] arr1={{1,2,3,4},
                {5,6,7,8},
        };
//        process1(arr,0,arr.length-1,0,arr[0].length-1);
        int a=0,b=arr1.length-1,c=0,d=arr1[0].length-1;
        while (c<=d && a<=b){
            process1(arr1,a,b,c,d);
            a++;
            c++;
            d--;
            b--;
        }
    }



    /**
     * ### 5.转圈打印矩阵
     *
     * 用边缘的四个节点进行调度
     */
    public static void printArrByRound(int[][] arr){

    }

    public static void process1(int[][] arr,int a,int b,int c,int d){
        if(a==b){
            for(int i=c;i<=d;i++){
                System.out.print(arr[a][i]+" ");
            }
        }else if(c==d){
            for(int i=a;i<=b;i++){
                System.out.print(arr[i][c]+" ");
            }
        }else{
            for(int i=c;i<d;i++){
                System.out.print(arr[a][i]+" ");
            }
            for(int i=a;i<b;i++){
                System.out.print(arr[i][d]+" ");
            }
            for(int i=d;i>c;i--){
                System.out.print(arr[b][i]+" ");
            }
            for(int i=b;i>a;i--){
                System.out.print(arr[i][c]+" ");
            }
        }

        //上面没问题


    }

    /**
     * ### 4.zigzag打印矩阵
     *
     * 使用两个坐标来进行调度.不要纠结于打印的点的坐标.
     */
    public static void printZigzag(int[][] arr){
        int x1=0,y1=0,x2=0,y2=0;
        boolean flag=false;
        while (y1!=arr.length){
            printTrail(arr,x1,y1,x2,y2,flag);
            flag=!flag;
//            if(x1<arr[0].length-1){
//                x1++;
//            }else{
//                y1++;
//            }
//            if(y2<arr.length-1){
//                y2++;
//            }else{
//                x2++;
//            }
            y1=x1==arr[0].length-1?y1+1:y1;
            x1=x1<arr[0].length-1?x1+1:x1;
            x2=y2== arr.length-1?x2+1:x2;
            y2=y2<arr.length-1?y2+1:y2;

//            System.out.println(x1);
//            System.out.println(y1);
//            System.out.println(x2);
//            System.out.println(y2);
//            break;
        }
    }

    //flag为true表示从上向下打印
    public static void printTrail(int[][] arr,int x1,int y1,int x2,int y2,boolean flag){
        if(flag){
            for(int i=x1,j=y1;i>=x2;i--,j++){
                System.out.print(arr[j][i]+" ");
            }
        }else{
            for(int i=x2,j=y2;i<=x1;i++,j--){
                System.out.print(arr[j][i]+" ");
            }
        }
    }


    /**
     * ### 3.一个数可以表示为连续的正整数相加则返回true,否则返回false,一个不行
     *
     * 思路:先利用暴力方法打印出来,再来找规律.
     */
    public static boolean isAddNumber(int n){
        if(n<=2){
            return false;
        }
        return (n&(n-1))!=0;
    }

    public static boolean isAddNumber1(int n){
        if(n<=1){
            return false;
        }
        for (int i = 1; i < n; i++) {
            int sum=i;
            for (int j = i+1; j < n; j++) {
                sum+=j;
                if(sum>n){
                    break;
                }
                if(sum==n){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * ### 1.给定一个苹果的数目,一个袋子只能装6个或者8个,如果可以装完,求最少的袋子的数目,不能就返回-1
     *
     * 思路:将100以内的袋子数目打印出来找规律,直接返回即可.
     * @param n
     */
    public static int getTheMinBags(int n){
        if(n%2!=0){
            return -1;
        }
        if(n<18){
            if(n<5 ||n==10){
                return -1;
            }
            if(n==6||n==8){
                return 1;
            }
            if(n==12||n==14||n==16){
                return 2;
            }
        }


        return (n-18)/8+3;
    }

    public static int getTheMinBags1(int n){
        int num1=n/8;
        while (true){
            if(num1==-1){
                break;
            }
            if((n-8*num1)%6==0){
                return num1+(n-8*num1)/6;
            }else{
                num1--;
            }
        }
        return -1;
    }

    /**
     * ### 2.牛和羊吃草,每次吃的是4的某次方,谁最先把草吃完谁获胜.
     *
     * 思路:先使用递归求出暴力解,再根据其找规律.
     */
    public static String eatGrass(int n){
        if(n<5){
            return n==0||n==2?"后手":"先手";
        }
        int base=1;
        while (base<n){
            if(eatGrass(n-base).equals("后手")){
                return "先手";
            }
            if(base>n/4){
                break;
            }
            base*=4;
        }
        return "后手";
    }
}
