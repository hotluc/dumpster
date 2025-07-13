package algorithm.string.hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 独特子串的数量
// 给你一个由数字组成的字符串s
// 返回s中独特子字符串数量
// 其中的每一个数字出现的频率都相同
// 测试链接 : https://leetcode.cn/problems/unique-substrings-with-equal-digit-frequency/
public class Code02_NumberOfUniqueString {
    public static int equalDigitFrequency(String str) {
        long base = 499;
        char[] s = str.toCharArray();
        int n = s.length;
        Set<Long> set = new HashSet<>();
        int[] counts = new int[10];
        for (int i = 0; i < n; i++) {
            Arrays.fill(counts, 0);
            long hashCode = 0;
            int curVal = 0,maxCnt = 0,maxCntKinds=0,allKinds=0;
            for (int j = i; j < n; j++) {
                curVal = s[j] - '0';
                hashCode = hashCode * base + curVal+1;
                counts[curVal]++;
                if (counts[curVal] == 1) {
                    allKinds++;
                }
                if (counts[curVal] > maxCnt) {
                    maxCnt = counts[curVal];
                    maxCntKinds = 1;
                }
                else if (counts[curVal] == maxCnt) {
                    maxCntKinds++;
                }
                if (maxCntKinds == allKinds) {
                    set.add(hashCode);
                }
            }
        }
        return set.size();
    }
}
