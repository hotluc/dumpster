package algorithm.map;

import java.util.Arrays;

//Bellman-Ford算法应用
public class Bellman_Ford1 {
    public static int findCheapestPrice(int n,int[][] flights, int start, int target, int k) {
        int[] current = new int[n];
        Arrays.fill(current, Integer.MAX_VALUE);
        current[start] = 0;
        for (int i = 0; i <= k; i++) {
            int[] next = Arrays.copyOf(current, n);
            for (int[] edge : flights) {
                if (current[edge[0]] != Integer.MAX_VALUE) {
                    next[edge[1]] = Math.min(next[edge[0]], current[edge[0]]+edge[2]);
                }
            }
            current = next;
        }
        return current[target]  == Integer.MAX_VALUE ? -1 : current[target];
    }
}
