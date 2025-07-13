package algorithm.sort;

import java.io.*;

/**
 * 归并排序
 */
public class MergeSort {
    public static int MAXN = 501;
    public static int[] arr = new int[MAXN];
    public static int[] help = new int[MAXN];
    public static int n;

    /**
     * 归并排序递归版
     *
     * @param l
     * @param r
     */
    public static void mergeSort1(int l, int r) {
        if (l == r) {
            return;
        }
        int mid = (l + r) / 2;
        mergeSort1(l, mid);
        mergeSort1(mid + 1, r);
        merge(l, mid, r);
    }

    public static void mergeSort2() {
        for (int l, m, r, step = 1; step < n; step *= 2) {
            l = 0;
            while (l < n) {
                m = l + step - 1;
                if (m + 1 >= n) {
                    break;
                }
                r = Math.min(l + 2 * step - 1, n - 1);
                merge(l, m, r);
                l = r + 1;
            }
        }
    }

    private static void merge(int l, int mid, int r) {
        int i = l, a = l, b = mid + 1;
        while (a <= mid && b <= r) {
            help[i++] = arr[a] <= arr[b] ? arr[a++] : arr[b++];
        }
        while (a <= mid) {
            help[i++] = arr[a++];
        }
        while (b <= r) {
            help[i++] = arr[b++];
        }
        for (i = l; i <= r; i++) {
            arr[i] = help[l];
        }
    }

    public static void main(String[] args) throws IOException {
        /*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
            }
            out.print(arr[0]);

            for (int i = 0; i < n; i++) {
                out.print(" "+arr[i]);
            }
            out.println();

        }
        out.flush();
        out.close();
        br.close();*/
        int[] arr = new int[]{8, 3, 9, 2, 4, 8};
        mergeSort1(0, arr.length - 1);
    }
}
