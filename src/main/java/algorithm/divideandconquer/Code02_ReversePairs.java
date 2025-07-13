package algorithm.divideandconquer;
// 翻转对数量
// 测试链接 : https://leetcode.cn/problems/reverse-pairs/
public class Code02_ReversePairs {
    public static int MAXN = 50001;
    public static int[] help = new int[MAXN];
    public static int reversePairs(int[] arr) {
        return counts(arr,0,arr.length-1);
    }
    public static int counts(int[] arr, int left, int right) {
        if (left == right){
            return 0;
        }
        int mid = (left + right)/2;
        return counts(arr, left,mid) + counts(arr,mid+1, right)+merge(arr, left,mid, right);
    }
    public static int merge(int[] arr, int left, int mid, int right) {
        //统计部分
        int ans = 0;
        for (int i = left,j=mid+1; i <= mid; i++) {
            while (j<=right && (long)arr[i] > (long) arr[j]*2) {
                j++;
            }
            ans += j-mid+1;
        }
        // 正常merge
        int i = left;
        int a = left;
        int b = mid+ 1;
        while (a <= mid && b <= right) {
            help[i++] = arr[a] <= arr[b] ? arr[a++] : arr[b++];
        }
        while (a <= mid) {
            help[i++] = arr[a++];
        }
        while (b <= right) {
            help[i++] = arr[b++];
        }
        for (i = left; i <= right; i++) {
            arr[i] = help[i];
        }
        return ans;
    }
}
