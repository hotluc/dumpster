package algorithm.orderedlist.splay;
// Splay树的实现，不用词频压缩，java版
// 实现一种结构，支持如下操作，要求单次调用的时间复杂度O(log n)
// 1，增加x，重复加入算多个词频
// 2，删除x，如果有多个，只删掉一个
// 3，查询x的排名，x的排名为，比x小的数的个数+1
// 4，查询数据中排名为x的数
// 5，查询x的前驱，x的前驱为，小于x的数中最大的数，不存在返回整数最小值
// 6，查询x的后继，x的后继为，大于x的数中最小的数，不存在返回整数最大值
// 所有操作的次数 <= 10^5
// -10^7 <= x <= +10^7
// 测试链接 : https://www.luogu.com.cn/problem/P3369
// 提交以下的code，提交时请把类名改成"Main"，可以通过所有测试用例

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
public class Code01_Splay1 {
    public static int MAXN = 100001;

    public static int head = 0;

    public static int cnt = 0;

    public static int[] key = new int[MAXN];

    public static int[] father = new int[MAXN];

    public static int[] left = new int[MAXN];

    public static int[] right = new int[MAXN];

    public static int[] size = new int[MAXN];

    public static void up(int i) {
        size[i] = size[left[i]] + size[right[i]] + 1;
    }
    public static int lr(int i) {
        return right[father[i]] == i ? 1 : 0;
    }
    // 编号为i的节点上升一步，在结构上调整
    public static void rotate(int i) {
        int f = father[i],g = father[f],soni = lr(i),sonf = lr(f);
    }
}
