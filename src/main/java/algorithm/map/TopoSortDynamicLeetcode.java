package algorithm.map;

import java.util.ArrayList;
import java.util.List;

// 拓扑排序模版（Leetcode）
// 邻接表建图（动态方式）
public class TopoSortDynamicLeetcode {
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        // 入度表
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            edges.get(edge[0]).add(edge[1]);
            indegree[edge[0]]++;
        }
        int[] queue = new int[numCourses];
        int l = 0, r = 0;
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue[r++] = i;
            }
        }
        int cnt = 0;
        while (l < r) {
            int cur = queue[l++];
            cnt++;
            for (int next : edges.get(cur)) {
                if (--indegree[next] == 0) {
                    queue[r++] = next;
                }
            }
        }
        return cnt == numCourses ? queue : new int[0];
    }
}
