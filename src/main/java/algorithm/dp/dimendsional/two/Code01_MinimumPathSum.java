package algorithm.dp.dimendsional.two;
// 最小路径和
// 给定一个包含非负整数的 m x n 网格 grid
// 请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
// 说明：每次只能向下或者向右移动一步。
// 测试链接 : https://leetcode.cn/problems/minimum-path-sum/
public class Code01_MinimumPathSum {
    // 暴力递归
    public static int minPathSum1(int[][] grid) {
        return f1(grid, grid.length - 1, grid[0].length - 1);
    }
    // 从(0,0)到(i,j)最小路径和
    // 一定每次只能向右或者向下
    public static int f1(int[][] grid, int i, int j) {
        if (i == 0 && j == 0) {
            return grid[0][0];
        }
        int up = Integer.MAX_VALUE,left = Integer.MAX_VALUE;
        if (i > 0) {
            up = f1(grid, i - 1, j);
        }
        if (j > 0) {
            left = f1(grid, i, j - 1);
        }
        return grid[i][j]+Math.min(up, left);
    }
    // 记忆化搜索
    public static int minPathSum2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }
        return f2(grid, grid.length - 1, grid[0].length - 1, dp);
    }
    public static int f2(int[][] grid, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int ans = 0;
        if (i == 0 && j == 0) {
            ans = grid[0][0];
        }
        int up = Integer.MAX_VALUE,left = Integer.MAX_VALUE;
        if (i > 0) {
            up = f1(grid, i - 1, j);
        }
        if (j > 0) {
            left = f1(grid, i, j - 1);
        }
        ans =  grid[i][j]+Math.min(up, left);
        dp[i][j] = ans;
        return ans;
    }
}
