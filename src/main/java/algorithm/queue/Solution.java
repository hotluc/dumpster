package algorithm.queue;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public static int MAXN = 100001;
    public static int[] deque = new int[20];
    public static int head,tail;
    public static int[] maxSlidingWindow(int[] arr,int k){
        head=tail=0;
        int len = arr.length;
        for (int i = 0; i < k-1; i++) {
            while (head<tail&&arr[deque[tail-1]]<=arr[i]){
                tail--;
            }
            deque[tail++]=i;
        }
        int m = len-k+1;
        int[]ans=new int[m];
        for (int l = 0,r=k-1; l < m; l++,r++) {
            while (head<tail&&arr[deque[tail-1]]<=arr[r]){
                tail--;
            }
            deque[tail++]=r;
            ans[l] = arr[deque[head]];
            if (deque[head]==l){
                head++;
            }
        }

        return ans;

    }

    public static void main(String[] args) {
        int[]nums = new int[]{1,3,-1,-3,5,3,6,7};int k = 3;
        maxSlidingWindow(nums,3);
        System.out.println(Arrays.toString(deque));

    }
}
