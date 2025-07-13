package algorithm.map;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * prim算法
 */
public class Prim {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
            int n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            in.nextToken();
            int m = (int) in.nval;
            for (int i = 0,u,v,w; i < m; i++) {
                in.nextToken();
                u = (int) in.nval;
                in.nextToken();
                v = (int) in.nval;
                in.nextToken();
                w = (int) in.nval;
                graph.get(u).add(new int[]{v,w});
                graph.get(v).add(new int[]{u,w});
            }
            PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
            heap.addAll(graph.get(1));
            boolean[] set = new boolean[n+1];
            int nodeCnt = 1;
            set[1] = true;
            int ans = 0;
            while (!heap.isEmpty()){
                int[] edge = heap.poll();
                int next = edge[0];
                int cost = edge[1];
                if (!set[next]){
                    nodeCnt++;
                    set[next] = true;
                    ans += cost;
                    heap.addAll(graph.get(next));
                }
            }
            out.println( nodeCnt== n - 1? ans : "orz");
        }
        out.flush();
        out.close();
        br.close();
    }
}
