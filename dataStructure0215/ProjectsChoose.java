package com.zhangyu.datastructure.dataStructure0215;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ProjectsChoose {
    public static void main(String[] args){
        t1();
    }

    public static void t1(){
        Project p1=new Project(1,2);
        Project p2=new Project(2,3);
        Project p3=new Project(3,4);
        Project[] projects={p1,p2,p3};
        int i = dealingwithProject2(projects,1);
        System.out.println(i);
    }

    public static class Project{
        int cost;
        int profit;

        public Project(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    public static int dealingwithProject2(Project[] projects,int money){
        return process2(projects,money);
    }

    public static int process2(Project[] projects,int money){
        PriorityQueue<Project> queue1=new PriorityQueue<>(new Comparator<Project>() {
            @Override
            public int compare(Project o1, Project o2) {
                return o1.cost- o2.cost;
            }
        });//小根堆,存放
        PriorityQueue<Project> queue2=new PriorityQueue<>(new Comparator<Project>() {
            @Override
            public int compare(Project o1, Project o2) {
                return o2.profit- o1.profit;
            }
        });
        for (int i = 0; i < projects.length; i++) {
            queue1.add(projects[i]);
        }
        while (!queue1.isEmpty()){
            if(money>=queue1.peek().cost){
                queue2.add(queue1.poll());
            }
            while (!queue2.isEmpty()){
                Project p = queue2.poll();
                money+=p.profit-p.cost;
            }
        }
        return money;
    }

    /**
     * ### 5.项目问题,每个项目有其对应的花费和利润,怎么选择项目做
     *
     * 复杂做法:准备一个小根堆和一个大根堆,小根堆里存放花费,大根堆中存放利润高的项目.
     * 每次从利润高的项目中做项目,再去小根堆中解锁项目.
     */
    //暴力递归
    public static int dealingwithProject1(Project[] projects,int money){
        return process1(projects,money,0,new ArrayList<>());
    }

    /**
     *
     * @param projects 剩下的项目的数量
     * @param money 剩下的钱
     * @param list 已经做过的项目
     *        num 表示现在来到哪一个项目了
     * @return
     */
    public static int process1(Project[] projects, int money, int num,ArrayList<Project> list){
        if(num==projects.length){
            return money;
        }
        int no=money;
        int yes=Integer.MIN_VALUE;
        if(money>projects[num].cost){
                //可以做这个项目
            list.add(projects[num]);
            money+=projects[num].profit-projects[num].cost;
            yes=process1(pro1(projects,num),money,0,list);
            list.remove(projects[num]);
        }
        return Math.max(no,yes);
    }

    //从一个数组中删除指定的元素
    public static Project[] pro1(Project[] projects,int i){
        Project[] p=new Project[projects.length-1];
        int index=0;
        for (int j = 0; j < projects.length; j++) {
            if(j!=i){
                p[index++]=projects[j];
            }
        }
        return p;
    }
}
