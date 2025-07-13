package algorithm.dp.optimize.binarylifting;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;



public class Code04_FindNear {
    public static int MAXN = 10001;
    public static int[] arr = new int[MAXN];
    public static int n;
    public static int[] to1 = new int[MAXN];
    public static int[] dist1 = new int[MAXN];
    public static int[] to2 = new int[MAXN];
    public static int[] dist2 = new int[MAXN];
    // 如下三个数组只有near2方法需要
    public static int[][] rank = new int[MAXN][2];
    public static int[] last = new int[MAXN];
    public static int[] next = new int[MAXN];
    // 有序表的实现
    public static void near1(){
        TreeSet<int[]> set = new TreeSet<>(Comparator.comparing(o->o[1]));
        for (int i = n; i >= 0 ; i--) {
            to1[i] = 0;
            dist1[i] = 0;
            to2[i] = 0;
            dist2[i] = 0;
            int[] cur = new int[]{i,arr[i]};
            int[] p1 = set.floor(cur);
            int[] p2 = p1!=null ? set.floor(new int[]{p1[0],p1[1]-1}) : null;
            int[] p3 = set.ceiling(cur);
            int[] p4 = p3!=null ? set.floor(new int[]{p3[0],p3[1]+1}) : null;
            /*update(i,p1!=null?p1[0]:0);
            update(i,p2!=null?p2[0]:0);
            update(i,p3!=null?p3[0]:0);
            update(i,p4!=null?p4[0]:0);
            set.add(cur);*/
        }
    }
}
