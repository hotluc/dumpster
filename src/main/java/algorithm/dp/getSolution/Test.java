package algorithm.dp.getSolution;

import java.util.Arrays;
import java.util.List;

/**
 *  从动态规划得到具体方案
 */
public class Test {

    public static int[][] longestCommonSubsequence(String text1, String text2) {
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        int n = s1.length, m = s2.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp;
    }
    public static int[] getSolution(String text1, String text2) {
        char[] s1 = text1.toCharArray(),s2 = text2.toCharArray();
        int[][] dp = longestCommonSubsequence(text1, text2);
        int n1 = dp.length, m1 = dp[0].length;
        int n = s1.length, m = s2.length;
        System.out.println(n1+"dp.length");
        System.out.println(m1+"dp[0].length");
        System.out.println(n+"字符串1n");
        System.out.println(m+"字符串2m");
        int res = dp[n][m];
        System.out.println(res);
        int[] ans = new int[res];
        if (res>0){
            for (int len=res,i=n,j=m; len>0;) {
                if (s1[i-1]==s2[j-1]){
                    ans[--len] = s1[i-1];
                    i--;
                    j--;
                }
                else {
                    if (dp[i-1][j] >= dp[i][j-1]){
                        i--;
                    }
                    else {
                        j--;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        longestCommonSubsequence("A123B4CD5E", "Q12RST345XY");
        getSolution("A123B4CD5E", "Q12RST345XY");
    }
}
