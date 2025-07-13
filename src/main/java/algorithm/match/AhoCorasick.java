package algorithm.match;

/**
 * AC自动机
 */
public class AhoCorasick {
    //目标字符串的数量
    public static int MAXN = 200001;
    //所有目标字符串的总字符数量
    public static int MAXS = 200001;
    //记录每个目标字符串的结尾字符位置
    public static int[] end = new int[MAXN];
    //AC自动机的节点
    public static int[][] tree = new int[MAXS][26];
    //每个节点的fail指针
    public static int[] fail = new int[MAXS];
    public static int count = 0;
    //收集词频
    public static int[] times = new int[MAXS];
    //可以用作队列或栈
    public static boolean[] box = new boolean[MAXS];
    // 链式前向星，为了建立fail指针的反图
    public static int[] head = new int[MAXS];

    public static int[] next = new int[MAXS];

    public static int[] to = new int[MAXS];

    public static int edge = 0;
    // 遍历fail反图，递归方法会爆栈，所以用非递归替代
    public static boolean[] visited = new boolean[MAXS];
    // AC自动机加入目标字符串
    public static void insert(int index,String str) {
        char[] s = str.toCharArray();
        int u = 0;
        for (int i = 0,cur; i < s.length; i++) {
            cur= s[i] - 'a';
            if (tree[u][cur] == 0) {
                tree[u][cur] = ++count;
            }
            u = tree[u][cur];
        }
        end[index] = u;
    }
}
