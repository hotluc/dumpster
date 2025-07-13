package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public static ListNode partition(ListNode head, int x) {
        ListNode maxHead = null, maxTail = null, minHead = null, minTail = null, next;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.val < x) {
                if (minHead == null) {
                    minHead = head;
                } else {
                    minTail.next = head;
                }
                minTail = head;
            } else {
                if (maxHead == null) {
                    maxHead = head;
                } else {
                    maxTail.next = head;
                }
                maxTail = head;
            }
            head = next;
        }
        if (minHead == null) {
            return maxHead;
        }
        minTail.next = maxHead;
        return minHead;
    }

    public static int findPeakElement(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 0;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[len - 1] > nums[len - 2]) {
            return len - 1;
        }
        int l = 1, r = len - 2, mid, ans = -1;
        while (l <= r) {
            mid = (l + r) / 2;
            if (nums[mid - 1] > nums[mid]) {
                r = mid - 1;
            } else if (nums[mid + 1] > nums[mid]) {
                l = mid + 1;
            } else {
                ans = mid;
                break;
            }
        }
        return ans;
    }

    public static int lowBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static int[] searchRange(int[] nums, int target) {
        int start = lowBound(nums, target);
        if (start == nums.length || nums[start] != target) {
            return new int[]{-1, -1};
        }
        int end = lowBound(nums, target + 1) - 1;
        return new int[]{start, end};
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, 0, ans);
        return ans;
    }

    public static void dfs(int[] nums, int pos, List<List<Integer>> ans) {
        if (pos == nums.length) {
            List<Integer> cur = new ArrayList<>();
            for (int num : nums) {
                cur.add(num);
            }
            ans.add(cur);
        } else {
            for (int i = pos; i < nums.length; i++) {
                swap(nums, pos, i);
                System.out.println("交换" + nums[pos] + "和" + nums[i]);
                dfs(nums, pos + 1, ans);
                swap(nums, pos, i);
            }
        }
    }

    private static void swap(int[] nums, int pos, int i) {
        int temp = nums[pos];
        nums[pos] = nums[i];
        nums[i] = temp;
    }

    public static int[] evenOddBit(int n) {
        int[] res = new int[2];
        for (int i = 0; n > 0; n >>= 1) {
            res[i] += n & 1;
            i ^= 1;
        }
        return res;
    }

    public static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        dfs(s, 0, 4, res);
        return res;
    }

    private static void dfs(String s, int pos, int i, List<String> ans) {
        if (i == 0) {
            if (pos == s.length()) {
                List<String> cur = new ArrayList<>();
                ans.add(String.join(".", cur));
            }
            return;
        }
        for (int j = i; j < s.length() && j < i + 3; j++) {
            if (s.charAt(i) != '0' && j > i) {
                return;
            }
            int v = Integer.parseInt(s.substring(i, j + 1));
            if (v >= 0 && v <= 255) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1, i - 1, ans);
                ans.removeLast();
            }

        }
    }
    public static int[] maximumBeauty(int[][] items, int[] queries) {
        Arrays.sort(items, Comparator.comparingInt(a->a[0]));
        System.out.println(Arrays.deepToString(items));
        int[] ans = new int[queries.length];
        for (int i = 0; i < ans.length; i++) {
            System.out.println(binSearch(items,queries[i]));
            //ans[i] = items[binSearch(items,queries[i])][1];
        }
        return ans;

    }
    public static int binSearch(int[][] items,int target){
        int left = 0,right = items.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (items[mid][0] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
    public static void setZeroes(int[][] matrix) {

    }
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] nums1 = new int[]{1, 2, 3};
        permute(nums1);
        int[][] items = new int[][]{{1,2},{3,2},{2,4},{5,6},{3,5}};
        int[] queries  = new int[]{1, 2, 3, 4, 5, 6};
        int[][] matrix = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
        System.out.println(Arrays.toString(maximumBeauty(items, queries)));
    }
    public static int countPrefixes(String[] words, String s) {
        int ans = 0;
        for (String word : words) {
            if (s.startsWith(word)) {
                ans++;
            }
        }
        return ans;
    }
    public static int rangeBitwiseAnd(int left, int right) {
        while (left < right) {
            right &= (right - 1);
        }
        return right;
    }
}