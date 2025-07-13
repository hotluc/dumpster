package algorithm.sort;


/**
 * 堆排序
 */
public class HeapSort {
    //i位置的数,向上调整大根堆
    public static void heapInsert(int[] arr,int i){
        while (arr[i]>arr[(i-1)/2]){
            swap(arr,i,(i-1)/2);
            i=(i-1)/2;
        }
    }
    //i位置的数,向下调整大根堆
    public static void heapify(int[] arr,int i,int size) {
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
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    // 从顶到底建立大根堆，O(n * logn)
    // 依次弹出堆内最大值并排好序，O(n * logn)
    // 整体时间复杂度O(n * logn)
    public static void heapSort1(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            heapInsert(arr,i);
        }
        int size = n;
        while (size > 1) {
            swap(arr,0,--size);
            heapify(arr,0,size);
        }
    }
    // 从底到顶建立大根堆，O(n)
    // 依次弹出堆内最大值并排好序，O(n * logn)
    // 整体时间复杂度O(n * logn)
    public static void heapSort2(int[] arr) {
        int n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            heapify(arr, i, n);
        }
        int size = n;
        while (size > 1) {
            swap(arr,0,--size);
            heapify(arr,0,size);
        }
    }
}
