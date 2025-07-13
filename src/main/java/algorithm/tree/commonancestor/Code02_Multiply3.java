package algorithm.tree.commonancestor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Code02_Multiply3 {

    public static int MAXN = 500001;

    public static int LIMIT = 20;

    public static int power;

    public static int log2(int n) {
        int ans = 0;
        while ((1 << ans) <= (n >> 1)) {
            ans++;
        }
        return ans;
    }

    public static int cnt;

    public static int[] head = new int[MAXN];

    public static int[] next = new int[MAXN << 1];

    public static int[] to = new int[MAXN << 1];

    public static int[][] stjump = new int[MAXN][LIMIT];

    public static int[] deep = new int[MAXN];

    public static void build(int n) {
        power = log2(n);
        cnt = 1;
        Arrays.fill(head, 1, n + 1, 0);
    }

    public static void addEdge(int u, int v) {
        next[cnt] = head[u];
        to[cnt] = v;
        head[u] = cnt++;
    }
    // bfs
    public static void bfs(int root) {
        int[] queue = new int[MAXN];
        int front = 0, rear = 0;

        deep[root] = 1;
        queue[rear++] = root;

        while (front < rear) {
            int u = queue[front++];
            for (int e = head[u]; e != 0; e = next[e]) {
                int v = to[e];
                if (deep[v] == 0) {
                    deep[v] = deep[u] + 1;
                    stjump[v][0] = u;
                    for (int p = 1; p <= power; p++) {
                        stjump[v][p] = stjump[stjump[v][p - 1]][p - 1];
                    }
                    queue[rear++] = v;
                }
            }
        }
    }

    public static int lca(int a, int b) {
        if (deep[a] < deep[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        for (int p = power; p >= 0; p--) {
            if (deep[stjump[a][p]] >= deep[b]) {
                a = stjump[a][p];
            }
        }
        if (a == b) {
            return a;
        }
        for (int p = power; p >= 0; p--) {
            if (stjump[a][p] != stjump[b][p]) {
                a = stjump[a][p];
                b = stjump[b][p];
            }
        }
        return stjump[a][0];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        in.nextToken();
        int n = (int) in.nval;
        in.nextToken();
        int m = (int) in.nval;
        in.nextToken();
        int root = (int) in.nval;
        build(n);
        for (int i = 1, u, v; i < n; i++) {
            in.nextToken();
            u = (int) in.nval;
            in.nextToken();
            v = (int) in.nval;
            addEdge(u, v);
            addEdge(v, u);
        }
        bfs(root);
        for (int i = 1, a, b; i <= m; i++) {
            in.nextToken();
            a = (int) in.nval;
            in.nextToken();
            b = (int) in.nval;
            out.println(lca(a, b));
        }
        out.flush();
        out.close();
        br.close();
    }

}
