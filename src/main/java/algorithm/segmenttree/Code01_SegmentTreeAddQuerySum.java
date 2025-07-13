package algorithm.segmenttree;

import java.util.Arrays;

// 线段树支持范围增加、范围查询
// 维护累加和
// 测试链接 : https://www.luogu.com.cn/problem/P3372
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Code01_SegmentTreeAddQuerySum {
    public static int MAXN = 100001;
    public static int[] arr = new int[MAXN];
    public static int[] sum = new int[MAXN];
    public static int[] add = new int[MAXN];
    public static int[] max = new int[MAXN];
    // 累加和信息的汇总
    public static void up(int i){
        // 父范围的累加和 = 左范围累加和 + 右范围累加和
        sum[i] = sum[i<<1]+sum[i<<1+1];
        max[i] = Math.max(max[i<<1],max[i<<1+1]);
    }
    // 懒信息的下发
    public static void down(int i,int ln,int rn){
        if (add[i]!=0){
            // 发左
            lazy(i<<1,add[i], ln);
            // 发右
            lazy(i << 1 | 1, add[i], rn);
            // 父范围懒信息清空
            add[i] = 0;
        }
    }

    // 当前来到l~r范围，对应的信息下标是i，范围上数字的个数是n = r-l+1
    // 现在收到一个懒更新任务 : l~r范围上每个数字增加v
    // 这个懒更新任务有可能是任务范围把当前线段树范围全覆盖导致的
    // 也有可能是父范围的懒信息下发下来的
    // 总之把线段树当前范围的sum数组和add数组调整好
    // 就不再继续往下下发了，懒住了
    public static void lazy(int i,int v,int n){
        sum[i] += v*n;
        add[i] += v;
        max[i] = v;
    }

    // 建树
    public static void build(int l, int r, int i) {
        if (l == r) {
            sum[i] = arr[l]=max[i];
        } else {
            int mid = (l + r) >> 1;
            build(l, mid, i << 1);
            build(mid + 1, r, i << 1 | 1);
            up(i);
        }
        add[i] = 0;
        max[i] = 0;
    }
    // 范围修改
    // jobl ~ jobr范围上每个数字增加jobv
    public static void add(int jobl, int jobr, int jobv, int l, int r, int i) {
        jobl++;
        jobr++;
        if (jobl <= l && r <= jobr) {
            lazy(i, jobv, r - l + 1);
        } else {
            int mid = (l + r) >> 1;
            down(i, mid - l + 1, r - mid);
            if (jobl <= mid) {
                add(jobl, jobr, jobv, l, mid, i << 1);
            }
            if (jobr > mid) {
                add(jobl, jobr, jobv, mid + 1, r, i << 1 | 1);
            }
            up(i);
        }
    }
    public static int query(int jobl, int jobr, int l, int r, int i) {
        if (jobl <= l && r <= jobr) {
            return max[i];
        }
        int mid = (l + r) >> 1;
        down(i, mid - l + 1, r - mid);
        int ans = Integer.MAX_VALUE;
        if (jobl <= mid) {
            ans = Math.min(ans, query(jobl, jobr, l, mid, i << 1));
        }
        if (jobl > mid) {
            ans = Math.min(ans, query(jobl, jobr, mid + 1, r, i << 1 | 1));
        }
        return ans;
    }
    public static int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length,k=0;
        build(1, n,1);
        for (int i = 0; i < nums.length; i++) {
            add(i,i,nums[i],1,n,1);
        }
        for (int[] query : queries) {
            add(query[0], query[1], -query[3], 1, n, 1);
            if (query(1, n, 1, n, 1) > 0) {
                k++;
            }
        }
        return k;
    }
    public static void main(String[] args) {
        minZeroArray(new int[]{2,0,2},new int[][]{{0,2,1},{0,2,1},{1,1,3}});
    }
}