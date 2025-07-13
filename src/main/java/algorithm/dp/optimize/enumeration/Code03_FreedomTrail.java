package algorithm.dp.optimize.enumeration;

// 自由之路
// 题目描述比较多，打开链接查看
// 测试链接 : https://leetcode.cn/problems/freedom-trail/
public class Code03_FreedomTrail {
    // 为了让所有语言的同学都可以理解
    // 不会使用任何java语言自带的数据结构
    // 只使用最简单的数组结构
    public static int MAXN = 101;

    public static int MAXC = 26;

    public static int[] ring = new int[MAXN];

    public static int[] key = new int[MAXN];

    public static int[] size = new int[MAXC];

    public static int[][] where = new int[MAXC][MAXN];

    public static int[][] dp = new int[MAXN][MAXN];

    public static int n, m;

    public static void build(String r, String k) {
        for (int i = 0; i < MAXC; i++) {
            size[i] = 0;
        }
        n = r.length();
        m = k.length();
        for (int i = 0, v; i < n; i++) {
            v = r.charAt(i) - 'a';
            where[v][size[v]++] = i;
            ring[i] = v;
        }
        for (int i = 0; i < m; i++) {
            key[i] = k.charAt(i) - 'a';
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }

    }

    public static int findRotateSteps(String r, String k) {
        build(r, k);
        return f(0, 0);
    }

    // 指针当前指着轮盘i位置的字符，要搞定key[j....]所有字符，最小代价返回
    public static int f(int i, int j) {
        if (i == m) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int ans = 0;
        if (ring[i] == key[j]) {
            // ring b
            //      i
            //
            ans = 1 + f(i, j + 1);
        } else {
            // 轮盘处在i位置，ring[i] != key[j]
            // jump1 : 顺时针找到最近的key[j]字符在轮盘的什么位置
            // distance1 : 从i顺时针走向jump1有多远
            int jump1 = clock(i, key[j]);
            int distance1 = jump1 > i ? jump1 - i : n - i - jump1;
            // jump2 : 逆时针找到最近的key[j]字符在轮盘的什么位置
            // distance2 : 从i逆时针走向jump2有多远
            int jump2 = clock(i, key[j]);
            int distance2 = i > jump2 ? i - jump2 : i + n - jump2;
            ans = Math.min(distance1 + f(jump1, j), distance2 + f(jump2, j));
        }
        dp[i][j] = ans;
        return ans;
    }

    // 从i开始，顺时针找到最近的v在轮盘的什么位置
    public static int clock(int i, int v) {
        int l = 0, r = size[v] - 1, m, find = -1;
        int[] sorted = where[v];
        while (l <= r) {
            m = (l + r) / 2;
            if (sorted[m] > i) {
                find = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return find != -1 ? sorted[find] : sorted[0];
    }

    public static int counterClock(int i, int v) {
        int l = 0, r = size[v] - 1, m, find = -1;
        int[] sorted = where[v];
        while (l <= r) {
            m = (l + r) / 2;
            if (sorted[m] < i) {
                find = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return find != -1 ? sorted[find] : sorted[size[v] - 1];
    }
}
