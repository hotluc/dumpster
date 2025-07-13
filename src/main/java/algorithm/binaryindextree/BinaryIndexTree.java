package algorithm.binaryindextree;

import java.util.concurrent.locks.Lock;

/**
 * 树状数组
 */
public class BinaryIndexTree {
    public static int MAXN = 500001;
    public static int[] tree = new int[MAXN];
    public static int n, m;

    public static int lowbit(int i) {
        return i & -i;
    }

    public static void add(int i, int v) {
        while (i <= n) {
            tree[i] += v;
            i += lowbit(i);
        }
    }

    public static int sum(int i) {
        int ans = 0;
        while (i > 0) {
            ans += tree[i];
            i -= lowbit(i);
        }
        return ans;
    }

    public static int range(int l,int r) {
        return sum(r) - sum(l-1);
    }
    public static int divisorSubstrings(int num, int k) {
        String str = String.valueOf(num);
        int ans = 0;
        int n = str.length();
        for (int i = 0; i <= n-k; i++) {
            int temp = Integer.parseInt(str.substring(i,i+k));
            if (temp!=0 && num%temp==0){
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        System.out.println(divisorSubstrings(430043,2));
    }
}