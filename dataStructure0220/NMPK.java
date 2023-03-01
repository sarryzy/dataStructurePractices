package com.zhangyu.datastructure.dataStructure0220;

public class NMPK {
    public static void main(String[] args){
        int i = walk1(100, 1, 10000, 11);
        int j = walk2(100, 1, 10000, 11);
        int k = walk3(100, 1, 10000, 11);
        System.out.println(i);
        System.out.println(j);
        System.out.println(k);
    }

    /**
     * ### 3.一个机器人,在N个格子上走动,每次必须走动K步,问最终能来到P位置的方法有多少种.
     *
     * 简单思路:直接应用递归来进行求解.
     */
    public static int walk1(int N,int M,int P,int K){
        //n为距离,M为出发点,P为终点,K为步数,返回总共有多少种走路的方法
        if(N<1||M<1||M>N||P<1||P>N||K<1){
            return 0;
        }
        return process1(N,M,P,K);
    }

    public static int process1(int N,int M,int P,int K){
        //M表示当前来到的位置
//        if(K==0&&M!=P){
//            return 0;
//        }else if(K==0 && M==P){
//            return 1;
//        }
        if(K==0){
            return P==M?1:0;
        }
        if(M==1){
            return process1(N,2,P,K-1);
        }
        if(M==N){
            return process1(N,N-1,P,K-1);
        }
        return process1(N,M+1,P,K-1)+process1(N,M-1,P,K-1);
    }

    public static int walk2(int N,int M,int P,int K){
        //n为距离,M为出发点,P为终点,K为步数,返回总共有多少种走路的方法
        if(N<1||M<1||M>N||P<1||P>N||K<1){
            return 0;
        }
        int[][] dp=new int[N+1][K+1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j]=-1;
            }
        }
        return process2(N,M,P,K,dp);
    }

    public static int process2(int N,int cur,int P,int step,int[][] dp){
        //M表示当前来到的位置
//        if(K==0&&M!=P){
//            return 0;
//        }else if(K==0 && M==P){
//            return 1;
//        }
        if(dp[cur][step]!=-1){
            return dp[cur][step];
        }
        if(step==0){
            dp[cur][step]=cur==P?1:0;
            return dp[cur][step];
        }
        if(cur==1){
            dp[cur][step]=process2(N,2,P,step-1,dp);
            return dp[cur][step];
        }
        if(cur==N){
            dp[cur][step]=process2(N,N-1,P,step-1,dp);
            return dp[cur][step];
        }
        dp[cur][step]=process2(N,cur-1,P,step-1,dp)+process2(N,cur+1,P,step-1,dp);
        return dp[cur][step];
    }

    public static int walk3(int N,int cur,int des,int step){
        return process3(N,cur,des,step);
    }

    public static int process3(int n,int cur,int des,int step){
        int[][] dp=new int[n+1][step+1];
        for (int ste = 0; ste < dp[0].length; ste++) {
            for (int cu = 1; cu < dp.length; cu++) {
                if(ste==0){
                    dp[cu][ste]=cu==des?1:0;
                    continue;
                }
                if(cu==1){
                    dp[cu][ste]=dp[2][ste-1];
                    continue;
                }
                if(cu==n){
                    dp[cu][ste]=dp[cu-1][ste-1];
                    continue;
                }
                dp[cu][ste]=dp[cu-1][ste-1]+dp[cu+1][ste-1];
                /**
                 * if(K==0){
                 *             return P==M?1:0;
                 *         }
                 *         if(M==1){
                 *             return process1(N,2,P,K-1);
                 *         }
                 *         if(M==N){
                 *             return process1(N,N-1,P,K-1);
                 *         }
                 *         return process1(N,M+1,P,K-1)+process1(N,M-1,P,K-1);
                 */

            }

        }
        return dp[cur][step];
    }
}
