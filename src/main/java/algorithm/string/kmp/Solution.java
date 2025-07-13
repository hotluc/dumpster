package algorithm.string.kmp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static int kmp(char[] s1, char[] s2) {
        int n = s1.length, m = s2.length, x = 0, y = 0;
        int[] next = NextArray(s2, m);
        while (x < m && y < n) {
            if (s1[x] == s2[y]) {
                x++;
                y++;
            } else if (y == 0) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == m ? x - y : -1;
    }

    // 得到next数组
    public static int[] NextArray(char[] s, int m) {
        if (m == 1) {
            return new int[]{-1};
        }
        int[] next = new int[m];
        next[0] = -1;
        next[1] = 0;
        // i表示当前要求next值的位置
        // cn表示当前要和前一个字符比对的下标
        int i = 2, cn = 0;
        while (i < m) {
            if (s[i - 1] == s[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static int lengthOfLongestSubstring(String s) {
        Set<Character> occ = new HashSet<>();
        int len = s.length();
        int right = -1, ans = 0;
        for (int i = 0; i < len; i++) {
            if (i != 0) {
                occ.remove(s.charAt(i - 1));
            }
            while (right + 1 < len && !occ.contains(s.charAt(right + 1))) {
                occ.add(s.charAt(right + 1));
                right++;
            }
            System.out.println(s.substring(i, right));
            ans = Math.max(ans, right - i + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "abcabcabc";
        System.out.println(Arrays.toString(NextArray(s.toCharArray(), s.length())));
    }
}
