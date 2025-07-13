package algorithm.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Solution {
    public static boolean checkXMatrix(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == j || i + j + 1 == grid.length) {
                    if (grid[i][j] == 0) {
                        return false;
                    }
                } else if (grid[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
    public static String truncateSentence(String s, int k) {
        int n = s.length(),end = 0,count = 0;
        for (int i = 1; i <= n; i++) {
            if (i == n || s.charAt(i) == ' ') {
                count++;
                if (count == k){
                    System.out.println(s.charAt(i));
                    end = i;
                    break;
                }
            }
        }
        return s.substring(0,end);
    }
    public static boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 1; i< matrix.length; i++) {
            for (int j = 1; j< matrix[i].length; j++) {
                    if (matrix[i][j] != matrix[i-1][j-1]){
                        return false;
                }
            }
        }
        return true;
    }
    public static double trimMean(int[] arr) {
        Arrays.sort(arr);
        arr[0] = 0;
        arr[arr.length - 1] = 0;
        return (double) Arrays.stream(arr).sum() /Arrays.stream(arr).count();

    }
    public static int[] findMissingAndRepeatedValues(int[][] grid) {
        HashSet<Integer> set = new HashSet<>();
        int[] res = new int[2];
        int sum = 0,n = grid.length;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!set.add(grid[i][j])){
                    res[0] = grid[i][j];
                }
                sum += grid[i][j];
            }
        }
        res[1] = n * n * (n * n + 1) / 2 - sum + res[0];
        return res;
    }
    public static List<Integer> intersection(int[][] nums) {
        HashSet<Integer> set = new HashSet<>(1000);
        HashSet<Integer> res = new HashSet<>(1000);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                if (!set.add(nums[i][j])){
                    res.add(nums[i][j]);
                }
            }
        }
        return res.stream().toList();
    }
    public static void main(String[] args) {
        String s = "Hello how are you Contestant";
        int[][] grid = new int[][]{{3,1,2,4,5},{1,2,3,4},{3,4,5,6}};
        int[][]matrix = new int[][]{{1,2,3},{4,5,6}};
        int k = 4;
        System.out.println(intersection(grid));

    }
}
