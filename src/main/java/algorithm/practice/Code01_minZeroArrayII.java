package algorithm.practice;

import java.util.Arrays;

public class Code01_minZeroArrayII {
    public static int MAXN = 400004;
    public static int[] arr = new int[MAXN];
    public static int[] sum = new int[MAXN];
    public static int[] add = new int[MAXN];
    public static int[] max = new int[MAXN];

    public static void up(int i) {
        sum[i] = sum[i << 1] + sum[(i << 1) | 1];
        max[i] = Math.max(max[i << 1], max[(i << 1) | 1]);
    }

    public static void down(int i, int ln, int rn) {
        if (add[i] != 0) {
            lazy(i << 1, add[i], ln);
            lazy((i << 1) | 1, add[i], rn);
            add[i] = 0;
        }
    }

    public static void lazy(int i, int v, int n) {
        sum[i] += v * n;
        add[i] += v;
        max[i] += v;
    }

    public static void build(int l, int r, int i) {
        add[i] = 0;
        if (l == r) {
            sum[i] = arr[l];
            max[i] = arr[l];
        } else {
            int mid = (l + r) >> 1;
            build(l, mid, i << 1);
            build(mid + 1, r, (i << 1) | 1);
            up(i);
        }
    }

    public static void add(int jobl, int jobr, int jobv, int l, int r, int i) {
        if (jobl <= l && r <= jobr) {
            lazy(i, jobv, r - l + 1);
        } else {
            int mid = (l + r) >> 1;
            down(i, mid - l + 1, r - mid);
            if (jobl <= mid) {
                add(jobl, jobr, jobv, l, mid, i << 1);
            }
            if (jobr > mid) {
                add(jobl, jobr, jobv, mid + 1, r, (i << 1) | 1);
            }
            up(i);
        }
    }

    public static int queryAll() {
        return max[1];
    }

    public static int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }
        build(1, n, 1);
        if(queryAll()<=0){
            return 0;
        }
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0]+1;
            int r = queries[i][1]+1;
            int v = -queries[i][2];
            add(l, r, v, 1, n, 1);
            if(queryAll()<=0){
                return i+1;
            }
        }
        return -1;
    }
    public static int findPeakElement(int[] arr) {
        int n = arr.length;
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > arr[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    public static void main(String[] args) {
        int[] nums = {2,0,2};
        int[][] queries = {{0, 2, 1},{0,2,1},{1,1,3}};
        int[] nums1 = {2};
        int[][] queries1 = {{0, 0, 3},{0, 0, 3},{0, 0, 4},{0, 0, 1},{0, 0, 2},{0,0,5},{0,0,4},{0,0,5}};
        int[] nums2 = {4,5,6,7,0,1,2};
        System.out.println();
    }
}
