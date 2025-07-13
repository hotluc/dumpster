package algorithm.practice.dumpster;

public class OrangesRotting {
    public static int MAXN = 100;
    public static int MAXM = 100;
    public static int[][] queue = new int[MAXN * MAXM][2];
    public static int l, r;
    public static boolean[][] visited = new boolean[MAXN][MAXM];
    public static int[] move = new int[]{-1, 0, 1, 0, -1};

    public static int orangesRotting1(int[][] grid) {
        l = r = 0;
        int n = grid.length, m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    visited[i][j] = true;
                    queue[r][0] = i;
                    queue[r++][1] = j;
                } else {
                    visited[i][j] = false;
                }
            }
        }

        int level = -1;  // 从 -1 开始，每轮 +1，最终刚好是分钟数
        while (l < r) {
            level++;
            int size = r - l;
            for (int k = 0, x, y, nx, ny; k < size; k++) {
                x = queue[l][0];
                y = queue[l++][1];
                for (int i = 0; i < 4; i++) {
                    nx = x + move[i];
                    ny = y + move[i + 1];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == 1 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue[r][0] = nx;
                        queue[r++][1] = ny;
                    }
                }
            }
        }

        // 检查是否还有未被腐烂的新鲜橘子
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    return -1;
                }
            }
        }

        return level == -1 ? 0 : level;  // 特判全为 0 或 2 的情况
    }

    public static int orangesRotting(int[][] grid) {
        int times = 0,n = grid.length,m = grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    times++;
                    dfs(grid,i,j);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return times-1;
    }
    public static void dfs(int[][] grid, int i, int j) {
        if (i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]==0) {
            return;
        }
        grid[i][j] = 2;
        visited[i][j] = true;
        dfs(grid,i-1,j);
        dfs(grid,i+1,j);
        dfs(grid,i,j-1);
        dfs(grid,i,j+1);
    }
    public static void main(String[] args) {
        int[][] grid = new int[][]{{2,1,1},{0,1,1},{1,0,1}};
        int[][] f = new int[][]{{0,2}};
        System.out.println(orangesRotting1(grid));
    }
}
