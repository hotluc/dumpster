package algorithm.sort;

import java.util.Arrays;

//基数排序
public class RadixSort {
    //可以设置进制，不一定是10进制
    public static int BASE = 10;
    public static int MAXN = 50001;
    public static int[] help = new int[MAXN];
    public static int[] cnts = new int[BASE];

    public static void sortArray(int[] arr) {
        if (arr.length > 1) {
            //如果会溢出,那么用long类型数组排序
            int n = arr.length, min = arr[0];
            //找到数组最小值
            for (int i = 1; i < n; i++) {
                min = Math.min(min, arr[i]);
            }
            int max = 0;
            for (int i = 1; i < n; i++) {
                arr[i] -= min;
                max = Math.max(max, arr[i]);
            }
            //根据最大值在BASE进制的位数决定基数排序做几轮
            radixSort(arr, n, bits(max));
            //数组所有值都减去了最小值，所以最后不要忘了还原
            for (int i = 0; i < n; i++) {
                arr[i] += min;
            }
        }
    }

    //返回number在BASE进制下有几位
    public static int bits(int number) {
        int ans = 0;
        while (number > 0) {
            ans++;
            number /= 10;
        }
        return ans;
    }

    //基数排序核心代码
    //arr保证没有负数
    //n是arr长度
    //bits是arr中最大值在BASE进制下有几位
    public static void radixSort(int[] arr, int n, int bits) {
        for (int offset = 1; bits > 0; offset *= BASE, bits--) {
            Arrays.fill(cnts, 0);
            for (int i : arr) {
                //数字提取每一位的技巧
                cnts[(i / offset) % BASE]++;
            }
            //处理成前缀次数累加的形式
            for (int i = 1; i < arr.length; i++) {
                cnts[i] += cnts[i - 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                // 前缀数字分区技巧
                //数字提取某一位的技巧
                help[--cnts[(arr[i] / offset) % BASE]] = arr[i];
            }
            if (n >= 0) System.arraycopy(help, 0, arr, 0, n);
        }
    }
}
