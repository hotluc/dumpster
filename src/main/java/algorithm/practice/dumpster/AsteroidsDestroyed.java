package algorithm.practice.dumpster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AsteroidsDestroyed {
    public static boolean asteroidsDestroyed(int mass, int[] asteroids) {
        int n = asteroids.length;
        if (n == 1) {
            return mass >= asteroids[0];
        }
        Arrays.sort(asteroids);
        int ans = findRight(asteroids, mass);
        mass += asteroids[ans];
        for (int i = ans + 1; i < n; i++) {
            if (asteroids[i] > mass) {
                return false;
            }
            mass += asteroids[i];
        }
        return true;
    }

    public static int findRight(int[] nums, int target) {
        int l = 0, r = nums.length - 1, ans = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] <= target) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    public static int findLucky(int[] arr) {
        int[] cnt = new int[502];
        int ans = -1;
        for (int num : arr) {
            cnt[num]++;
        }
        for (int num : arr) {
            if (cnt[num] == num) {
                ans = Math.max(ans, num);
            }
        }
        return ans;
    }

    public static List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                ans.add(i);
                System.out.println(i);
            }
        }
        return ans;
    }

    public static int maxLen(int[] candidates, int k) {
        int ans = 0;
        for (int num : candidates) {
            if ((num & 1 << k) != 0) {
                ans++;
            }
        }
        return ans;
    }

    public static int largestCombination(int[] candidates) {
        int ans = 0;
        for (int i = 0; i < 24; i++) {
            ans = Math.max(ans, maxLen(candidates, i));
        }
        return ans;
    }

    public static int power(int n) {
        int ans = 0;
        while ((1 << ans) <= (n >> 1)) {
            ans++;
        }
        return ans;
    }

    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] cnt = new int[n + 2];
        for (int[] book : bookings) {
            cnt[book[0]] += book[2];
            cnt[book[1] + 1] -= book[2];
        }
        for (int i = 1; i < cnt.length; i++) {
            cnt[i] += cnt[i - 1];
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = cnt[i + 1];
        }
        return ans;
    }

    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> ans = new ArrayList<>();
        int i = 0, j = 0;
        while (i < firstList.length && j < secondList.length) {
            int lo = Math.max(firstList[i][0], secondList[j][0]);
            int hi = Math.min(firstList[i][1], secondList[j][1]);
            if (lo <= hi) {
                ans.add(new int[]{lo, hi});
            }
            if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }

    public static int longestPalindromeSubseq1(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        return f(s, 0, n - 1);
    }

    public static int longestPalindromeSubseq(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s[i] == s[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
    public static int longestPalindromeSubseqZip(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1; // base case: 单个字符是回文
            int prev = 0; // 表示 dp[i+1][j-1]
            for (int j = i + 1; j < n; j++) {
                int temp = dp[j]; // 暂存当前 dp[j]，它是 dp[i+1][j]
                if (s[i] == s[j]) {
                    dp[j] = prev + 2;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                prev = temp; // 更新 prev 为 dp[i+1][j-1]，用于下一轮 j+1
            }
        }

        return dp[n - 1]; // 最终的结果是整个字符串的 LPS 长度
    }
    public static int f(char[] s, int i, int j) {
        if (i > j) {
            return 0;
        }
        if (i == j) {
            return 1;
        }
        int ans;
        if (s[i] == s[j]) {
            ans = f(s, i + 1, j - 1) + 2;
        } else {
            ans = Math.max(f(s, i + 1, j), f(s, i, j - 1));
        }
        return ans;
    }

    public static void aVoid(int[] firstList, int[] secondList) {
        int n = secondList[1];
        int[] cnt = new int[n + 2];
        cnt[firstList[0]]++;
        cnt[firstList[1] + 1]--;
        cnt[secondList[0]]++;
        cnt[secondList[1] + 1]--;
        for (int i = 1; i <= n; i++) {
            cnt[i] += cnt[i - 1];
        }
        System.out.println(Arrays.toString(cnt));
    }
    public static int countPrimeSetBits(int left, int right) {
        int ans = 0;
        for (int i = left; i <= right ; i++) {
            if (isPrime(Integer.bitCount(i))) {
                ans++;
            }
        }
        return ans;
    }
    public static boolean isPrime(int num){
        if (num < 2){
            return false;
        }
        for (int i = 2; i*i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static List<String> validStrings(int n) {
        return new ArrayList<>();
    }
    public static int findUnsortedSubarray(int[] nums) {
        int n = nums.length,right=-1,max=Integer.MIN_VALUE,min=Integer.MAX_VALUE,left=n;
        for (int i=0;i<n;i++){
            if (max>nums[i]){
                right=i;
            }
            max=Math.max(max,nums[i]);
        }
        for (int i=n-1;i>=0;i--){
            if (min<nums[i]){
                left=i;
            }
            min=Math.min(min,nums[i]);
        }
        return Math.max(0,right-left+1);
    }
    public static int minFlips(String target) {
        return 0;
    }
    public static String baseNeg2(int n) {
        return Integer.toBinaryString(-n);
    }
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    public static int getDecimalValue(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            head = head.next;
        }
        return Integer.parseInt(sb.toString(),2);
    }
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(73));
        System.out.println(Integer.toBinaryString(-73));
        int[] firstList = new int[]{0, 2};
        int[] secondList = new int[]{1, 5};
        System.out.println(Integer.bitCount(23));
    }
}
