package algorithm.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalDP {

    /**
     * 递归实现
     *
     * @param str
     * @return
     */
    public static int minInsertion(String str) {
        return dfs(str.toCharArray(), 0, str.length() - 1);
    }

    private static int dfs(char[] charArray, int l, int r) {
        if (l == r) {
            return 0;
        }
        if (l + 1 == r) {
            return charArray[l] == charArray[r] ? 0 : 1;
        }
        if (charArray[l] == charArray[r]) {
            return dfs(charArray, l + 1, r - 1);
        } else {
            return Math.min(dfs(charArray, l + 1, r), dfs(charArray, l, r - 1)) + 1;
        }
    }

    public static int minInsertion1(String str) {
        char[] charArray = str.toCharArray();
        int len = str.length();
        int[][] dp = new int[len][len];
        for (int i = 0; i < len - 1; i++) {
            dp[i][i + 1] = charArray[i] == charArray[i + 1] ? 0 : 1;
        }
        for (int l = len - 3; l >= 0; l--) {
            for (int r = l + 2; r < len; r++) {
                if (charArray[l] == charArray[r]) {
                    dp[l][r] = dp[l + 1][r - 1];
                } else {
                    dp[l][r] = Math.min(dp[l + 1][r], dp[l][r - 1]) + 1;
                }
            }
        }
        return dp[0][len - 1];
    }

    public static int longest(String str) {
        char[] chars = str.toCharArray();
        int n = str.length();
        int[] dp = new int[n];
        for (int l = n - 1, leftdown = 0, backUp; l >= 0; l--) {
            dp[l] = 1;
            if (l + 1 < n) {
                leftdown = dp[l + 1];
                dp[l + 1] = chars[l] == chars[l + 1] ? 2 : 1;
            }
            for (int r = l + 2; r < n; r++) {
                backUp = dp[r];
                if (chars[l] == chars[r]) {
                    dp[r] = 2 + leftdown;
                } else {
                    dp[r] = Math.max(dp[r], dp[r - 1]);
                }
                leftdown = backUp;
            }
        }
        return dp[n - 1];
    }

    public static boolean predictTheWinner(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int first = dfs(nums, 0, nums.length - 1);
        int second = sum - first;
        return first >= second;
    }

    private static int dfs(int[] nums, int l, int r) {
        if (l == r) {
            return nums[l];
        }
        if (l + 1 == r) {
            return Math.max(nums[l], nums[r]);
        }
        return Math.max(nums[l] + Math.min(dfs(nums, l + 2, r), dfs(nums, l + 1, r - 1)), nums[r] + dfs(nums, l + 1, r - 1));
    }

    public static boolean predictTheWinnerDP(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n - 1; i++) {
            dp[i][i] = nums[i];
            dp[i][i + 1] = Math.max(nums[i], nums[i + 1]);
        }
        for (int l = n - 3; l >= 0; l--) {
            for (int r = l + 2; r < n; r++) {
                dp[l][r] = Math.max(nums[l] + Math.min(dp[l + 2][r], dp[l + 1][r - 1]), nums[r] + Math.min(dp[l + 1][r + 1], dp[l][r - 2]));
            }
        }
        int first = dp[0][n - 1];
        int second = sum - first;
        return first >= second;
    }

    public int minCost(int n, int[] cuts) {
        return 0;
    }
    public int strangePrinter(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[][] dp = new int[n][n];
        dp[n-1][n-1] = 1;
        for (int i = 0; i < n-1; i++) {
            dp[i][i] = 1;
            dp[i][i+1] = s[i]==s[i+1]?1:2;
        }
        for (int l = n-3,ans; l >=0 ; l--) {
            for (int r = l+2; r < n; r++) {
                if (s[l]==s[r]){
                    dp[l][r] = dp[l+1][r];
                }else {
                    ans = Integer.MAX_VALUE;
                    for (int m = l; m < r; m++) {
                        ans = Math.min(ans,dp[l][m]+dp[m +1][r]);
                    }
                    dp[l][r] = ans;
                }
            }
        }
        return dp[0][n-1];
    }
    //回文
    protected boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
    public static int minCut(String str) {
        char[] s = str.toCharArray();
        int n = str.length()-1;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n-1; i++) {
            dp[i][i+1] = s[i]==s[i+1]?0:1;
        }
        for (int l = n-3,ans; l >=0 ; l--) {
            for (int r = 0; r < n; r++) {
                if (s[l]==s[r]){
                    dp[l][r] = dp[l-1][r-1];
                }
                else {
                    ans = Integer.MAX_VALUE;
                    for (int m = l; m < r; m++) {
                        ans = Math.min(ans,dp[l][m]+dp[m +1][r]);
                        dp[l][r] = ans;
                    }
                }
            }
        }
        return dp[0][n-1];
    }

    public static void main(String[] args) {
        System.out.println(minCut("ab"));
    }

}