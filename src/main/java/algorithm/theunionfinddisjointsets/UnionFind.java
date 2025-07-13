package algorithm.theunionfinddisjointsets;

/**
 * 并查集
 */
public class UnionFind {
    public static int MAXN = 10001;
    public static int[] father;
    public static int n;
    public void build() {
        father = new int[MAXN];
        for (int i = 0; i <= n; i++) {
            father[i] = i;
        }
    }
    public static int find(int i) {
        if (i != father[i]){
            father[i] = find(father[i]);
        }
        return father[i];
    }
    public static boolean isSameSet(int x, int y) {
        return find(x) == find(y);
    }
    public static void union(int x, int y) {
        father[find(x)] = find(y);
    }
}
