package algorithm.heap;

import java.util.PriorityQueue;

/**
 * 堆
 */
public class Heap {
    //i位置的数,向上调整大根堆
    public void heapInsert(int[] arr,int i){
        while (arr[i]>arr[(i-1)/2]){
            swap(arr,i,(i-1)/2);
            i=(i-1)/2;
        }
    }
    //i位置的数,向下调整大根堆
    public void heapify(int[] arr,int i,int size) {
        int left = 2 * i + 1;
        while (left < size){
            int best = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            best = arr[best] > arr[i] ? best : i;
            if (best == i){
                break;
            }
            swap(arr,i,best);
            i = best;
            left = 2 * i + 1;
        }
    }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    //从顶点开始,向下调整大根堆，0(nlogn)
    public void heapSort1(int[] arr){
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            heapInsert(arr,i);
        }
        int size = n;
        while (size > 1){
            swap(arr,0,--size);
            heapify(arr,0,size);
        }
    }
    //从底部开始,向上调整大根堆，0(n)
    public void heapSort2(int[] arr){
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1,o2)-> o2-o1);
        int n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            heapify(arr,i,n);
        }
        int size = n;
        while (size > 1){
            swap(arr,0,--size);
            heapify(arr,0,size);
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(4);
        heap.add(2);
        heap.add(1);
        heap.add(3);
            System.out.println(heap.poll());

    }
}
