package algorithm.adjacent_difference.two;

import java.io.*;

public class Code03_DiffMatrixLuogu {
    public static int MAXN = 1002;
    public static int[][] diff = new int[MAXN][MAXN];
    public static int n, q;

    public static void add(int a, int b, int c, int d, int k) {
        diff[a][b] += k;
        diff[c + 1][b] += k;
        diff[a][d + 1] -= k;
        diff[c + 1][d + 1] += k;
    }

    public static void build() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                diff[i][j] += diff[i][j - 1] + diff[i - 1][j] - diff[i - 1][j - 1];
            }
        }
    }

    public static void clear() {
        for (int i = 1; i <= n + 1; i++) {
            for (int j = 1; j <= n + 1; j++) {
                diff[i][j] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            q = (int) in.nval;
            for (int i = 1,a,b,c,d; i <= q; i++) {
                in.nextToken();
                a = (int) in.nval;
                in.nextToken();
                b = (int) in.nval;
                in.nextToken();
                c = (int) in.nval;
                in.nextToken();
                d = (int) in.nval;
                add(a, b, c, d, i);
            }
            build();
            for (int i = 1; i <= n; i++) {
                out.println(diff[i][1]);
                for (int j = 2; j <= n; j++) {
                    out.print(diff[i][j] + " ");
                }
                out.println();
            }
        }
        out.flush();
        out.close();
        br.close();
    }
}