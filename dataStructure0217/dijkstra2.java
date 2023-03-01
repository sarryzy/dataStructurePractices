package com.zhangyu.datastructure.dataStructure0217;
import com.zhangyu.datastructure.dataStructure0216.Graph.*;

import java.util.HashMap;
import java.util.HashSet;

public class dijkstra2 {
    /**
     * ### 2.使用小根堆优化dijkstra算法
     *
     * 上面的dijkstra算法每一次在找距离最小的节点的时候,需要遍历哈希表中所有的值,
     * 如果使用一个小根堆,那么每次就可以直接使用即可.但是这个小根堆里面的值可能发生修改,因此需要手动实现小根堆.
     * 小根堆需要满足的条件:每次弹出一个最小值,并且这个弹出的值不能够再次弹出,可以使用set来记录弹出过的值.
     */
    public static class NodeHeap{
        Node[] arr;
        HashMap<Node,Integer> location;//标记堆中节点的位置,弹出则记录为-1
        HashMap<Node,Integer> distance;//标记堆中节点的距离

        int heapSize;

        public NodeHeap(){
            arr=new Node[200];
            heapSize=0;
            location=new HashMap<>();
            distance=new HashMap<>();
        }

        public void heapInsert(Node node,int i){
            //在i位置向上进行heapinsert
            arr[heapSize++]=node;
            while (arr[i].val<arr[(i-1)/2].val){
                //当前位置的值比其父节点的值要小的时候,交换
                swap(arr,i,(i-1)/2);
            }
        }

        public void heapDown(int i){
            //在i位置向下heapdown
            int index=i;
            int left=2*i+1;
            while (left<heapSize){
                left=left+1<heapSize && arr[left+1].val<arr[left].val?left+1:left;
                if(arr[index].val<=arr[left].val){
                    break;
                }
                swap(arr,index,left);
                index=left;
                left=2*index+1;
            }
        }

        public void heapUp(int i){
            while (arr[i].val<arr[(i-1)/2].val){
                //当前位置的值比其父节点的值要小的时候,交换
                swap(arr,i,(i-1)/2);
            }
        }

        public void heapify(int i){
            //向上向下开始heapify
            heapUp(i);
            heapDown(i);
        }



        /**
         * 节点的值代表到这个位置的距离
         * @param node
         */
        public void addOrUpdateOrIgnore(Node node){
            if(!location.containsKey(node)){
                //当位置表中没有的时候,说明没有弹出过,直接加进去
                heapInsert(node,heapSize++);
            }else{
                //set中有,说明需要更新或者忽略
                //如果需要忽略,则代表已经弹出过,这个位置的值已经被记下来了
                int loc=location.get(node);
                if(loc==-1){
                    return;
                }
                //此处代表需要更新
                if(node.val<arr[loc].val){
                    arr[loc].val=node.val;
                    heapify(loc);
                }
            }
        }

        public static void swap(Node[] arr,int i,int j){
            Node temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
        }
    }
}
