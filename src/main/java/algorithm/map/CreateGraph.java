package algorithm.map;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 建图
 */
public class CreateGraph {
    //最大顶点数
    public static int MAXN = 10;
    //最大边数
    public static int MAXM = 21;
    //邻接矩阵建图
    public static int[][] graph1 = new int[MAXN][MAXN];
    //邻接表建图
    public static ArrayList<ArrayList<int[]>> graph2 = new ArrayList<>();

    //链式前向星建图
    public static int[] head = new int[MAXN];
    public static int[] next = new int[MAXN];
    public static int[] to = new int[MAXM];
    public static int[] weight = new int[MAXM];
    public static int cnt;

    public static void buildGraph(int n){
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                graph1[i][j] = 0;
            }
        }
        graph2.clear();
        for (int i = 0; i <= n; i++) {
            graph2.add(new ArrayList<>());
        }
        //链式前向星清空
        cnt = 1;
        Arrays.fill(head, 1,n+1,0);
    }
    //链式前向星加边
    public static void addEdge(int u, int v,int w){
        // u -> v , 边权重是w
        next[cnt] = head[u];
        to[cnt] = v;
        weight[cnt] = w;
        head[u] = cnt++;

    }
    public static void directGraph(int[][] edges){
        for (int[] edge : edges) {
            //System.out.println("f"+Arrays.toString(edge));
            graph1[edge[0]][edge[1]] = edge[2];
        }
        for (int[] edge : edges) {
            graph2.get(edge[0]).forEach(x -> System.out.println(x[0] + "0,1 " + x[1]));
            graph2.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        for (int[] edge : edges) {
            addEdge(edge[0], edge[1], edge[2]);
        }
    }
    public static void undirectGraph1(int[][] edges){
        for (int[] edge : edges) {
            edges[edge[0]][edge[1]] = edge[2];
            edges[edge[1]][edge[0]] = edge[2];
        }
    }
    public static void undirectGraph2(int[][] edges){
        for (int[] edge : edges) {
            graph2.get(edge[0]).add(new int[]{edge[1], edge[2]});
            graph2.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }
    }
    public static void traverseGraph(int n){
        System.out.println("邻接矩阵：");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(graph1[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("邻接表：");
        for (int i = 1; i <= n; i++) {
            System.out.print(i + "(邻居.边权): ");
            for (int[] edge : graph2.get(i)) {
                System.out.print("[" + edge[0] + "," + edge[1] + "] ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // 理解了带权图的建立过程，也就理解了不带权图
        // 点的编号为1...n
        // 例子1自己画一下图，有向带权图，然后打印结果
        int n1 = 4;
        int[][] edges1 = { { 1, 3, 6 }, { 4, 3, 4 }, { 2, 4, 2 }, { 1, 2, 7 }, { 2, 3, 5 }, { 3, 1, 1 } };
        buildGraph(n1);
        directGraph(edges1);
        traverseGraph(n1);
        System.out.println("==============================");
    }
}
