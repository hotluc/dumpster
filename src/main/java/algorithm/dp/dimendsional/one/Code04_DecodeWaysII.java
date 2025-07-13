package algorithm.dp.dimendsional.one;

import algorithm.bitmap.BitMap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// 解码方法 II
// 一条包含字母 A-Z 的消息通过以下的方式进行了 编码 ：
// 'A' -> "1"
// 'B' -> "2"
// ...
// 'Z' -> "26"
// 要 解码 一条已编码的消息，所有的数字都必须分组
// 然后按原来的编码方案反向映射回字母（可能存在多种方式）
// 例如，"11106" 可以映射为："AAJF"、"KJF"
// 注意，像 (1 11 06) 这样的分组是无效的，"06"不可以映射为'F'
// 除了上面描述的数字字母映射方案，编码消息中可能包含 '*' 字符
// 可以表示从 '1' 到 '9' 的任一数字（不包括 '0'）
// 例如，"1*" 可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19"
// 对 "1*" 进行解码，相当于解码该字符串可以表示的任何编码消息
// 给你一个字符串 s ，由数字和 '*' 字符组成，返回 解码 该字符串的方法 数目
// 由于答案数目可能非常大，答案对 1000000007 取模
// 测试链接 : https://leetcode.cn/problems/decode-ways-ii/
public class Code04_DecodeWaysII {
    // 暴力尝试
    public static int numDecodings1(String s) {
        return f1(s.toCharArray(), 0);
    }
    // s : 数字字符串
    // s[i....]有多少种有效的转化方案
    public static int f1(char[] s, int i) {
        if (i == s.length) {
            return 1;
        }
        if (s[i] == '0') {
            return 0;
        }
        return 0;
    }
    public static int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.add(num);
        }
        while (pq.size() > k) {
            pq.remove();
        }
        return pq.peek();
    }
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int[] map = new int[(nums.length+31)/32];
        for (int num : nums) {
            if ((map[num / 32] >> (num % 32) & 1) == 0){
                map[num / 32] |= 1 << (num % 32);
            }
            else {
                ans.add(num);
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        BitMap bitMap = new BitMap(10000);
        int[] nums = new int[] {4,3,2,7,8,2,3,1};
        List<Integer> ans = new ArrayList<>();
        for (int num : nums) {
            if (!bitMap.contains(num)) {
                bitMap.add(num);
            }
            else {
                ans.add(num);
            }
        }
        long i = Long.MAX_VALUE;
        System.out.println(Long.toBinaryString(i).length());
        System.out.println(findDuplicates(nums));
    }
}
