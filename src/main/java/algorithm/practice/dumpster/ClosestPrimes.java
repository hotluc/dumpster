package algorithm.practice.dumpster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class ClosestPrimes {
    public static int[] closestPrimes(int left, int right) {
        List<Integer> primes = getPrimes(left, right);
        int[] ans = new int[2];
        int diff = Integer.MAX_VALUE;
        if (primes.size() <= 1) {
            return new int[]{-1, -1};
        }
        for (int i = 1; i < primes.size(); i++) {
            if (primes.get(i) - primes.get(i - 1) < diff) {
                ans[0] = primes.get(i - 1);
                ans[1] = primes.get(i);
                diff = primes.get(i) - primes.get(i - 1);
            }
        }
        return ans;
    }

    public static List<Integer> getPrimes(int left, int right) {
        boolean[] visited = new boolean[right + 1];
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i * i <= right; i++) {
            if (!visited[i]) {
                for (int j = i * i; j <= right; j += i) {
                    visited[j] = true;
                }
            }
        }
        for (int i = 2; i <= right; i++) {
            if (!visited[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    public static final int MAXH = 100010; // 高度最多这么多
    public static int[] max = new int[MAXH << 2]; // 最大值数组

    // 线段树区间查询最大值
    public static int query(int ql, int qr, int l, int r, int i) {
        if (ql <= l && r <= qr) {
            return max[i];
        }
        int mid = (l + r) >> 1;
        int ans = 0;
        if (ql <= mid) {
            ans = Math.max(ans, query(ql, qr, l, mid, i << 1));
        }
        if (qr > mid) {
            ans = Math.max(ans, query(ql, qr, mid + 1, r, i << 1 | 1));
        }
        return ans;
    }

    // 单点更新：将位置 idx 的值更新为 max(原来的, val)
    public static void update(int idx, int val, int l, int r, int i) {
        if (l == r) {
            max[i] = Math.max(max[i], val);
            return;
        }
        int mid = (l + r) >> 1;
        if (idx <= mid) {
            update(idx, val, l, mid, i << 1);
        } else {
            update(idx, val, mid + 1, r, i << 1 | 1);
        }
        max[i] = Math.max(max[i << 1], max[i << 1 | 1]);
    }

    public static int maxHeight(int[][] cuboids) {
        int n = cuboids.length;
        for (int[] cuboid : cuboids) {
            Arrays.sort(cuboid);
        }
        Arrays.sort(cuboids, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[2] - b[2];
        });
        return cuboids[0][1];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(closestPrimes(1, 2)));
        System.out.println(getPrimes(1, 2));
    }
}
