package algorithm.stack;

import java.util.Arrays;

/**
 * 单调栈
 */
public class Solution {
    //静态数组，用于记录每个字符出现的次数
    static int[] cnts = new int[26];
    //静态数组，用于标记字符是否已经在结果中出现
    static boolean[] has = new boolean[26];
    //静态数组，用作栈，存储结果字符串中的字符
    static char[] stack = new char[26];
    //栈顶指针
    static int r;
    public static String removeDuplicateLetters(String str) {
        Arrays.fill(cnts, 0);
        Arrays.fill(has, false);
        r = 0;
        char[] s = str.toCharArray();
        for (char c : s) {
            cnts[c - 'a']++;
        }
        for (char cur : s) {
            if (!has[cur - 'a']) {
                while (r > 0 && stack[r - 1] > cur && cnts[stack[r - 1] - 'a'] > 0) {
                    has[stack[--r] - 'a'] = false;
                }
                stack[r++] = cur;
                has[cur - 'a'] = true;
            }
            cnts[cur-'a']--;
        }
        return String.valueOf(stack, 0, r);
    }
    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("bcabc"));
    }
    public static int findDuplicate(int[] nums) {
        if (nums == null||nums.length<2) {
            return -1;
        }
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (slow != fast){
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }
}
