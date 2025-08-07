package algorithm.practice.dumpster;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class FindThePrefixCommonArray {
    public static int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] result = new int[n];
        long p=0,q=0;
        for (int i = 0; i < n; i++) {
            p|= 1L <<A[i];
            q|= 1L <<B[i];
            result[i] = Long.bitCount(p&q);
        }
        return result;
    }
    public static boolean isPowerTwo(int val){
        return (val & -val) == val;
    }
    public static String makeFancyString(String str) {
        StringBuilder sb = new StringBuilder(str);
        char[] s = str.toCharArray();
        for (int i = s.length-1; i-2 >=0 ; i--) {
            if (s[i]==s[i-1]&&s[i-1]==s[i-2]){
                sb.deleteCharAt(i);
            }
        }
        return sb.toString();
    }
    public static String makeFancyString1(String str) {
        StringBuilder sb = new StringBuilder();
        char[] s = str.toCharArray();
        for (char c : s) {
            int n = sb.length();
            if (n>=2&&sb.charAt(n-1)==c&&sb.charAt(n-2)==c){
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }
    public static int[] resultsArray(int[][] queries, int k) {
        int n = queries.length;
        int[] ans = new int[n];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        for (int i = 0; i < n; i++) {
            pq.offer(Math.abs(queries[i][0])+Math.abs(queries[i][1]));
            if (pq.size()>k) {
                pq.poll();
            }
            ans[i] = pq.size() == k ? pq.peek() : -1;
        }
        return ans;
    }
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey(),count=entry.getValue();
            if (pq.size()==k) {
                if (pq.peek()[1]<count) {
                    pq.poll();
                    pq.offer(new int[]{num,count});
                }
            }
            else {
                pq.offer(new int[]{num,count});
            }
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = pq.poll()[0];
        }
        return ans;
    }
    public static int[] productExceptSelf(int[] nums) {
        int len = nums.length,r=1;
        int[] ans = new int[len];
        ans[0] = 1;
        for (int i = 1; i < len; i++) {
            ans[i] = nums[i-1] * ans[i - 1];
        }
        for (int i = len-1; i >=0 ; i--) {
            ans[i] = ans[i] * r;
            r *= nums[i];
        }
        return ans;
    }
    public int firstMissingPositive(int[] nums) {
        int l=0,r = nums.length;
        while (l<r) {
            if (nums[l]==l+1) {
                l++;
            }
            else if (nums[l]<=l||nums[l]>r||nums[nums[l]-1]==nums[l]) {
                swap(nums,l,--r);
            }
            else {
                swap(nums,l,nums[l]-1);
            }
        }
        return l+1;
    }
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

        public static void main(String[] args) {
        System.out.println(makeFancyString("aaabaaaa"));
    }
}
