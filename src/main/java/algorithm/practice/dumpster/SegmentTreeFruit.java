package algorithm.practice.dumpster;

public class SegmentTreeFruit {
    public static int MAXN = 100005;
    public static int[] arr = new int[MAXN];
    public static int[] max = new int[MAXN<<2];
    public static void up(int i){
        max[i] = Math.max(max[i<<1], max[i<<1|1]);
    }
    public static void build(int l, int r,int i){
        if (l==r){
            max[i] = arr[l];
        }
        else{
            int mid = (l+r)/2;
            build(l, mid, i << 1);
            build(mid + 1, r, i << 1 | 1);
            up(i);
        }
    }
    public static int query(int jobl, int jobr, int l, int r, int i, int v) {
        if (r < jobl || l > jobr || max[i] < v) {
            return -1;
        }
        if (l == r) {
            return l;
        }
        int mid = (l + r) >> 1;
        int res = query(jobl, jobr, l, mid, i << 1, v);
        if (res != -1) return res;
        return query(jobl, jobr, mid + 1, r, i << 1 | 1, v);
    }

    public static void update(int jobl, int jobr, int l, int r, int i) {
        // jobl == jobr，表示更新的点
        if (l == r) {
            max[i] = 0;
            return;
        }
        int mid = (l + r) >> 1;
        if (jobr <= mid) {
            update(jobl, jobr, l, mid, i << 1);
        } else if (jobl > mid) {
            update(jobl, jobr, mid + 1, r, i << 1 | 1);
        } else {
            // 理论上不用支持区间更新，这里写也不会用
            update(jobl, mid, l, mid, i << 1);
            update(mid + 1, jobr, mid + 1, r, i << 1 | 1);
        }
        up(i);
    }

}
