package algorithm.binary;


/**
 * n皇后的位运算版本
 */
public class NQueen {
    //数组路径信息实现的N皇后问题，不推荐
    public static int totalNQueenS1(int n) {
        if (n < 1) {
            return 0;
        }
        return f1(0, new int[n], n);
    }

    private static int f1(int i, int[] path, int n) {
        if (i == n) {
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (check(path, i, j)) {
                path[i] = j;
                res += f1(i + 1, path, n);
            }
        }
        return res;
    }

    private static boolean check(int[] path, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (j == path[k] || Math.abs(i - k) == Math.abs(path[k] - j)) {
                return false;
            }
        }
        return true;
    }

    //用位信息表示路径实现的N皇后问题，推荐
    public static int totalNQueenS2(int n) {
        if (n < 1) {
            return 0;
        }
        int limit = (1 << n) - 1;
        return f2(limit, 0, 0, 0);
    }

    private static int f2(int limit, int col, int left, int right) {
        if (col == limit) {
            return 1;
        }
        int ban = col | left | right;
        int candidate = limit & (~ban);
        int place, ans = 0;
        while (candidate != 0) {
            place = candidate & (-candidate);
            candidate ^= place;
            ans += f2(limit, col | place, (left | place) >> 1, (right | place) << 1);
        }
        return ans;
    }
}
