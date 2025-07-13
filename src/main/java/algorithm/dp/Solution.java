package algorithm.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * 递归实现
     *
     * @param grid
     * @return
     */
    public static int pathMinSum1(int[][] grid) {
        return dfs(grid, grid.length - 1, grid[0].length - 1);
    }

    private static int dfs(int[][] grid, int i, int j) {
        if (i == 0 && j == 0) {
            return grid[0][0];
        }
        int up = Integer.MAX_VALUE;
        int left = Integer.MAX_VALUE;
        if (i > 0) {
            up = dfs(grid, i - 1, j);
        }
        if (j > 0) {
            left = dfs(grid, i, j - 1);
        }
        return grid[i][j] + Math.min(up, left);
    }

    public static int pathMinSumDp(int[][] grid) {
        int n = grid.length;
        ;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[n - 1][m - 1];
    }

    public static int pathMinSumDp1(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] dp = new int[m];
        dp[0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i] = dp[i - 1] + grid[0][i];
        }
        for (int i = 1; i < n; i++) {
            dp[0]+=grid[i][0];
            for (int j = 1; j < m; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1];
    }
    public static boolean exist(char[][] broad,String word){
        char[] w = word.toCharArray();
        for (int i = 0; i < broad.length; i++) {
            for (int j = 0; j < broad[0].length; j++) {
                if (f(broad,i,j,w,0)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean f(char[][] broad, int i, int j, char[] w, int k) {
        if (k== w.length){
            return true;
        }
        if (i<0||i== broad.length||j<0||j==broad[0].length||broad[i][j]==w[k]){
            return false;
        }
        char temp = broad[i][j];
        broad[i][j]=0;
        boolean flag = f(broad,i,j-1,w,k+1)||f(broad,i,j+1,w,k+1)||f(broad,i+1,j,w,k+1)|| f(broad,i+1,j,w,k+1);
        broad[i][j]=temp;
        return flag;
    }
    public static int longest(String str1,String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int n = s1.length;
        int m = s2.length;
        return f1(s1,s2,n-1,m-1);
    }

    private static int f1(char[] s1, char[] s2, int i1, int i2) {
        if (i1<0||i2<0){
            return 0;
        }
        int p1 = f1(s1,s2,i1-1,i2-1);
        int p2 = f1(s1,s2,i1-1,i2);
        int p3 = f1(s1,s2,i1,i2-2);
        int p4 = s1[i1]==s2[i2]?(p1+1):0;
        return Math.max(Math.max(p1,p2),Math.max(p3,p4));
    }
    public static List<List<String>> partition(String s) {
        return dfs(s.toCharArray(),0,s.length()-1);
    }

    private static List<List<String>> dfs(char[] charArray, int l, int r) {
        List<List<String>> ans = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        if (l==r){
            cur.add(String.valueOf(charArray[l]));
            ans.add(cur);
            return ans;
        }
        if(l+1==r){
            if (charArray[l]==charArray[r]){
                cur.add(String.valueOf(charArray[l]));
                cur.add(String.valueOf(charArray[r]));
                cur.add(String.valueOf(charArray[l]+charArray[r]));
                ans.add(cur);
            }
            else {
                cur.add(String.valueOf(charArray[l]));
                cur.add(String.valueOf(charArray[r]));
                ans.add(cur);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(longest("abcde","ace"));
        int[] nums = {1,2,3,1,2,3};int k = 2;
        System.out.println(containsNearbyDuplicate(nums,k));
    }
    public static int minArraySum(int[] nums, int k, int op1, int op2) {
        return dfs(nums,k,0,nums.length-1);
    }

    private static int dfs(int[] nums, int k, int left,int right) {
        if (left==right){
            if (nums[left]>=k){
                return Math.min((nums[left]+1)/2,nums[left]-k);
            }
            return (nums[left]+1)/2;
        }
        //
        return 0;
       // return dfs(nums,k,left+1,right)+dfs(nums,k)
    }
    public String breakPalindrome(String palindrome) {
        return "";
    }
    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
    //空间优化
    public static int minPathSumZip(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[m];
        dp[0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i-1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < n; j++) {
                dp[j] = Math.min(dp[j-1],dp[j]) + grid[i][j];
            }
        }
        return dp[n-1];
    }
    public static int longestCommonSubsequence(String text1, String text2) {
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        int n = s1.length;
        int m = s2.length;
        int[][] dp = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1[i-1]==s2[j-1]){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[n][m];
    }
    public static int longestCommonSubsequenceZip(String text1, String text2) {
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        int n = s1.length;
        int m = s2.length;
        int[] dp = new int[m+1];
        for (int i = 1; i <= n; i++) {
            int pre = 0;
            for (int j = 1; j <= m; j++) {
                int temp = dp[j];
                if (s1[i-1]==s2[j-1]){
                    dp[j] = pre+1;
                }else {
                    dp[j] = Math.max(dp[j],dp[j-1]);
                }
                pre = temp;
            }
        }
        return dp[m];
    }
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == nums[i+1] && Math.abs(nums[i]-nums[i+1])<=k){
                return true;
            }
        }
        return false;
    }
}