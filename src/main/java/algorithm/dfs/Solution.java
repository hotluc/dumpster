package algorithm.dfs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static int orangesRotting(int[][] grid) {
        int time = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    time++;
                    dfs(grid, m, n, i, j);
                }
            }
        }
        return time;
    }

    public static void dfs(int[][] broad, int m, int n, int i, int j) {
        if (m == -1 || m == broad.length || n == -1 || n == broad[0].length || broad[i][j] == 0) {
            return;
        }
        broad[i][j] = 2;
        dfs(broad, m, n, i + 1, j);
        dfs(broad, m, n, i - 1, j);
        dfs(broad, m, n, i, j - 1);
        dfs(broad, m, n, i, j + 1);
    }

    public static void setZeroes(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                dfs1(matrix, matrix.length, matrix[0].length, i, j);
            }
        }
        System.out.println(Arrays.deepToString(matrix));
    }

    public static void dfs1(int[][] broad, int m, int n, int i, int j) {
        if (m == 0 || m == broad.length || n == 0 || n == broad[0].length || broad[m][n] != 0) {
            return;
        }
        broad[i][j] = 0;
        dfs(broad, m, n, i + 1, j);
        dfs(broad, m, n, i - 1, j);
        dfs(broad, m, n, i, j - 1);
        dfs(broad, m, n, i, j + 1);

    }
    static int ans = -1;
    public static int beautifulSubsets(int[] nums, int k) {

        Map<Integer,Integer> map = new HashMap<>();
        beautifulSubsets(0,nums,k,map);
        return ans;
    }

    private static void beautifulSubsets(int i,int[] nums, int k, Map<Integer, Integer> cnt) {
        if (i == nums.length) {
            ans++;
            return;
        }
        beautifulSubsets(i+1,nums,k,cnt);
        int x = nums[i];
        if (cnt.getOrDefault(x-k,0) == 0 && cnt.getOrDefault(x+k,0) == 0){
            cnt.merge(x,1,Integer::sum);
            beautifulSubsets(i+1,nums,k,cnt);
            cnt.merge(x,-1,Integer::sum);
        }
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        setZeroes(grid);
    }

}
