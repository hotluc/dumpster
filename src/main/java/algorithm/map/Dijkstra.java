package algorithm.map;

import java.util.*;

/**
 * Dijkstra算法
 */
public class Dijkstra {

    public static int netWorkDelayTime(int[][] times, int n, int s) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : times) {
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[s] = 0;
        boolean[] visited = new boolean[n + 1];
        Queue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        heap.add(new int[]{s, 0});
        while (!heap.isEmpty()) {
            int u = heap.poll()[0];
            if (visited[u]) {
                continue;
            }
            visited[u] = true;
            for (int[] edge : graph.get(u)) {
                int v = edge[0];
                int w = edge[1];
                if (!visited[v] && distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                    heap.add(new int[]{v, distance[u] + w});
                }
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i <= n; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, distance[i]);
        }
        return ans;
    }

    // 链式前向星+反向索引堆的实现
    public static int netWorkDelayTime1(int[][] times, int n, int s) {
        build(n);
        for (int[] edge : times) {
            addEdge(edge[0], edge[1], edge[2]);
        }
        while (!isEmpty()) {
            int u = pop();
            for (int ei = head[u]; ei > 0; ei = next[ei]) {
                addOrUpdateOrIgnore(to[ei], distance[u] + weight[ei]);
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, distance[i]);
        }
        return ans;
    }

    public static int MAXN = 101;
    public static int MAXM = 6001;
    // 链式前向星
    public static int[] head = new int[MAXN];
    public static int[] next = new int[MAXN];
    public static int[] to = new int[MAXN];
    public static int[] weight = new int[MAXM];
    public static int cnt;
    // 反向索引堆
    public static int[] heap = new int[MAXN];
    public static int[] where = new int[MAXN];
    public static int heapSize;
    public static int[] distance = new int[MAXN];

    public static void build(int n) {
        cnt = 1;
        heapSize = 0;
        Arrays.fill(head, 1, n + 1, 0);
        Arrays.fill(where, 1, n + 1, -1);
        Arrays.fill(distance, 1, n + 1, Integer.MAX_VALUE);
    }

    // 链式前向星建图
    public static void addEdge(int u, int v, int w) {
        next[cnt] = head[u];
        to[cnt] = v;
        weight[cnt] = w;
        head[u] = cnt++;
    }

    public static void addOrUpdateOrIgnore(int v, int c) {
        if (where[v] == -1) {
            heap[heapSize] = v;
            where[v] = heapSize++;
            distance[v] = c;
            heapInsert(where[v]);
        } else if (where[v] == c) {
            distance[v] = Math.min(distance[v], c);
            heapInsert(where[v]);
        }
    }

    public static void heapInsert(int i) {
        while (distance[heap[i]] < distance[heap[(i - 1) / 2]]) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    public static int pop() {
        int ans = heap[0];
        swap(0, --heapSize);
        heapify(0);
        where[ans] = -2;
        return ans;
    }

    public static void heapify(int i) {
        int l = 1;
        while (l < heapSize) {
            int best = l + 1 < heapSize && distance[heap[l + 1]] < distance[heap[l]] ? l + 1 : l;
            if (best == i) {
                break;
            }
            swap(best, i);
            i = best;
            i = i * 2 + 1;
        }
    }

    public static boolean isEmpty() {
        return heapSize == 0;
    }

    public static void swap(int i, int j) {
        int temp = head[i];
        head[i] = head[j];
        head[j] = temp;
        where[heap[i]] = i;
        where[heap[j]] = j;
    }
}
