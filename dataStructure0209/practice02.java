package com.zhangyu.datastructure.dataStructure0209;

import java.util.Stack;

public class practice02 {

    public static void main(String[] args){
        int n=12;
        int i3 = hanoi3(n);
        int i2 = hanoi2(n);
        hanoi1(n);
        int i1=count;
        int i4 = hanoi4(n);
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);
        System.out.println(i4);
    }

    public static void t1(Stack<Integer> stack1,Stack<Integer> stack2){
        stack2.push(stack1.pop());
    }

    static int count;
    public static void hanoi1(int n){
        if(n<1){
            return;
        }
        process1(n,"left","mid","right");
        System.out.println("count:"+count);
    }

    public static void process1(int n,String left,String mid,String right){
        count++;
        System.out.println("count:"+count);
        System.out.println("n:"+n);
        if(n==1){
            System.out.println("move:"+left+" to:"+right);
            return;
        }
        process1(n-1,left,right,mid);
        System.out.println("move:"+left+" to:"+right);
        process1(n-1,mid,left,right);
    }

    //不允许从左边移到右边,或者直接从右边移到左边的hanoi塔
    public static int hanoi2(int n){
        if(n<1){
            return 0;
        }
        int i = process2(n, "left", "mid", "right", "left", "right");
        return i;
    }

    public static int process2(int n,String left,String mid,String right,String from,String to){
        if(n==1){
            if(from.equals("mid") || to.equals("mid")){
                System.out.println("move 1 from"+from+" to "+to);
                return 1;
            }else{
                System.out.println("move 1 from"+from+" to "+mid);
                System.out.println("move 1 from"+mid+" to "+to);
                return 2;
            }
        }
        if(from.equals("mid")||to.equals("mid")){
            String another=from.equals("left")||to.equals("left")?right:left;
            int part1=process2(n-1,left,mid,right,from,another);
            int part2=1;
            System.out.println("move 1 from"+from+" to "+to);
            int part3=process2(n-1,left,mid,right,another,to);
            return part1+part2+part3;
        }else{
            int part1=process2(n-1,left,mid,right,from,mid);
            int part2=process2(n-1,left,mid,right,mid,to);
            int part3=1;
            System.out.println("move 1 from"+from+" to "+mid);
            int part4=process2(n-1,left,mid,right,to,mid);
            int part5=process2(n,left,mid,right,mid,to);
            return part1+part2+part3+part4+part5;
        }
    }

    //用非递归实现hanoi
    public static int hanoi3(int n){
        if(n<1){
            return 0;
        }
        return process3(n);
    }

    public static int process3(int n){
        Stack<Integer> stack1=new Stack<>();
        Stack<Integer> stack2=new Stack<>();
        Stack<Integer> stack3=new Stack<>();
        stack1.push(Integer.MAX_VALUE);
        stack2.push(Integer.MAX_VALUE);
        stack3.push(Integer.MAX_VALUE);
        int count=0;
        for (int i = n; i > 0; i--) {
            stack1.push(i);
        }
        int flag1=1;
        int flag2=0;
        int flag3=0;
        int flag4=0;
        leftToMid(stack1,stack2);
        count++;
        while (stack3.size()!=n+1){
            System.out.println("flag1:"+flag1);
            System.out.println("flag2:"+flag2);
            System.out.println("flag3:"+flag3);
            System.out.println("flag4:"+flag4);
            if(flag1==1){
                System.out.println("flag1");
                if (rightToMid(stack3,stack2)==1) {
                    flag2=1;
                }
                else if (midToRight(stack2,stack3)==1) {
                    flag4=1;
                }
//                else if (midToLeft(stack2,stack1)==1) {
//                    flag3=1;
//                }
                flag1=0;
            }else if(flag2==1){
                System.out.println("flag2");

                if(leftToMid(stack1,stack2)==1){
                    flag1=1;
                }
//                else if (midToRight(stack2,stack3)==1) {
//                    flag4=1;
//                }
                else if (midToLeft(stack2,stack1)==1) {
                    flag3=1;
                }
                flag2=0;
            }else if(flag3==1){
                System.out.println("flag3");

//                if(leftToMid(stack1,stack2)==1){
//                    flag1=1;
//                }
                 if (midToRight(stack2,stack3)==1) {
                    flag4=1;
                }
                else if (rightToMid(stack3,stack2)==1) {
                    flag2=1;
                }
                flag3=0;
            }else if(flag4==1){
                System.out.println("flag4");
                if(leftToMid(stack1,stack2)==1){
                    flag1=1;
                }
//                else if (rightToMid(stack3,stack2)==1) {
//                    flag2=1;
//                }
                else if (midToLeft(stack2,stack1)==1) {
                    flag3=1;
                }
                flag4=0;
            }
            count++;
            if(stack3.size()==n+1){
                break;
            }
        }
        return count;
    }

    public static int  leftToMid(Stack<Integer> stack1,Stack<Integer> stack2){
        if(stack1!=null && stack2!=null && stack1.peek()<stack2.peek()){
            stack2.push(stack1.pop());
            return 1;
        }
        return 0;
    }

    public static int rightToMid(Stack<Integer> stack3,Stack<Integer> stack2){
        if(stack3!=null && stack2!=null &&stack3.peek()<stack2.peek()){
            stack2.push(stack3.pop());
            return 1;
        }
        return 0;
    }

    public static int midToLeft(Stack<Integer> stack2,Stack<Integer> stack1){
        if(stack1!=null && stack2!=null &&stack2.peek()<stack1.peek()){
            stack1.push(stack2.pop());
            return 1;
        }
        return 0;
    }

    public static int midToRight(Stack<Integer> stack2,Stack<Integer> stack3){
        if(stack3!=null && stack2!=null &&stack2.peek()<stack3.peek()){
            stack3.push(stack2.pop());
            return 1;
        }
        return 0;
    }

    public enum Action{
        LToM,RToM,MToL,MToR
    }

    public static int hanoi4(int n){
        Stack<Integer> stack1=new Stack<>();
        Stack<Integer> stack2=new Stack<>();
        Stack<Integer> stack3=new Stack<>();
        stack1.push(Integer.MAX_VALUE);
        stack2.push(Integer.MAX_VALUE);
        stack3.push(Integer.MAX_VALUE);
        for (int i = n; i > 0; i--) {
            stack1.push(i);
        }
        Action[] pre={Action.LToM};
        int count=0;
        while (stack3.size()!=n+1){
            count+=process4(pre,Action.MToL,Action.LToM,stack1,stack2,"left","mid");
            count+=process4(pre,Action.LToM,Action.MToL,stack2,stack1,"mid","left");
            count+=process4(pre,Action.RToM,Action.MToR,stack2,stack3,"mid","right");
            count+=process4(pre,Action.MToR,Action.RToM,stack3,stack2,"right","mid");
        }
        System.out.println("hanoi4_count:"+count);
        return count;
    }

    /**
     *
     * @param pre,前一步的操作
     * @param Notnow,这一步不允许的操作
     * @param now
     * @param stack1
     * @param stack2
     * @param from
     * @param to
     * @return
     */
    public static int process4(Action[] pre,Action Notnow,Action now,Stack<Integer> stack1,Stack<Integer> stack2,String from,String to){
        if(pre[0]!=Notnow && stack1.peek()<stack2.peek()){
            int i=stack1.peek();
            stack2.push(stack1.pop());
            pre[0]=now;
            System.out.println("move "+i+"from "+from+"to "+to);
            return 1;
        }
        return 0;
    }
}
