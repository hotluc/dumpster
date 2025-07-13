package algorithm.map;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 克鲁斯卡尔算法
 */
public class Kruskal {
    //点
    public static int MAXN = 5001;
    //边
    public static int MAXM = 200001;
    public static int[] father = new int[MAXN];
    public static int[][] edges = new int[MAXM][3];
    public static int n, m;

    public static void build() {
        for (int i = 1; i <= n; i++) {
            father[i] = i;
        }
    }

    public static int find(int i) {
        if (i != father[i]) {
            father[i] = find(father[i]);
        }
        return father[i];
    }

    public static boolean union(int x,int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy){
            father[fx] = fy;
            return true;
        }
        else {
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            build();
            for (int i = 0; i < m; i++) {
                in.nextToken();
                edges[i][0] = (int) in.nval;
                in.nextToken();
                edges[i][1] = (int) in.nval;
                in.nextToken();
                edges[i][2] = (int) in.nval;
            }
        }
        Arrays.sort(edges,0,m, Comparator.comparingInt(a -> a[2]));
        int ans = 0;
        int edgesCnt = 0;
        for (int[] edge:edges){
            if (union(edge[0],edge[1])){
                edgesCnt++;
                ans += edge[2];
            }
        }
        out.println(edgesCnt == n - 1? ans : "orz");
        out.flush();
        out.close();
        br.close();
    }
}