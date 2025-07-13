package algorithm.recursion;

import java.util.*;

public class Solution {
    private static List<String> ans = new ArrayList<>();
    private static List<Integer> path = new ArrayList<>();

    public static List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        dfs(s, 0, ans);
        return ans;
    }

    private static void dfs(String s, int i, List<List<String>> path) {
        if (i == s.length()) {
            List<String> cur = new ArrayList<>();
            path.add(cur);
            return;
        } else {
            for (int j = i; i < s.length(); i++) {
                if (isPalindrome(i, j)) {
                    //path.add(s.substring(i, j + 1));
                    //dfs(j + 1);
                    path.remove(path.size() - 1); // 恢复现场
                }
            }
        }
    }

    private static boolean isPalindrome(int left, int right) {
        while (left < right) {
            /*if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }*/
        }
        return true;
    }

    private static void dfs(String str, int i) {
        if (i == str.length()) {
            ans.add(String.valueOf(i));
        }
        for (int j = 0; j < str.length(); j++) {
            //int nums = Integer.parseInt()
        }
    }

    public static List<String> restoreIpAddresses1(String s) {
        List<String> ans = new ArrayList<>();

        return ans;
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, 0);
    }

    private static boolean dfs(String str, List<String> wordDict, int i) {
        return false;
    }

    public static int maximalSquare(char[][] matrix) {
        int i = dfs(matrix, matrix.length, matrix[0].length);
        return i * i;
    }

    private static int dfs(char[][] matrix, int n, int m) {
        if (n < 0 || n == matrix.length || m < 0 || m == matrix[0].length || matrix[n][m] == 0) {
            return 0;
        }
        char temp = matrix[n][m];
        matrix[n][m] = 0;
        int i = Math.min(dfs(matrix, n - 1, m) + dfs(matrix, n + 1, m), dfs(matrix, n, m + 1) + dfs(matrix, n, m - 1));
        matrix[n][m] = temp;
        return i;
    }

    public static int longestSubsequence(int[] arr, int difference) {
        HashMap<Integer, Integer> dp = new HashMap<>();
        int max = 0;
        for (int i : arr) {
            dp.put(i, dp.getOrDefault(i - difference, 0) + 1);
            max = Math.max(dp.get(i), max);
        }
        return max;
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int m = 0, n = matrix[0].length - 1;
        while (m < matrix.length && n >= 0) {
            if (matrix[m][n] == target) {
                return true;
            } else if (matrix[m][n] > target) {
                n--;
            } else {
                m++;
            }
        }
        return false;
    }

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.isEmpty()) {
            return ans;
        }
        Map<Character, String> phoneMap = new HashMap<>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backTrack(ans, phoneMap, digits, 0, new StringBuilder());
        return ans;
    }

    private void backTrack(List<String> ans, Map<Character, String> phoneMap, String digits, int i, StringBuilder stringBuilder) {
        if (i == digits.length()) {
            ans.add(stringBuilder.toString());
        }
        String s = phoneMap.get(digits.charAt(i));
        for (int j = 0; j < s.length(); j++) {
            stringBuilder.append(s.charAt(j));
            backTrack(ans, phoneMap, digits, i + 1, stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }

    static List<String> ans1 = new ArrayList<>();
    static List<String> path1 = new ArrayList<>();

    public static List<String> restoreIpAddresses(String s) {
        dfs(s, 0, 4);
        return ans1;
    }

    private static void dfs(String s, int i, int k) {
        if (k == 0) {
            if (i == s.length()) {
                ans1.add(String.join(".", path1));
            }
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }

    private static void backtrack(int[] candidates, int index, int target, List<Integer> list, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(list));
        } else {
            for (int i = index; i < candidates.length; i++) {
                if (target < candidates[i]) continue;//剪枝
                list.add(candidates[i]);
                backtrack(candidates, i, target - candidates[i], list, res);
                list.removeLast(); //回溯
            }
        }
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack1(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }

    private static void backtrack1(int[] candidates, int index, int target, List<Integer> list, List<List<Integer>> res) {
        if (index == candidates.length) {
            if (target == 0) {
                res.add(new ArrayList<>(list));
            }
        } else {
            for (int i = 0; i < candidates.length; ++i) {
                list.add(candidates[i]);
                if (target < candidates[i]) continue;//剪枝
                backtrack1(candidates, i, target - candidates[i], list, res);
                list.removeLast(); //回溯
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = new int[]{1,2,3};
        int[] nums = {1,5,11,5};
        System.out.println(canPartition(nums));

    }
    public static boolean canPartition1(int[] nums) {
        int sum = Arrays.stream(nums).sum(), n = nums.length, target = sum / 2;
        if (sum % 2 != 0) {
            return false;
        }
        int[][] dp = new int[n + 1][target + 1];
        for (int i = 1; i <= n; i++) {
            int current = nums[i - 1]; // 修正索引为i-1
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= current) { // 使用current代替nums[i]
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - current] + current);
                }
            }
        }
        return dp[n][target] == target;
    }
    public static boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum(),n=nums.length,target=sum/2;
        if(sum%2 != 0) {
            return false;
        }
        int[][] dp = new int[n+1][target+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i-1][j];
                System.out.println(i);
                if (j >= nums[i-1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-nums[i-1]] + nums[i-1]);
                }
            }
        }
        return dp[n][target] == target;
    }

}
