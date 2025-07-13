package algorithm.tanxin;

import java.util.*;

public class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length==0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, Comparator.comparingInt(a->a[0]));
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            int l = interval[0], r = interval[1];
            if (merged.isEmpty() || merged.getLast()[1] < l) {
                merged.add(new int[]{l, r});
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], r);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
    public static String reverseWords(String str) {
        char[] arr = str.trim().toCharArray();

        int left = 0, right = arr.length - 1;
        while (left++ <= right--) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
        return String.valueOf(arr);
    }

    public static void main(String[] args) {

        String s =  "Let's take LeetCode contest".trim();
        List<String> words = Arrays.asList(s.split("\\s+"));
        Solution solution = new Solution();
        Collections.reverse(words);
        System.out.println(String.join(" ", words))                 ;
    }
}