package com.zhangyu.datastructure.dataStructure0131;

public class practice03 {
    public static void main(String[] args){
        int[] arr={1,1,2,2,2,3,3,3,4,4,4,4,5,5,5,5};
        getNum(arr);
    }

    public static ListNode Merge(ListNode list1,ListNode list2){
        //合并两个链表
        //首先抓出头结点
        if(list1==null || list2== null){
            return list1==null ? list2:list1;
        }
        ListNode head=list1.val<=list2.val?list1:list2;
        ListNode cur=head;
        ListNode cur1=head.next;
        ListNode cur2=head==list1?list2:list1;
        while(cur1!=null && cur2!=null){
            if(cur1.val<=cur2.val){
                cur.next=cur1;
                cur=cur.next;
                cur1=cur1.next;
            }else{
                cur.next=cur2;
                cur=cur.next;
                cur2=cur2.next;
            }
        }
        cur.next=cur1==null?cur2:cur1;
        return head;
    }

    /**
     * 寻找>=value的最左位置
     */
    public static int findVal(int[] arr,int l,int r,int val){
        int index=-1;
        while (l<=r){
            int mid=l+(r-l)/2;
            if(arr[mid]>=val){
                index=mid;
                r=mid-1;
            }else{
                l=mid+1;
            }
        }
        return index;
    }

    /**
     * 局部最小值问题
     */
    public static int partMin(int[] arr){
        int n=arr.length;
        if(arr[0]<arr[1]){
            return 0;
        }
        if(arr[n-1]<arr[n-2]){
            return n-1;
        }
        int l=1;
        int r=n-2;
        while(l<=r){
            int mid=l+(r-l)/2;
            if(arr[mid]<arr[mid+1] && arr[mid]<arr[mid-1]){
                return mid;
            }
            else if(arr[mid]>arr[mid-1]){
                r=mid-1;
            }else{
                l=mid+1;
            }
        }
        return 0;
    }

    public static int findPeakElement (int[] arr){
        int n=arr.length;
        if(arr[0]>arr[1]){
            return 0;
        }
        if(arr[n-1]>arr[n-2]){
            return n-1;
        }
        int l=1;
        int r=n-2;
        while(l<=r){
            int mid=l+(r-l)/2;
            if(arr[mid]>arr[mid+1] && arr[mid]>arr[mid-1]){
                return mid;
            }
            else if(arr[mid]<arr[mid-1]){
                r=mid-1;
            }else{
                l=mid+1;
            }
        }
        return 0;
    }

    /**
     * 两个数出现了奇数次,其他的数都出现了偶数次
     */
    public static void getNum(int[] arr){
        int eor=0;
        for (int i = 0; i < arr.length; i++) {
            eor=eor^arr[i];
        }
        //最终得到的eor为a^b
        int eor1=eor&(~eor+1);
        int eor2=0;
        for (int i = 0; i < arr.length; i++) {
            if((arr[i]&eor1)!=0){
                eor2^=arr[i];
            }
        }
        System.out.println((eor2^eor1)+" "+(eor^(eor1^eor2)));
    }
}
