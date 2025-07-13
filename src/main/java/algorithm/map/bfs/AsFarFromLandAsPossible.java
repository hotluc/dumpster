package algorithm.map.bfs;

public class AsFarFromLandAsPossible {
    public static int MAXN = 101;
    public static int MAXM = 101;
    public static int[][] queue = new int[MAXM * MAXN][2];
    public static int left, right;
    public static boolean[][] visited = new boolean[MAXM][MAXN];
    public static int[] move = new int[]{-1, 0, 1, 0, -1};

    public static int maxDistance(int[][] grid) {
        left = right = 0;
        int n = grid.length;
        int m = grid[0].length;
        int sea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    visited[i][j] = true;
                    queue[right][0] = i;
                    queue[right++][1] = j;

                } else {
                    visited[i][j] = false;
                    sea++;
                }
            }
        }
        if (sea == 0 || sea == n * m) {
            return -1;
        }
        int level = 0;
        while (left < right) {
            level++;
            int size = right - left;
            for (int i = 0, x, y, nx, ny; i < size; i++) {
                x = queue[left][0];
                y = queue[left++][1];
                for (int j = 0; j < 4; j++) {
                    nx = x + move[j];
                    ny = y + move[j + 1];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue[right][0] = nx;
                        queue[right++][1] = ny;
                    }
                }
            }
        }
        return level - 1;
    }
}
