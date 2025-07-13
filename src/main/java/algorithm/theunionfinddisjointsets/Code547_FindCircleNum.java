package algorithm.theunionfinddisjointsets;

import java.util.Arrays;

//有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
//省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
//给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
//返回矩阵中 省份 的数量。
public class Code547_FindCircleNum {
    public static int MAXN = 100;
    public static int[] father = new int[MAXN];
    public static int set = 0;

    public static void build(int n) {
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
        set = n;
    }

    public static int find(int i) {
        if (i != father[i]) {
            father[i] = find(father[i]);
        }
        return father[i];
    }

    public static void union(int x, int y) {
        if (find(x) != find(y)) {
            father[find(x)] = find(y);
            set--;
        }
    }

    public static int findCircleNum(int[][] isConnected) {
        build(isConnected.length);
        int n = isConnected.length;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                System.out.print(i);
                System.out.println(j);
                if (isConnected[i][j] == 1) {
                    union(i, j);
                }
            }
        }
       return set;
    }

    public static void main(String[] args) {
        int[][] isConnected = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int[][] isConnected2 = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        int[][] isConnected3 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        System.out.println(findCircleNum(isConnected3));
    }
}
