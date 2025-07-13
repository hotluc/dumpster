package algorithm.dp.knapsack;

import java.io.*;

public class DependKnapSack {
    public static int MAXN = 33001;
    public static int MAXM = 61;
    public static int[] cost = new int[MAXM];
    public static int[] weight = new int[MAXM];
    public static boolean[] king = new boolean[MAXN];
    public static int[] fans = new int[MAXN];
    public static int[][] follows = new int[MAXN][2];
    public static int[] dp = new int[MAXM];
    public static int n, m;
    public static void clean(){
        for (int i = 1; i <= m; i++) {
            fans[i]=0;
        }
    }

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            clean();
            for (int i = 1,v,p,q; i <=m; i++) {
                in.nextToken();
                v = (int) in.nval;
                in.nextToken();
                p = (int) in.nval;
                in.nextToken();
                q = (int) in.nval;
                cost[i]=v;
                weight[i]=v*p;
                king[i]=q==0;
                if (q!=0){
                    follows[q][fans[q]++]=i;
                }
            }
            out.println(compute());
        }
        out.flush();
        out.close();
    }
    private static int compute(){

        return 0;
    }

}
