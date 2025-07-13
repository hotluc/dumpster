package algorithm.dp.knapsack;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class PartitionedKnapSack {
    public static int MAXN = 1001;
    public static int MAXM = 1001;
    /**
     * arr[i][0]表示第i个物品的体积
     * arr[i][1]表示第i个物品的价值
     * arr[i][2]表示第i个物品的所属组别
     */
    public static int[][] arr = new int[MAXN][3];
    public static int[] dp = new int[MAXM];
    public static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(bufferedReader);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            m = (int) in.nval;
            in.nextToken();
            n = (int) in.nval;
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                arr[i][0] = (int) in.nval;
                in.nextToken();
                arr[i][1] = (int) in.nval;
                in.nextToken();
                arr[i][2] = (int) in.nval;
            }
            Arrays.sort(arr, 1, n + 1, Comparator.comparingInt(o -> o[2]));
            out.println(compute());
        }
        out.flush();
        out.close();
        bufferedReader.close();
    }

    private static int compute() {
        int team = 1;
        for (int i = 2; i <= n; i++) {
            if (arr[i][2] != arr[i - 1][2]) {
                team++;
            }
        }
        //dp[i][j]表示前i组物品，体积不超过j的最大价值
        int[][] dp = new int[team + 1][m + 1];
        for (int start = 1, end = 2, i = 1; start <= n; start++) {
            while (end <= n && arr[end][2] == arr[start][2]) {
                end++;
            }
            for (int j = 0; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = start; k < end; k++) {
                    if (j >= arr[k][0]) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - arr[k][0]] + arr[k][1]);
                    }
                }
            }
            start = end++;
        }

        return dp[team][m];
    }

    public static boolean isMatch(String str, String pat) {
        char[] s = str.toCharArray();
        char[] p = pat.toCharArray();
        return f1(s, p, 0, 0);
    }

    private static boolean f1(char[] s, char[] p, int i, int j) {
        if (i == s.length) {
            if (j == p.length) {
                return true;
            } else {
                return j + 1 < p.length && p[j + 1] == '*' && f1(s, p, i, j + 2);
            }
        } else if (j == p.length) {
            return false;
        } else {
            return false;

        }

    }

}
