package algorithm.practice.dumpster;


import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 练习leetcode的垃圾堆
 */
public class Dumpster {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static int countGoodTriplets(int[] arr, int a, int b, int c) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    if (Math.abs(arr[i] - arr[j]) <= a && Math.abs(arr[j] - arr[k]) <= b && Math.abs(arr[i] - arr[k]) <= c) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }
        char[] stack = new char[n];
        int size = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack[size++] = c;
            } else {
                if (size == 0) {
                    return false;
                }
                char last = stack[--size];
                if ((c == ')' && last != '(') || (c == '}' && last != '{') || (c == ']' && last != '[')) {
                    return false;
                }
            }
        }
        return size == 0;
    }

    public static int maximumDifference(int[] nums) {
        int ans = -1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] >= nums[j]) {
                    continue;
                }
                i++;
                ans = Math.max(ans, Math.abs(nums[i] - nums[j]));
            }
        }
        return ans;

    }

    public static int maximumDifference2(int[] nums) {
        int ans = -1;
        for (int i = 0, j = 1; j < nums.length; j++) {
            if (nums[i] >= nums[j]) {
                continue;
            }
            System.out.println(ans);
            ans = Math.max(ans, Math.abs(nums[i] - nums[j]));
        }
        return ans;
    }

    public static int findClosest(int x, int y, int z) {
        int xz = Math.abs(x - z);
        int yz = Math.abs(y - z);
        System.out.println(xz + "----" + yz);
        if (xz < yz) {
            return 1;
        } else if (xz == yz) {
            return 0;
        } else {
            return 2;
        }
    }

    public static String smallestPalindrome(String s) {
        int n = s.length(), mid = n / 2;
        char[] t = s.substring(0, mid).toCharArray();
        Arrays.sort(t);
        StringBuilder ans = new StringBuilder(n);
        ans.append(t);
        if (n % 2 == 1) {
            ans.append(s.charAt(mid));
        }
        for (int i = mid - 1; i >= 0; i--) {
            ans.append(t[i]);
        }
        return ans.toString();
    }

    public static int strStr(String haystack, String needle) {
        return bf(haystack, needle, 0);
    }

    public static int bf(String str, String target, int pos) {
        int lenStr = str.length(), lenSub = target.length();
        if (pos < 0 || pos > lenStr) {
            return -1;
        }
        int i = pos, j = 0;
        while (i < lenStr && j < lenSub) {
            if (str.charAt(i) == target.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j >= target.length()) {
            return i - j;
        } else {
            return -1;
        }
    }

    public static int countPairs(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j] && (i * j) % k == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int findChampion(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            int[] line = grid[i];
            int sum = 0;
            for (int num : line) {
                sum += num;
            }
            if (sum == n - 1) {
                return i;
            }
        }
        return -1;
    }

    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & -n) == n;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] cnt = new int[26];
        for (char c : magazine.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (--cnt[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public static int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        int[] cnt = new int[26];
        for (char c : chars) {
            cnt[c - 'a']++;
        }
        for (int i = 0; i < chars.length; i++) {
            if (cnt[chars[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static char findTheDifference(String s, String t) {
        char[] chars = s.toCharArray();
        int[] cnt = new int[26];
        for (char c : chars) {
            cnt[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            cnt[c - 'a']--;
            if (cnt[c - 'a'] < 0) {
                return c;
            }
        }
        return Character.MIN_VALUE;
    }

    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> w1 = new HashMap<>();
        Map<String, Integer> w2 = new HashMap<>();
        for (String word : words1) {
            w1.put(word, w1.getOrDefault(word, 0) + 1);
        }
        for (String word : words2) {
            w2.put(word, w2.getOrDefault(word, 0) + 1);
        }
        int count = 0;
        for (String word : w1.keySet()) {
            if (w1.get(word) == 1 && w2.getOrDefault(word, 0) == 1) {
                count++;
            }
        }
        return count;
    }

    public static int findKOr(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < 31; i++) {
            int cnt = 0;
            for (int num : nums) {
                if (((num >> i) & 1) == 1) {
                    cnt++;
                }
            }
            if (cnt >= k) {
                ans |= 1 << i;
            }
        }
        return ans;
    }

    public static int minMaxDifference(int num) {
        int min = num;
        int max = num;
        String s = String.valueOf(max);
        for (char c : s.toCharArray()) {
            if (c == '9') {
                continue;
            } else {
                s = s.replaceAll(String.valueOf(c), "9");
                max = Integer.parseInt(s);
                break;
            }


        }
        s = String.valueOf(min);
        for (char c : s.toCharArray()) {
            if (c == '0') {
                continue;
            } else {
                s = s.replaceAll(String.valueOf(c), "0");
                min = Integer.parseInt(s);
                break;
            }

        }
        return max - min;
    }

    public static long countBadPairs(int[] nums) {
        long count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i] - i;
            count += i - map.getOrDefault(key, 0);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        return count;
    }

    public static int[] singleNumber(int[] nums) {
        int[] result = new int[2];
        return result;
    }

    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n >>= 1;
        }
        return count;
    }

    public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder1 = new StringBuilder();
        for (String s : word1) {
            stringBuilder.append(s);
        }
        for (String s : word2) {
            stringBuilder1.append(s);
        }
        return stringBuilder.toString().contentEquals(stringBuilder1);
    }

    public static boolean checkZeroOnes(String str) {
        char[] s = str.toCharArray();
        int maxOnes = calculateMaxConsecutive(s, '1');
        int maxZeros = calculateMaxConsecutive(s, '0');
        return maxOnes > maxZeros;
    }

    private static int calculateMaxConsecutive(char[] s, char target) {
        return recursiveHelper(s, target, 0, 0, 0);
    }

    private static int recursiveHelper(char[] s, char target, int index, int cur, int maxLen) {
        if (index == s.length) {
            return Math.max(cur, maxLen);
        }
        if (s[index] == target) {
            cur++;
            maxLen = Math.max(maxLen, cur);
        } else {
            cur = 0;
        }
        return recursiveHelper(s, target, index + 1, cur, maxLen);
    }

    public static int[] leftRightDifference(int[] nums) {
        int[] pre = new int[nums.length];
        int[] next = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }
        System.out.println(Arrays.toString(pre));
        for (int i = nums.length - 1; i > 1; i--) {
            next[i] = next[i - 1] + nums[i - 1];
        }
        System.out.println(Arrays.toString(next));
        return pre;
    }

    public static int missingNumber(int[] nums) {
        int sum = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum ^= i;
        }
        for (int num : nums) {
            sum ^= num;
        }
        System.out.println(sum);
        return sum;
    }

    //i位置的数,向下调整大根堆
    public static void heapify(int[] arr, int i, int size) {
        int left = 2 * i + 1;
        while (left < size) {
            int best = left + 1 < size && arr[left + 1] < arr[left] ? left + 1 : left;
            best = arr[best] < arr[i] ? best : i;
            if (best == i) {
                break;
            }
            swap(arr, i, best);
            i = best;
            left = 2 * i + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static Integer thirdMax(int[] nums) {
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            heapify(nums, i, n);
        }
        System.out.println(Arrays.toString(nums));
        return nums[0];
    }

    public static int findJudge(int n, int[][] trust) {
        int[] inDegrees = new int[n + 1];
        int[] outDegrees = new int[n + 1];
        for (int[] edge : trust) {
            ++inDegrees[edge[0]];
            ++outDegrees[edge[1]];
        }
        for (int i = 1; i <= n; i++) {
            if (inDegrees[i] == n - 1 && outDegrees[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static int buyChoco(int[] prices, int money) {
        Arrays.sort(prices);
        int ans = money - prices[0] - prices[1];
        return ans >= 0 ? ans : money;
    }

    public static boolean validMountainArray(int[] arr) {
        if (arr == null || arr.length < 3) {
            return false;
        }
        int n = arr.length, i = 0;
        while (i + 1 < n && arr[i] < arr[i + 1]) {
            i++;
        }
        if (i == n - 1 || i == 0) {
            return false;
        }
        // 递减扫描
        while (i + 1 < n && arr[i] > arr[i + 1]) {
            i++;
        }

        return i == n - 1;
    }

    public static int countSubarrays(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }
        int left = 0, mid = 1, right, count = 0;
        for (right = 2; right < n; right++) {
            System.out.print(left + " ");
            System.out.print(mid + " ");
            System.out.println(right + " ");
            if (2 * (nums[left++] + nums[right]) == nums[mid++]) {
                count++;
            }
            if (nums[left++] + nums[right] == nums[mid++] / 2) {
                count++;
            }
        }
        return count;
    }

    public static int countDigits(int num) {
        int bits = bits(num), count = 0;
        for (int offset = 1; bits > 0; offset *= 10, bits--) {
            int i = num / offset % 10;
            if (num % i == 0) {
                count++;
            }
        }
        return count;
    }

    public static int bits(int number) {
        int count = 0;
        while (number > 0) {
            count++;
            number /= 10;
        }
        return count;
    }

    public static List<Integer> intersection(int[][] nums) {
        int[] count = new int[1001];
        int n = nums.length;
        for (int[] num : nums) {
            for (int i : num) {
                count[i]++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i <= 1000; i++) {
            if (count[i] == n) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static int diagonalPrime(int[][] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            if (isPrime(nums[i][i])) {
                res = Math.max(res, nums[i][i]);
            }
            if (isPrime(nums[i][n - 1 - i])) {
                res = Math.max(res, nums[i][n - 1 - i]);
            }
        }
        return res;
    }

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int findNumbers(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            if (bits(num) % 2 == 0) {
                ans++;
            }
        }
        return ans;
    }

    public static int findClosestNumber(int[] nums) {
        int ans = Integer.MAX_VALUE;
        for (int num : nums) {
            ans = Math.min(ans, Math.abs(num));
        }
        return ans;
    }

    public static int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[0][i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= coins[i - 1]) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
                }
            }
        }
        return dp[n][amount] == Integer.MAX_VALUE ? -1 : dp[n][amount];
    }

    private static int attempt(int[] coin, int i, int amount) {
        if (i >= coin.length) {
            return (amount == 0) ? 0 : Integer.MAX_VALUE; // 基准情况修正
        }
        int p1 = attempt(coin, i + 1, amount);
        int p2 = Integer.MAX_VALUE;
        if (amount >= coin[i]) {
            int next = attempt(coin, i, amount - coin[i]);
            if (next != Integer.MAX_VALUE) { // 避免溢出
                p2 = 1 + next; // 累加当前硬币
            }
        }
        return Math.min(p1, p2);
    }

    public static int numberOfPoints(List<List<Integer>> nums) {

        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i).getFirst() < nums.get(i - 1).getFirst()) {
            }
        }
        return 0;
    }

    public static boolean buddyStrings(String str, String goal) {
        char[] s = str.toCharArray();
        char[] goals = goal.toCharArray();
        int[] cnt = new int[26];
        for (char c : s) {
            cnt[c - 'a']++;
        }
        for (char c : goals) {
            cnt[c - 'a']--;
        }
        for (char c : s) {
            if (cnt[c - 'a'] == 0) {
                return true;
            }
        }
        return false;
    }

    public static int[] sortedSquares(int[] nums) {
        int n = nums.length, l = 0, r = n - 1;
        while (l++ < r--) {
            if (Math.abs(nums[l]) > Math.abs(nums[r])) {
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
            }
        }
        return nums;
    }

    public static int arraySign(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            if (num == 0) {
                return 0;
            }
            if (num < 0) {
                ans++;
            }
        }
        System.out.println(ans);
        return ans % 2 == 0 ? 1 : -1;
    }

    public static void duplicateZeros(int[] arr) {
        int n = arr.length, top = 0, i = 0;
        while (top < n) {
            i++;
            if (arr[i] != 0) {
                top++;
            } else {
                top += 2;
            }
        }
        int j = n - 1;
        if (top == n + 1) {
            arr[j] = 0;
            j--;
            i--;
        }
        while (j == 0) {
            arr[j] = arr[i];
            j--;
            if (arr[i] == 0) {
                arr[j] = arr[i];
                j--;
            }
            i--;
        }
    }

    public static String decodeMessage(String key, String message) {
        char[] hash = new char[26];
        char cur = 'a';

        for (char c : key.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            int i = c - 'a';
            if (hash[i] > 0) {
                continue;
            }
            hash[i] = cur++;
        }
        char[] res = message.toCharArray();
        int len = message.length();
        for (int i = 0; i < len; i++) {
            if (res[i] != ' ') {
                res[i] = hash[res[i] - 'a'];
            }
        }
        return String.valueOf(res);


    }

    public static boolean checkAlmostEquivalent(String word1, String word2) {
        int[] counts = new int[26];
        for (char c : word1.toCharArray()) {
            counts[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            counts[c - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (Math.abs(counts[i]) > 3) {
                return false;
            }
        }
        return true;
    }

    public static int countConsistentStrings(String allowed, String[] words) {
        int mask = 0, cnt = 0;
        for (char c : allowed.toCharArray()) {
            mask |= (1 << (c - 'a'));
        }
        for (String word : words) {
            int mask1 = 0;
            for (char c : word.toCharArray()) {
                mask1 |= (1 << (c - 'a'));
            }
            if ((mask1 | mask) == mask) {
                cnt++;
            }
        }
        return cnt;

    }

    public static int mostFrequent(int[] nums, int key) {
        int[] arr = new int[1001];
        int mostFreq = 0, ans = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == key && ++arr[nums[i + 1]] > mostFreq) {
                mostFreq = arr[nums[i + 1]];
                ans = nums[i + 1];
            }
        }

        return ans;
    }

    public static String replaceDigits(String str) {
        char[] s = str.toCharArray();
        for (int i = 1; i < s.length; i += 2) {
            s[i] = (char) (s[i - 1] - '0' + s[i]);
        }
        return String.valueOf(s);
    }

    public static int[][] construct2DArray(int[] original, int m, int n) {
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = original[i + j];
            }
        }
        return res;
    }

    public static void test(int n) {
        int len = 1;
        int offset = 1;
        int tmp = n / 10;

        while (tmp > 0) {
            len++;
            offset *= 10;
            tmp /= 10;
            System.out.println(tmp);
        }
        System.out.println(len);
        System.out.println(offset);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int l = m - 1, r = n - 1, i = m + n - 1;
        while (l >= 0 && r >= 0) {
            nums1[i--] = nums1[l] > nums2[r] ? nums1[l--] : nums2[r--];
        }
        while (l >= 0) {
            nums1[i--] = nums1[l--];
        }
        while (r >= 0) {
            nums1[i--] = nums2[r--];
        }
        System.out.println(Arrays.toString(nums1));
    }

    public static int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            res[i] = count(i);
        }
        return res;
    }

    private static int count(int n) {
        int count = 0;
        while (n > 0) {
            count++;
            n &= (n - 1);
        }
        return count;
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static int findLHS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (Math.abs(nums[i] - nums[j]) == 1) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return 0;
    }

    public static int[] findErrorNums(int[] nums) {
        int[] error = new int[2];
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int i = 1; i <= n; i++) {
            int count = map.getOrDefault(i, 0);
            if (count == 2) {
                error[0] = i;
            } else if (count == 0) {
                error[1] = i;
            }
        }
        return new int[]{-1, -1};
    }

    public static int findLengthOfLCIS(int[] nums) {
        int l = 0, r = 0, maxLen = 0;
        while (r < nums.length) {
            if (l == r || nums[r - 1] < nums[r]) {
                maxLen = Math.max(maxLen, r - l + 1);
                r++;
            } else {
                l = r;

            }
        }
        return maxLen;
    }

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 2);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Arrays.sort(nums2);
        int n = nums1.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int ans = findLeft(nums2, nums1[i]);
            System.out.println(ans);
            res[i] = ans == -1 ? -1 : nums2[ans];
        }
        return res;
    }

    public static int findLeft(int[] nums, int target) {
        int l = 0, r = nums.length - 1, ans = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 2);
            if (nums[mid] >= target) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    //101
    public static int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public static boolean isHappy(int n) {
        int slow = n;
        int fast = getNext(n);
        while (fast != 1 && fast != slow) {
            slow = getNext(slow);
            fast = getNext(getNext(n));
        }
        return fast == 1;
    }

    public static int addDigits(int num) {
        while (num >= 10) {
            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        }
        return num;
    }

    public static int[] move = new int[]{-1, 0, 1, 0, -1};

    public static int islandPerimeter(int[][] grid) {
        int m = grid.length, n = grid[0].length, ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int x = i + move[k];
                        int y = j + move[k + 1];
                        if (x < 0 || x >= n || y < 0 || y >= m || grid[x][y] == 0) {
                            cnt++;
                        }
                    }
                    ans += cnt;
                }
            }
        }
        return ans;
    }

    public static List<String> getLongestSubsequence(String[] words, int[] groups) {
        List<String> ans = new ArrayList<>();
        int n = words.length;
        for (int i = 0; i < n; i++) {
            if (i == 0 || groups[i] != groups[i - 1]) {
                ans.add(words[i]);
            }
        }
        return ans;
    }

    public static List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        List<String> ans = new ArrayList<>();
        int n = words.length;
        for (int i = 0; i < n; i++) {
            if (i == 0 || groups[i] != groups[i - 1]) {
                ans.add(words[i]);
            }
        }
        return ans;


    }

    public static int mySqrt(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    public static boolean isPalindrome(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        char[] reverse = new char[n];
        for (int i = 0; i < n; i++) {
            if (chars[i] >= 'a' && chars[i] <= 'z') {
                reverse[i] = chars[i];
            }
        }
        System.out.println(Arrays.toString(reverse));
        return true;

    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> ans = new ArrayList<>();
        //inorderTraversal(root.left);
        ans.add(root.val);
        //inorderTraversal(root.right);
        return ans;
    }

    public static boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length(), i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }

    public static String[] findWords(String[] words) {
        String s1 = "qwertyuiop", s2 = "asdfghjkl", s3 = "zxcvbnm";
        List<String> ans = new ArrayList<>();
        for (String w : words) {
            String lowerWord = w.toLowerCase();
            System.out.println("lowerWord==" + lowerWord);
            boolean isS1 = true, isS2 = true, isS3 = true;
            for (char c : lowerWord.toCharArray()) {
                System.out.println("c==" + c);
                if (!s1.contains(String.valueOf(c))) {
                    isS1 = false;
                }
                if (!s2.contains(String.valueOf(c))) {
                    isS2 = false;
                }
                if (!s3.contains(String.valueOf(c))) {
                    isS3 = false;
                }
            }
            if (isS1 || isS2 || isS3) {
                ans.add(w);
            }
        }
        return ans.toArray(new String[0]);
    }

    public static String finalString(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c == 'i') {
                sb.reverse();  // 遇到 'i' 时反转已积累的字符
            } else {
                sb.append(c);  // 其他字符直接追加
            }
        }
        return sb.toString();
    }

    public static String finalString1(String str) {
        char[] queue = new char[100];
        int head = 0, tail = 0;
        boolean flag = true;
        for (char c : str.toCharArray()) {
            if (c == 'i') {
                flag = !flag;
            } else if (flag) {
                queue[tail++] = c;
            } else {
                queue[head++] = c;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tail - head; i++) {
            sb.append(queue[i]);
        }
        if (!flag) {
            sb.reverse();
        }
        System.out.println(queue);
        return sb.toString();
    }

    public static char[] reserve(char[] str, int l, int r) {
        while (l < r) {
            char temp = str[l];
            str[l] = str[r];
            str[r] = temp;
            l++;
            r--;
        }
        return str;
    }

    public static boolean findSubarrays(int[] nums) {
        int n = nums.length;
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < n - 1; i++) {
            int sum = nums[i] + nums[i + 1];
            if (!seen.add(sum)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMonotonic(int[] nums) {
        boolean inc = true, dec = true;
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                inc = false;
            }
            if (nums[i] < nums[i + 1]) {
                dec = false;
            }
        }
        return inc || dec;
    }

    public static int diagonalSum(int[][] mat) {
        int n = mat.length, sum = 0, mid = n / 2;
        for (int i = 0; i < n; i++) {
            sum += mat[i][i] + mat[i][n - 1 - i];
        }

        return sum - mat[mid][mid] * (n & 1);
    }

    public static int[] decode(int[] encoded, int first) {
        int n = encoded.length + 1;
        int[] arr = new int[n];
        arr[0] = first;
        for (int i = 1; i < n; i++) {
            arr[i] = arr[i - 1] ^ encoded[i - 1];
        }
        return arr;
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (map.containsKey(num) && i - map.get(num) <= k) {
                return true;
            }
            map.put(num, i);
        }
        return false;
    }

    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode temp = dummy;
        while (temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return dummy.next;
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }
        return p1;
    }

    public static int[] corpFlightBookings(int[][] booking, int n) {
        int[] cnt = new int[n + 2];
        for (int[] book : booking) {
            cnt[book[0]] += book[2];
            cnt[book[1] + 1] -= book[2];
        }
        System.out.println(Arrays.toString(cnt));
        for (int i = 1; i <= n; i++) {
            cnt[i] += cnt[i - 1];
        }
        return cnt;
    }

    public static int minZeroArray(int[] nums, int[][] queries) {
        int left = 0, right = nums.length;
        if (!check(nums, queries, right)) {
            return -1;
        }
        while (left < right) {
            int mid = (left + right) / 2;
            if (check(nums, queries, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static boolean check(int[] nums, int[][] queries, int k) {
        int[] deltaArray = new int[nums.length + 1];
        for (int i = 0; i < k; i++) {
            int left = queries[i][0], right = queries[i][1], val = queries[i][2];
            deltaArray[left] -= val;
            deltaArray[right + 1] += val;
        }
        int[] operationArray = new int[deltaArray.length];
        int currentOperation = 0;
        for (int i = 0; i < deltaArray.length; i++) {
            currentOperation += deltaArray[i];
            operationArray[i] = currentOperation;
        }
        for (int i = 0; i < nums.length; i++) {
            if (operationArray[i] < nums[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] cnt = new int[n + 1];
        for (int[] query : queries) {
            cnt[query[0]] -= 1;
            cnt[query[1] + 1] += 1;
        }
        for (int i = 1; i < cnt.length; i++) {
            cnt[i] += cnt[i - 1];
        }
        for (int i = 0; i < n; i++) {
            nums[i] += cnt[i];
            if (nums[i] > 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean exist(char[][] board, String word) {
        if (word.isEmpty()) {
            return true;
        }
        build(word.toLowerCase());
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, i, j, 1)) {
                    clear();
                    return true;
                }
            }
        }
        clear();
        return false;
    }

    public static int MAXN = 10001;
    public static int[][] tree = new int[MAXN][];
    public static int[] pass = new int[MAXN];
    public static int[] end = new int[MAXN];
    public static int cnt;

    public static void build(String word) {
        cnt = 1;
        int cur = 1;
        pass[cur]++;
        for (int i = 0, path; i < word.length(); i++) {
            path = word.charAt(i) - 'a';
            if (tree[cur][path] == 0) {
                tree[cur][path] = ++cnt;
            }
            cur = tree[cur][path];
            pass[cur]++;
        }
        end[cur]++;
    }

    public static boolean dfs(char[][] board, int i, int j, int t) {
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] == 0) {
            return false;
        }
        char temp = board[i][j];
        int road = Character.toLowerCase(temp) - 'a'; // 修复此处
        t = tree[t][road];
        if (pass[t] == 0) {
            return false;
        }
        if (end[t] > 0) {
            return true;
        }
        boolean find = dfs(board, i - 1, j, t) || dfs(board, i + 1, j, t) || dfs(board, i, j - 1, t) || dfs(board, i, j + 1, t);
        board[i][j] = temp;
        return find;
    }

    public static void clear() {
        for (int i = 1; i <= cnt; i++) {
            Arrays.fill(tree[i], 0);
            pass[i] = 0;
            end[i] = 0;
        }
    }

    public static int MAXN1 = 50003;
    public static int[] arr = new int[MAXN1];
    public static int[] help = new int[MAXN1];

    public static int reversePairs(int[] record) {
        arr = record;
        int n = record.length;
        help = new int[n];
        return f(0, n - 1);
    }

    public static int f(int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = (l + r) / 2;
        return f(l, mid) + f(mid + 1, r) + merge(l, mid, r);
    }

    public static int merge(int l, int m, int r) {
        int ans = 0;
        for (int i = m, j = r; i >= l; i--) {
            while (j >= m + 1 && arr[i] <= arr[j]) {
                j--;
            }
            ans += j - m;
        }
        int i = l, a = l, b = m + 1;
        while (a <= m && b <= r) {
            help[i++] = arr[a] < arr[b] ? arr[a++] : arr[b++];
        }
        while (a <= m) {
            help[i++] = arr[a++];
        }
        while (b <= r) {
            help[i++] = arr[b++];
        }
        for (i = l; i <= r; i++) {
            arr[i] = help[i];
        }
        return ans;
    }


    public static int reversePairs1(int[] record) {
        arr = record;
        int n = record.length;
        help = new int[n];
        return f1(0, n - 1);
    }

    private static int f1(int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = (l + r) / 2;
        return f1(l, mid) + f1(mid + 1, r) + merge1(l, mid, r);
    }

    private static int merge1(int l, int m, int r) {
        int ans = 0;
        // 统计逆序对数目
        for (int i = m, j = r; i >= l; i--) {
            while (j >= m + 1 && arr[i] <= arr[j]) {
                j--;
            }
            ans += j - m; // 右半部分中比arr[i]小的元素个数
        }
        // 合并到help数组
        int i = l;
        int a = l, b = m + 1;
        while (a <= m && b <= r) {
            help[i++] = arr[a] <= arr[b] ? arr[a++] : arr[b++];
        }
        while (a <= m) help[i++] = arr[a++];
        while (b <= r) help[i++] = arr[b++];
        // 复制回原数组
        for (i = l; i <= r; i++) {
            arr[i] = help[i];
        }
        return ans;
    }

    public static int maxRemoval(int[] nums, int[][] queries) {
        Arrays.sort(queries, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int n = nums.length;
        int[] diff = new int[n + 1];
        int sumD = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            sumD += diff[i];
            // 维护左端点 <= i 的区间
            while (j < queries.length && queries[j][0] <= i) {
                pq.add(queries[j][1]);
                j++;
            }
            // 选择右端点最大的区间
            while (sumD < nums[i] && !pq.isEmpty() && pq.peek() >= i) {
                sumD++;
                diff[pq.poll() + 1]--;
            }
            if (sumD < nums[i]) {
                return -1;
            }
        }
        return pq.size();
    }

    public static int search1(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public int[] plusOne(int[] digits) {
        int len = digits.length;
        for (int i = len - 1; i >= 0; i--) {
            digits[i] = (digits[i] + 1) % 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        digits = new int[len + 1];
        digits[0] = 1;
        return digits;
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.isEmpty()) {
                break;
            }
        }
        return prefix;
    }

    public static String longestCommonPrefix(String str1, String str2) {
        int len = Math.min(str1.length(), str2.length()), index = 0;
        while (index < len && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    public static int findPeakElement(int[] arr) {
        int n = arr.length;
        if (arr.length == 1) {
            return 0;
        }
        if (arr[0] > arr[1]) {
            return 0;
        }
        if (arr[n - 1] > arr[n - 2]) {
            return n - 1;
        }
        int l = 1, r = n - 2, m = 0, ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr[m - 1] > arr[m]) {
                r = m - 1;
            } else if (arr[m] < arr[m + 1]) {
                l = m + 1;
            } else {
                ans = m;
                break;
            }
        }
        return ans;
    }

    public static int binSearch(int[] arr, int left, int right, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int mid = 0;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static int findPeakElement1(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > arr[right]) {
                // 最大值在左半部分（包括 mid）
                right = mid;
            } else {
                // 最大值在右半部分（不包括 mid）
                left = mid + 1;
            }
        }
        return left;
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        int[] nums = new int[n];
        for (int i = 1; i <= n; i++) {
            nums[i - 1] = i;
        }
        System.out.println(Arrays.toString(nums));
        f(nums, 0, ans);
        return ans;
    }

    private static void f(int[] nums, int i, List<List<Integer>> ans) {
        if (i == 2) {
            List<Integer> cur = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                cur.add(nums[j]);
            }
            ans.add(cur);
        } else {
            for (int j = i; j < nums.length; j++) {
                swap(nums, i, j);
                f(nums, i + 1, ans);
                swap(nums, i, j);
            }
        }
    }

    public static int findDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return -1;
        }
        int slow = nums[0], fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }

    public static List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> ans = new ArrayList<>();
        if (words == null) {
            return ans;
        }
        for (int i = 0; i < words.length; i++) {
            if (words[i].indexOf(x) != -1) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static class NumArray {
        private int[] nums;
        private int[] tree;

        private int lowbit(int i) {
            return i & -i;
        }

        private void add(int index, int val) {
            while (index < tree.length) {
                tree[index] += val;
                index += lowbit(index);
            }
        }

        private int sum(int index) {
            int ans = 0;
            while (index > 0) {
                ans += tree[index];
                index -= lowbit(index);
            }
            return ans;
        }

        public NumArray(int[] nums) {
            this.tree = new int[nums.length + 1];
            this.nums = nums;
            for (int i = 0; i < nums.length; i++) {
                add(i + 1, nums[i]);
            }
        }

        public void update(int index, int val) {
            add(index + 1, val - nums[index]);
            nums[index] = val;
        }

        public int sumRange(int left, int right) {
            return sum(right + 1) - sum(left);
        }
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = binSearchFirstColumn(matrix, target);
        if (row < 0) {
            return false;
        }
        return binSearchFirstRow(matrix[row], target);
    }

    private static int binSearchFirstColumn(int[][] matrix, int target) {
        int low = 0, high = matrix.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (matrix[mid][0] <= target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static boolean binSearchFirstRow(int[] row, int target) {
        int low = 0, high = row.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (row[mid] == target) {
                return true;
            } else if (row[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

    public static boolean searchMatrixBin(int[][] matrix, int target) {
        int left = 0, right = matrix.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                if (anInt == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int countPrimes(int n) {
        int ans = n - 1;
        if (ans < 2) {
            return 0;
        }
        boolean[] isPrime = new boolean[n + 1];
        int count = (ans + 1) / 2;
        for (int i = 3; i * i <= ans; i += 2) {
            if (!isPrime[i]) {
                for (int j = i * i; j <= ans; j += 2 * i) {
                    if (!isPrime[j]) {
                        isPrime[j] = true;
                        count--;
                    }
                }
            }
        }
        return count;
    }

    public static int integerBreak(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], 1);
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= i) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - i] * i);
                }
            }
        }
        return dp[n][n];
    }

    public static int integerReplacement(int n) {
        if (n == 1) {
            return 0;
        }
        if (n % 2 == 0) {
            return 1 + integerReplacement(n / 2);
        }

        return 2 + Math.min(integerReplacement(n / 2), integerReplacement(n / 2 + 1));

    }

    public static boolean isAnagram(String s, String t) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            count[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }

    class NumArray1 {
        public int[] sum;

        public NumArray1(int[] nums) {
            sum = new int[nums.length];
            sum[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                sum[i] = sum[i - 1] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return left == 0 ? sum[right] : sum[right] - sum[left - 1];
        }
    }

    public static int differenceOfSums(int n, int m) {
        int num1 = 0, num2 = 0;
        for (int i = 1; i <= n; i++) {
            if (i % m == 0) {
                num2 += i;
            } else {
                num1 += i;
            }
        }
        return num1 - num2;
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        return Arrays.stream(nums2).filter(set::remove).toArray();
    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;
        for (int x : s) {
            if (i < g.length && g[i] <= x) {
                i++;
            }
        }
        return i;
    }

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 1;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i - 1]) {
                nums[i] = nums[j];
                i++;
            }

        }
        return i;
    }

    public static int removeDuplicatesII(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 1;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i - 1]) {
                nums[i] = nums[j];
                i += 2;
            }
        }
        System.out.println(Arrays.toString(nums));
        return i;
    }

    public static int longestPalindrome(String[] words) {
        int[][] cnt = new int[26][26];
        for (String word : words) {
            cnt[word.charAt(0) - 'a'][word.charAt(1) - 'a']++;
        }
        int ans = 0, odd = 0;
        for (int i = 0; i < 26; i++) {
            int c = cnt[i][i];
            ans += c - c % 2;
            odd |= c % 2;
            for (int j = i + 1; j < 26; j++) {
                ans += Math.min(cnt[i][j], cnt[j][i]) * 2;
            }
        }
        return (ans + odd) * 2;
    }

    public int findMaximumXOR(int[] nums) {
        build(nums);
        int ans = 0;
        for (int num : nums) {
            ans = Math.max(ans, maxXor(num));
        }
        clear();
        return ans;
    }

    public static int MAXN2 = 300001;
    public static int[][] tree1 = new int[MAXN2][2];
    public static int cnt2;
    public static int high;

    public static void build(int[] nums) {
        cnt2 = 1;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        high = 31 - Integer.numberOfLeadingZeros(max);
        for (int num : nums) {
            insert(num);
        }
    }

    public static void insert(int num) {
        int cur = 1;
        for (int i = high, path; i >= 0; i--) {
            path = (num >> i) & i;
            if (tree[cur][path] == 0) {
                tree[cur][path] = ++cnt;
            }
            cur = tree[cur][path];
        }
    }

    public static int maxXor(int num) {
        int ans = 0, cur = 1;
        for (int i = high, status, want; i >= 0; i--) {
            status = (num >> i) & 1;
            want = status ^ 1;
            if (tree[cur][want] == 0) {
                want ^= 1;
            }
            ans = (status ^ want) << i;
            cur = tree[cur][want];
        }
        return ans;
    }

    public static void clear1() {
        for (int i = 0; i < tree1.length; i++) {
            tree1[i][0] = tree1[i][1] = 0;
        }
    }

    public static int maxProfit(int[] prices) {
        int ans = 0;
        for (int i = 1, min = prices[0]; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            ans = Math.max(ans, prices[i] - min);
        }
        return ans;
    }

    public static int findNthDigit(int n) {
        return 0;
    }

    public static boolean exist1(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, i, j, w, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dfs(char[][] board, int i, int j, char[] w, int k) {
        if (k == w.length) {
            return true;
        }
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != w[k]) {
            return false;
        }
        char ch = board[i][j];
        board[i][j] = '0';
        boolean flag = dfs(board, i - 1, j, w, k + 1) || dfs(board, i + 1, j, w, k + 1) || dfs(board, i, j - 1, w, k + 1) || dfs(board, i, j + 1, w, k + 1);
        board[i][j] = ch;
        return flag;
    }

    public static int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length; i += 2) {
            System.out.println(nums[i]);
            ans += nums[i];
        }
        return ans;
    }

    public static int MAX = 1000;
    public static String[] queue = new String[MAX];
    public static int head, tail;
    public static List<List<String>> graph = new ArrayList<>();
    public static Set<String> visited = new HashSet<>();

    static {
        for (int i = 0; i <= 26; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        for (int i = 0; i < 26; i++) {
            graph.get(i).clear();
        }
        for (String word : wordDict) {
            graph.get(word.charAt(0) - 'a').add(word);
        }
        head = tail = 0;
        queue[tail++] = s;
        visited.add(s);
        while (head < tail) {
            int size = tail - head;
            for (int i = 0; i < size; i++) {
                String cur = queue[head++];
                for (String word : graph.get(cur.charAt(0) - 'a')) {
                    if (cur.startsWith(word)) {
                        String next = cur.substring(word.length());
                        if (next.isEmpty()) {
                            return true;
                        }
                        if (!visited.contains(next)) {
                            visited.add(next);
                            queue[tail++] = next;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static long dividePlayers(int[] skills) {
        int sum = 0;
        for (int skill : skills) {
            sum += skill;
        }
        if (sum % skills.length / 2 != 0) {
            return -1;
        }
        Arrays.sort(skills);
        System.out.println(Arrays.toString(skills));
        sum = 1;
        for (int i = 0; i < skills.length; i++) {
            sum *= (skills[i] + skills[skills.length - i - 1]);
        }
        System.out.println(sum);
        return 0;
    }

    public static String answerString(String word, int numFriends) {
        int len = word.length();
        if (numFriends > len) {
            return "";
        }
        if (numFriends == 1) {
            return word;
        }
        int subLength = len - numFriends + 1;
        String ans = "";
        for (int i = 0; i <= len; i++) {
            String subStr = word.substring(i, Math.min(i + subLength, len));
            if (subStr.compareTo(ans) > 0) {
                ans = subStr;
            }
        }
        return ans;
    }

    public static int getSum(int a, int b) {
        if (a < 0) {
            return minus(b, a);
        }
        if (b < 0) {
            return minus(a, b);
        }
        return add(a, b);
    }

    public static int add(int a, int b) {
        int ans = a;
        while (b != 0) {
            ans = a ^ b;
            b = (a & b) << 1;
            a = ans;
        }
        return ans;
    }

    public static int minus(int a, int b) {
        return add(a, add(~b, 1));
    }

    private static int[] father = new int[26];

    public static void build() {
        for (int i = 0; i < 26; i++) {
            father[i] = i;
        }
    }

    public static int find(int i) {
        if (i != father[i]) {
            father[i] = find(father[i]);
        }
        return father[i];
    }

    public static void union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
            if (fx < fy) {
                father[fy] = fx;
            } else {
                father[fx] = fy;
            }
        }
    }

    public static String smallestEquivalentString(String s1, String s2, String baseStr) {
        build();
        for (int i = 0; i < s1.length(); i++) {
            union(s1.charAt(i) - 'a', s2.charAt(i) - 'a');
        }
        StringBuilder sb = new StringBuilder();
        for (char c : baseStr.toCharArray()) {
            int root = find(c - 'a');
            sb.append(((char) (root + 'a')));
        }
        return sb.toString();
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int ans = 0;
        for (int l = 0, r = 0; r < nums.length; ) {
            if (nums[r] == 1 && nums[r] == nums[r + 1]) {
                r++;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    public static String robotWithString(String s) {
        int[] cnt = new int[26];
        int size = 0;
        StringBuilder ans = new StringBuilder();
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        char[] stack = new char[10000];
        char minCharacter = 'a';
        for (char c : s.toCharArray()) {
            stack[size++] = c;
            cnt[c - 'a']--;
            while (minCharacter != 'z' && cnt[minCharacter - 'a'] == 0) {
                minCharacter++;
            }
            while (size != 0 && stack[size - 1] <= minCharacter) {
                ans.append(stack[--size]);
            }
        }
        return ans.toString();
    }

    public static int thirdMax1(int[] nums) {
        return randomizedSelect(nums, nums.length - 3);
    }

    public static int first, last;

    public static int randomizedSelect(int[] arr, int i) {
        int ans = 0;
        for (int l = 0, r = arr.length - 1; l <= r; ) {
            partition(arr, l, r, (int) (Math.random() * (r - l + 1)));
            if (i < first) {
                r = first - 1;
            } else if (i > last) {
                l = last + 1;
            } else {
                ans = arr[i];
                break;
            }
        }
        return ans;
    }

    public static void partition(int[] arr, int l, int r, int x) {
        first = l;
        last = r;
        int i = l;
        while (i <= last) {
            if (arr[i] == x) {
                i++;
            } else if (arr[i] < x) {
                swap(arr, first++, i++);
            } else {
                swap(arr, i, last--);
            }
        }
    }

    public static char[] genes = new char[]{'A', 'C', 'G', 'T'};

    public static int minMutation(String startGene, String endGene, String[] bank) {
        HashSet<String> dict = new HashSet<>(List.of(bank));
        if (!dict.contains(endGene)) {
            return -1;
        }
        HashSet<String> smallLevel = new HashSet<>();
        HashSet<String> bigLevel = new HashSet<>();
        HashSet<String> nextLevel = new HashSet<>();
        smallLevel.add(startGene);
        bigLevel.add(endGene);
        for (int len = 2; !smallLevel.isEmpty(); len++) {
            for (String w : smallLevel) {
                char[] word = w.toCharArray();
                for (int i = 0; i < word.length; i++) {
                    char old = word[i];
                    for (char gene : genes) {
                        if (gene != old) {
                            word[i] = gene;
                            String next = String.valueOf(word);
                            if (bigLevel.contains(next)) {
                                return len;
                            }
                            if (dict.contains(next)) {
                                dict.remove(next);
                                nextLevel.add(next);
                            }
                        }
                    }
                    word[i] = old;
                }
            }
            if (nextLevel.size() <= bigLevel.size()) {
                smallLevel = bigLevel;
            } else {
                smallLevel = bigLevel;
                bigLevel = nextLevel;
            }
            nextLevel = new HashSet<>();
        }
        return -1;
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    // 回溯函数
    private static void backtrack(int[] candidates, int target, int start, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            // 找到一组结果，加入结果集
            result.add(new ArrayList<>(current));
            return;
        }

        if (target < 0) {
            // 当前组合的和超过了 target，返回
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            // 选择 candidates[i]
            current.add(candidates[i]);
            // 递归调用时，仍然可以选择当前元素 i
            backtrack(candidates, target - candidates[i], i + 1, current, result);
            // 回溯，移除最后一个元素
            current.removeLast();
        }
    }

    public static int combinationSum4(int[] nums, int target) {
        return dfs(nums, 0, target);
    }

    private static int dfs(int[] nums, int i, int target) {
        if (i == nums.length) {
            return 1;
        }
        if (target == 0) {
            return 1;
        }
        if (target < 0) {
            return 0;
        }
        int count = 0;
        return count + dfs(nums, i, target - nums[i]);
    }

    public static int[] duration = new int[]{1, 7, 30};

    public static int mincostTickets1(int[] days, int[] costs) {
        return f1(days, costs, 0);
    }

    private static int f1(int[] days, int[] costs, int i) {
        if (i == days.length) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int k = 0, j = i; k < 3; k++) {
            while (j < days.length && duration[k] + days[i] > days[j]) {
                j++;
            }
            ans = Math.min(ans, costs[k] + f1(days, costs, j));
        }
        return ans;
    }

    public static int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] dp = new int[366];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int k = 0, j = i; k < 3; k++) {
                while (j < days.length && duration[k] + days[i] > days[j]) {
                    j++;
                }
                dp[i] = Math.min(dp[i], costs[k] + dp[j]);
            }
        }
        return dp[0];
    }

    public static long[] lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>(n);
        int curr = 1;
        for (int i = 0; i < n; i++) {
            res.add(curr);
            if (curr * 10 <= n) {
                curr *= 10; // 下一个层级
            } else {
                // 同层加一，直到不能加
                while (curr % 10 == 9 || curr + 1 > n) {
                    curr /= 10; // 回退
                }
                curr++; // 同层下一个
            }
        }
        return res.stream().mapToLong(Long::valueOf).toArray();
    }

    public int findKthNumber(int n, int k) {
        int curr = 1;
        k--;
        while (k > 0) {
            int steps = getSteps(curr, n);
            if (steps <= k) {
                k -= steps;
                curr++;
            } else {
                curr = curr * 10;
                k--;
            }
        }
        return curr;
    }

    public int getSteps(int curr, long n) {
        int steps = 0;
        long first = curr;
        long last = curr;
        while (first <= n) {
            steps += Math.min(last, n) - first + 1;
            first = first * 10;
            last = last * 10 + 9;
        }
        return steps;
    }

    public static void sortColors(int[] nums) {
        int[] cnt = new int[3];
        for (int num : nums) {
            cnt[num]++;
        }
        int index = 0;
        for (int i = 0; i < 3; i++) {
            while (cnt[i] > 0) {
                nums[index++] = i;
                cnt[i]--;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    public static int maxDifference(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        int maxOdd = 0, minEven = Integer.MAX_VALUE;
        for (int c : cnt) {
            if (c % 2 == 1) {
                maxOdd = Math.max(maxOdd, c);
            } else if (c > 0) {
                minEven = Math.min(minEven, c);
            }
        }
        return maxOdd - minEven;
    }

    public static int lastRemaining(int n) {
        int[] arr = new int[n];
        for (int i = 1; i <= n; i++) {
            arr[i - 1] = i;
        }
        System.out.println(Arrays.toString(arr));
        return 0;
    }

    public static int maxAdjacentDistance(int[] nums) {
        int n = nums.length;
        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            int next = (i + 1) % n;
            int diff = Math.abs(nums[i] - nums[next]);
            maxDiff = Math.max(maxDiff, diff);
        }

        return maxDiff;
    }

    public static int f1(int[] nums, int target) {
        return f1(nums, target, 0, 0);
    }

    private static int f1(int[] nums, int target, int i, int sum) {
        if (i == nums.length) {
            return sum == target ? 1 : 0;
        }
        return f1(nums, target, i + 1, sum + nums[i]) + f1(nums, target, i + 1, sum - nums[i]);
    }

    public static int f2(int[] nums, int target) {
        int s = 0;
        for (int num : nums) {
            s += num;
        }
        int n = nums.length, m = 2 * s + 1;
        if (target < -s || target > s) {
            return 0;
        }
        int[][] dp = new int[n + 1][m];
        dp[n][target + s] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = -s; j <= s; j++) {
                if (j + nums[i] + s < m) {
                    dp[i][j + s] += dp[i + 1][j + nums[i] + s];
                }
                if (j - nums[i] + s >= 0) {
                    dp[i][j + s] += dp[i + 1][j - nums[i] + s];
                }
            }
        }
        return dp[0][s];
    }

    private static int replace(String s, char oldChar, char newChar) {
        String t = s.replace(oldChar, newChar);
        return Integer.parseInt(t);
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int start, List<String> path, List<String> result) {
        // 如果已经有 4 段且刚好用完所有字符，则是一个合法 IP
        if (path.size() == 4 && start == s.length()) {
            result.add(String.join(".", path));
            return;
        }

        // 如果段数超了，或者字符用完但没凑够4段，直接返回
        if (path.size() >= 4) {
            return;
        }

        // 尝试从当前位置开始，取 1 到 3 位数字作为一段
        for (int i = 1; i <= 3; i++) {
            if (start + i > s.length()) break;

            String segment = s.substring(start, start + i);

            // 如果 segment 有前导0但长度大于1，或值超过255，跳过
            if ((segment.startsWith("0") && segment.length() > 1) || Integer.parseInt(segment) > 255) {
                continue;
            }

            path.add(segment); // 做选择
            backtrack(s, start + i, path, result);
            path.remove(path.size() - 1); // 撤销选择
        }
    }

    public static int numDistinct(String str, String target) {
        char[] s = str.toCharArray();
        char[] t = target.toCharArray();
        int[][] dp = new int[s.length + 1][t.length + 1];
        for (int i = 0; i <= s.length; i++) {
            dp[i][t.length] = 1;
        }
        for (int i = s.length - 1; i >= 0; i--) {
            for (int j = 0; j < t.length; j++) {
                if (s[i] == t[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }

    public static int numDistinctZip(String str, String target) {
        char[] s = str.toCharArray();
        char[] t = target.toCharArray();
        int[] dp = new int[t.length + 1];
        for (int i = 0; i <= s.length; i++) {
            dp[t.length] = 1;
        }
        for (int i = s.length - 1; i >= 0; i--) {
            for (int j = 0; j < t.length; j++) {
                if (s[i] == t[j]) {
                    dp[j] += dp[j + 1];
                }
            }
        }
        return dp[0];
    }

    public static int f2(char[] s1, char[] s2, int len1, int len2) {
        if (len1 == 0 || len2 == 0) {
            return 0;
        }
        int ans = 0;
        if (s1[len1 - 1] == s2[len2 - 1]) {
            ans = f2(s1, s2, len1 - 1, len2 - 1) + 1;
        } else {
            ans = (f2(s1, s2, len1, len2 - 1) + f2(s1, s2, len1 - 1, len2));
        }
        return ans;
    }

    public static int f1(char[] s, char[] t, int i, int j, int[][] dp) {
        int ans;
        if (j == t.length) {
            return 1;   // target 匹配完成
        }
        if (i == s.length) {
            return 0;   // source 结束但 target 没结束
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        } else {
            if (s[i] == t[j]) {
                //选或不选
                ans = f1(s, t, i + 1, j + 1, dp) + f1(s, t, i + 1, j, dp);
            } else {
                //只能不选
                ans = f1(s, t, i + 1, j, dp);
            }
        }
        dp[i][j] = ans;
        return ans;
    }

    public static List<Integer> best = new ArrayList<>();

    public static List<Integer> largestDivisibleSubsetDfs(int[] nums) {
        Arrays.sort(nums);

        backtrack(nums, 0, new ArrayList<>());
        return best;
    }

    private static void backtrack(int[] nums, int start, List<Integer> path) {
        if (path.size() > best.size()) {
            best = new ArrayList<>(path); // 记录当前最大集合
        }

        for (int i = start; i < nums.length; i++) {
            if (path.isEmpty() || nums[i] % path.getLast() == 0) {
                path.add(nums[i]);
                backtrack(nums, i + 1, path);
                path.removeLast(); // 回溯
            }
        }
    }

    public static int largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);  // 先排序，方便判断整除关系
        int[] dp = new int[nums.length + 1];

        return dfs1(nums, 0, 0); // 从第0个元素开始，prevVal为0表示还没选元素
    }

    // 递归函数：
    // start：当前考察的元素索引
    // prevVal：上一个选中的元素的值，0表示还没选
    public static int dfs1(int[] nums, int start, int prevVal) {
        // 如果已经考察完所有元素，返回0（没有更多元素可以选）
        if (start == nums.length) return 0;

        int taken = 0;
        // 如果没选过元素，或者当前元素能被上一个选中元素整除，尝试选当前元素
        if (prevVal == 0 || nums[start] % prevVal == 0) {
            taken = 1 + dfs1(nums, start + 1, nums[start]);
        }
        // 不选当前元素，继续考察后面的元素
        int notTaken = dfs1(nums, start + 1, prevVal);

        // 取选和不选的最大值
        return Math.max(taken, notTaken);
    }

    public static void printStatus(int status) {
        for (int i = status; i > 0; i = (i - 1) & status) {
            System.out.println(Integer.toBinaryString(i));
        }
    }

    private static int set(int s, int j, int v) {
        return (s & ~(1 << j)) | (v << j);
    }

    public static long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], questions[i][0] + dp[Math.min(n, i + questions[i][1] + 1)]);
        }
        return dp[0];
    }

    public static long mostPointsDFS(int[][] questions) {
        long[] dp = new long[questions.length + 1];
        return mostPointsDFS(questions, 0, dp);
    }

    public static long mostPointsDFS(int[][] questions, int i, long[] dp) {
        if (i >= questions.length) {
            return 0;
        }
        if (dp[i] != 0) {
            return dp[i];
        }
        long p1 = mostPointsDFS(questions, i + 1, dp);
        long p2 = mostPointsDFS(questions, i + questions[i][1] + 1, dp) + questions[i][0];
        long ans = Math.max(p1, p2);
        dp[i] = ans;
        return ans;
    }

    public static int partitionArray(int[] nums, int k) {
        return partitionArrayDFS(nums, 0, k);
    }

    public static int partitionArrayDFS(int[] nums, int i, int k) {
        if (i >= nums.length) {
            return 0;
        }
        int p1 = partitionArrayDFS(nums, i + 1, k);
        int p2 = 0;
        if (nums[i + 1] - nums[i] <= k) {
            p2 = partitionArrayDFS(nums, i + 1, k) + 1;
        }
        return Math.max(p1, p2);
    }

    public static int minimumDeletions1(String word, int k) {
        int[] cnt = new int[26];
        for (char c : word.toCharArray()) {
            cnt[c - 'a']++;
        }
        Arrays.sort(cnt);
        int maxSave = 0;
        for (int i = 0; i < 26; i++) {
            int sum = 0;
            for (int j = i; j < 26; j++) {
                sum += Math.min(cnt[j], cnt[i] + k);
            }
            maxSave = Math.max(maxSave, sum);
        }
        return word.length() - maxSave;
    }

    public static int minimumDeletions2(String word, int k) {
        int[] cnt = new int[26];
        for (char c : word.toCharArray()) {
            cnt[c - 'a']++;
        }
        Arrays.sort(cnt);
        int minDelete = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            int deleteCount = 0;
            for (int j = 0; j < 26; j++) {
                if (cnt[j] == 0) continue;
                if (cnt[j] < cnt[i]) {
                    deleteCount += cnt[j];
                } else if (cnt[j] > cnt[i] + k) {
                    deleteCount += cnt[j] - (cnt[i] + k);
                }
            }
            minDelete = Math.min(minDelete, deleteCount);
        }
        return minDelete;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    // 跳过重复元素
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return ans;
    }

    public static int balancedStringSplit(String s) {
        int ans = 0, d = 0;
        for (char c : s.toCharArray()) {
            if (c == 'L') {
                d++;
            } else {
                d--;
            }
            if (d == 0) {
                ans++;
            }
        }
        return ans;
    }

    public static int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1, mid, ans = 0;
        if (nums[left] > target) {
            return 0;
        }
        if (nums[right] < target) {
            return n;
        }
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static int numSubarraysWithSum(int[] nums, int goal) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0, sum = 0; i < nums.length; i++) {
            sum += nums[i];
            ans += map.getOrDefault(sum - goal, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }

    public static int distributeCandies(int[] candyType) {
        Arrays.sort(candyType);
        int n = candyType.length, size = 1;
        for (int i = 1; i < n; i++) {
            if (candyType[i] != candyType[i - 1]) {
                size++;
            }
        }
        System.out.println(size);
        return Math.min(size, n / 2);
    }

    public static String[] divideString(String s, int k, char fill) {
        List<String> ans = new ArrayList<>();
        int n = s.length();
        for (int i = 0; i < n; i += k) {
            ans.add(s.substring(i, Math.min(i + k, n)));
        }
        String last = ans.getLast();
        if (last.length() < k) {
            last += String.valueOf(fill).repeat(k - last.length());
            ans.set(ans.size() - 1, last);
        }
        return ans.toArray(new String[0]);
    }

    public static List<Integer> findAnagrams(String str, String p) {
        List<Integer> ans = new ArrayList<>();
        int n = str.length(), k = p.length();
        if (n < k) {
            return ans;
        }
        int[] pCnt = new int[26];
        for (char c : p.toCharArray()) {
            pCnt[c - 'a']++;
        }
        int[] windows = new int[26];
        char[] s = str.toCharArray();
        for (int i = 0; i < k; i++) {
            windows[s[i] - 'a']++;
        }
        if (Arrays.equals(windows, pCnt)) {
            ans.add(0);
        }

        for (int i = k; i < n; i++) {
            windows[s[i] - 'a']++;
            windows[s[i - k] - 'a']--;
            if (Arrays.equals(pCnt, windows)) {
                ans.add(i - k + 1);
            }
        }
        return ans;
    }

    public static int distinctAverages(int[] nums) {
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            set.add(nums[i] + nums[j]);
        }
        return set.size();
    }

    public static int findMaxK(int[] nums) {
        int k = -1;
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            set.add(x);
        }
        for (int x : nums) {
            if (set.contains(x)) {
                k = Math.max(k, x);
            }
        }
        return k;
    }

    public static int numberOfSpecialChars(String word) {
        int ans = 0;
        int[] status = new int[26];
        for (char c : word.toCharArray()) {
            if (Character.isLowerCase(c)) {
                status[c - 'a'] |= 1;
            }
            if (Character.isUpperCase(c)) {
                status[c - 'A'] |= 1 << 1;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (status[i] == 3) {
                ans++;
            }
        }

        return ans;
    }

    public static boolean uniqueOccurrences(int[] arr) {
        int offest = 1000;
        int[] count = new int[2001];
        for (int x : arr) {
            count[x + offest]++;
        }
        for (int i : arr) {
            System.out.println(count[i + offest]);
        }
        return true;
    }

    public static List<Integer> luckyNumbers(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean isMin = true, isMax = true;
                for (int k = 0; k < n; k++) {
                    if (matrix[i][k] < matrix[i][j]) {
                        isMin = false;
                        break;
                    }
                }
                if (!isMin) {
                    continue;
                }
                for (int k = 0; k < m; k++) {
                    if (matrix[k][j] > matrix[i][j]) {
                        isMax = false;
                        break;
                    }
                }
                if (isMax) {
                    ans.add(matrix[i][j]);
                }
            }
        }
        return ans;
    }

    public List<Integer> luckyNumbers1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] minRow = new int[m];
        Arrays.fill(minRow, Integer.MAX_VALUE);
        int[] maxRow = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                minRow[i] = Math.min(minRow[i], matrix[i][j]);
                maxRow[j] = Math.max(maxRow[j], matrix[i][j]);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == minRow[i] && matrix[i][j] == maxRow[j]) {
                    ans.add(matrix[i][j]);
                }
            }
        }
        return ans;
    }

    public static boolean isPossibleToSplit(int[] nums) {
        int[] count = new int[101];

        for (int num : nums) {
            count[num]++;
            if (count[num] > 2) {
                return false;
            }
        }

        return true;
    }

    public static int countOdds(int low, int high) {
        int ans = 0;
        while (low <= high) {
            if (low % 2 == 1) {
                ans++;
            }
            low++;
        }
        return ans;
    }

    public static int countOdds1(int low, int high) {
        if ((low & 1) == 0 && (high & 1) == 0) {
            return (high - low) / 2;
        }
        return (high - low) / 2 + 1;
    }

    public static int[][] big;
    public static int[][] small;
    public static int MAXN3 = 1010;
    public static int[][] father1 = new int[MAXN3][2];

    public static void build(int[][] items1) {
        for (int i = 0; i < items1.length; i++) {
            father1[i][0] = items1[i][0];
            father1[i][1] = items1[i][1];
        }
    }

    public static int find1(int i) {
        if (i != father1[i][0]) {
            father1[i][0] = find(father1[i][0]);
        }
        return father1[i][0];
    }

    public static void union1() {
        for (int i = 0; i < small.length; i++) {
            int fx = find1(big[i][0]);
            int fy = find1(small[i][0]);
            if (fx != fy) {
                father1[fy][0] = fx;
                father1[fx][1] += fy;
            }
        }
        System.out.println(Arrays.deepToString(father1));
    }

    public static List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        int m = items1.length, n = items2.length;
        if (m < n) {
            big = items2;
            small = items1;
        }
        big = items1;
        small = items2;
        build(big);
        List<List<Integer>> ans = new ArrayList<>();
        union1();
        return ans;
    }

    public static int[] leftRightDifference1(int[] nums) {
        int n = nums.length;
        int[] left = new int[n], right = new int[n], ans = new int[n];
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] + nums[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] + nums[i + 1];
        }
        for (int i = 0; i < n; i++) {
            ans[i] = Math.abs(left[i] - right[i]);
        }
        return ans;
    }

    public static void left(int[] nums) {
        int n = nums.length;
        int[] left = new int[n], right = new int[n];
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] + nums[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] + nums[i + 1];
        }

    }

    public static int[] n;

    public static int minMaxGame(int[] nums) {
        n = nums;
        int l = 0, r = nums.length - 1;
        return mergeSort(l, r);
    }

    public static int mergeSort(int l, int r) {
        if (l == r) {
            return n[l];
        }
        int m = (l + r) / 2;
        mergeSort(l, m);
        mergeSort(m + 1, r);
        return merge(l, m, r);
    }

    public static int minMaxGame1(int[] nums) {
        int n = nums.length;
        while (n != 1) {
            int[] newNums = new int[n / 2];
            for (int i = 0; i < n / 2; i++) {
                if (i % 2 == 0) {
                    newNums[i] = Math.min(nums[2 * i], nums[2 * i + 1]);
                } else {
                    newNums[i] = Math.max(nums[2 * i], nums[2 * i + 1]);
                }
            }
            nums = newNums;
            n = n / 2;
        }
        return nums[0];
    }

    public static int countElements(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 1; i + 1 < nums.length; i++) {
            if (nums[i] > nums[i - 1] && nums[i] < nums[i + 1]) {
                count++;
            }
        }
        return count;
    }

    public static int pivotInteger(int n) {
        int t = n * (n + 1) / 2;
        int x = (int) Math.sqrt(t);
        if (x * x == t) {
            return x;
        }
        return -1;
    }

    public static List<String> stringMatching(String[] words) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i != j && words[j].contains(words[i])) {
                    ans.add(words[i]);
                    break;
                }
            }
        }
        return ans;
    }

    public static boolean threeConsecutiveOdds(int[] arr) {
        int n = arr.length;
        for (int i = 0; i <= n - 3; i++) {
            if (arr[i] % 2 == 1 && arr[i + 1] % 2 == 1 && arr[i + 2] % 2 == 1) {
                return true;
            }
        }
        return false;
    }

    public static int arithmeticTriplets(int[] nums, int diff) {
        int ans = 0, n = nums.length;
        for (int i = 0, j = 1, k = 2; i < n - 2 && j < n - 1 && k < n; i++) {
            j = Math.max(j, i + 1);
            while (j < n - 1 && nums[j] - nums[i] < diff) {
                j++;
            }
            if (j >= n - 1 || nums[j] - nums[i] > diff) {
                continue;
            }
            k = Math.max(k, j + 1);
            while (k < n && nums[k] - nums[j] < diff) {
                k++;
            }
            if (k < n && nums[k] - nums[j] == diff) {
                ans++;
            }
        }
        return ans;
    }

    public static boolean checkOnesSegment1(String s) {
        int number = Integer.parseInt(s, 2), n = s.length(), mask = (1 << n) - 1;
        while (number <= 2) {
            if ((number & mask) == mask) {
                return true;
            }
            number >>= 1;
            mask >>= 1;
        }
        return false;
    }

    public static boolean checkOnesSegment(String s) {
        return !s.contains("01");
    }

    public static boolean checkZeroOnes1(String s) {
        int max0 = 0, max1 = 0, cur = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                cur++;
            } else {
                if (s.charAt(i - 1) == '1') {
                    max1 = Math.max(max1, cur);
                } else {
                    max0 = Math.max(max0, cur);
                }
                cur = 1;
            }
        }
        if (s.charAt(s.length() - 1) == '1') {
            max1 = Math.max(max1, cur);
        } else {
            max0 = Math.max(max0, cur);
        }
        return max1 > max0;
    }

    public int longestMonotonicSubarray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int maxLen = 1, inc = 1, dec = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                inc += 1;
                dec = 1;
            } else if (nums[i] < nums[i - 1]) {
                dec += 1;
                inc = 1;
            } else {
                inc = 1;
                dec = 1;
            }
            maxLen = Math.max(maxLen, Math.max(inc, dec));
        }
        return maxLen;
    }

    public static int getCommon(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                return nums1[i];
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return -1;

    }

    public static int differenceOfSum(int[] nums) {
        int sum = 0, bitSum = 0;
        for (int num : nums) {
            sum += num;
            bitSum += sumBit(num);
        }
        return Math.abs(sum - bitSum);
    }

    public static int sumBit(int n) {
        int ans = 0;
        while (n != 0) {
            ans += n % 10;
            n = n / 10;
        }
        return ans;
    }

    public static boolean checkIfExist(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    if (arr[i] == 2 * arr[j] || 2 * arr[i] == arr[j]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String largestOddNumber(String num) {
        for (int i = num.length() - 1; i >= 0; i--) {
            char c = num.charAt(i);
            if ((c - '0') % 2 == 1) {  // 如果是奇数
                return num.substring(0, i + 1);
            }
        }
        return "";
    }

    public static boolean isPrefixString(String s, String[] words) {
        int i = 0, n = s.length();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (i < n && c == s.charAt(i)) {
                    i++;
                } else {
                    return false;
                }
            }
            if (i == n) {
                return true;
            }
        }
        return false;
    }

    public static int[] sortEvenOdd(int[] nums) {

        List<Integer> even = new ArrayList<>();
        List<Integer> odd = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                even.add(nums[i]);
            } else {
                odd.add(nums[i]);
            }
        }
        Collections.sort(even);
        odd.sort(Collections.reverseOrder());
        int evenIndex = 0, oddIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                nums[i] = even.get(evenIndex++);
            } else {
                nums[i] = odd.get(oddIndex++);
            }
        }
        return nums;
    }

    public static List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : nums1) {
            map.put(i, 1);
        }
        for (int i : nums2) {
            map.put(i, map.getOrDefault(i, 0) | 2);
        }
        for (int i : nums3) {
            map.put(i, map.getOrDefault(i, 0) | 4);
        }
        List<Integer> res = new ArrayList<Integer>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int k = entry.getKey(), v = entry.getValue();
            if ((v & (v - 1)) != 0) {
                res.add(k);
            }
        }
        return res;
    }

    public static void num(int num) {
        System.out.println(Integer.toBinaryString(num & (num - 1)));
        System.out.println(Integer.toBinaryString(num & -num));
    }

    public static void num2() {
        System.out.println(Integer.toBinaryString(2));
        System.out.println(Integer.toBinaryString(4));
        System.out.println(Integer.toBinaryString(8));
        System.out.println(Integer.toBinaryString(16));
    }

    public boolean hasTrailingZeros(int[] nums) {
        int even = 0;
        for (int num : nums) {
            if (num % 2 == 0) {
                even++;
            }
        }
        return even >= 2;
    }

    public static boolean digitCount(String num) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = num.length();
        for (int i = 0; i < n; i++) {
            int digit = num.charAt(i) - '0';
            map.put(digit, map.getOrDefault(digit, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            int val = num.charAt(i) - '0';
            if (map.getOrDefault(i, 0) != val) {
                return false;
            }
        }
        return true;
    }

    public static int[] sortByBits(int[] arr) {
        return Arrays.stream(arr)
                .boxed()
                .sorted(Comparator
                        .comparingInt(Integer::bitCount)
                        .thenComparingInt(x -> x))
                .mapToInt(i -> i)
                .toArray();
    }

    public static boolean areOccurrencesEqual(String s) {
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }
        int target = 0;
        for (int c : counts) {
            if (c == 0) {
                continue;
            }
            if (target == 0) {
                target = c;
            } else if (c != target) {
                return false;
            }
        }
        return true;
    }

    public static int[][] construct2DArray1(int[] original, int m, int n) {
        int[][] ans = new int[m][n];
        if (original.length != m * n) {
            return new int[0][];
        }
        for (int i = 0; i < original.length; i += n) {
            System.arraycopy(original, i, ans[i / n], 0, n);
        }

        return ans;
    }

    public static long zeroFilledSubarray(int[] nums) {
        long count = 0, res = 0;
        for (int num : nums) {
            if (num == 0) {
                count++;
                res += count;
            } else {
                count = 0;
            }
        }
        return res;
    }

    public static String freqAlphabets1(String str) {
        StringBuilder sb = new StringBuilder();
        char[] s = str.toCharArray();
        for (int i = 0; i < s.length; i++) {
            if (s[i] >= '1' && s[i] <= '9') {
                sb.append((char) ('a' + (s[i] - '0')));
            } else {
                if (i + 2 < s.length && i + 2 == '#') {
                    sb.append((char) ('j' + (s[i] - '0') * 10 + s[i + 1] - '0'));
                }
            }
        }
        return sb.toString();
    }

    public static String freqAlphabets(String str) {
        StringBuilder sb = new StringBuilder();
        char[] s = str.toCharArray();
        for (int i = 0; i < s.length; ) {
            if (i + 2 < s.length && s[i + 2] == '#') {
                sb.append((char) ('j' + (s[i] - '0') * 10 + s[i + 1] - '0' - 10));
                i += 3;
            } else {
                sb.append((char) ('a' + (s[i] - '0') - 1));
                i++;
            }
        }
        return sb.toString();
    }

    public static int pivotIndex(int[] nums) {
        int n = nums.length;
        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 0; i < n; i++) {
            if (sums[i + 1] - sums[0] == sums[n] - sums[i]) {
                return i;
            }

        }
        return -1;
    }

    public static List<Integer> findLonely(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> ans = new ArrayList<>();
        for (int num : nums) {
            if (map.get(num) == 1 && !map.containsKey(num + 1) && !map.containsKey(num - 1)) {
                ans.add(num);
            }
        }
        return ans;
    }

    public static int[] getNoZeroIntegers(int n) {
        int[] ans = new int[2];
        for (int i = 1; i < n; i++) {
            int other = n - i;
            if (!hasZero(i) && !hasZero(other)) {
                ans[0] = i;
                ans[1] = other;
                break;
            }
        }
        return ans;
    }

    public static int specialArray(int[] nums) {
        int ans = -1;
        for (int left = 0; left <= nums.length; left++) {
            if (f(nums, left, left)) {
                ans = left;
            }
        }
        return ans;
    }

    public static boolean f(int[] nums, int x, int k) {
        for (int num : nums) {
            if (num >= x) {
                k--;
            }
        }
        return k == 0;
    }

    private static boolean hasZero(int num) {
        while (num > 0) {
            if (num % 10 == 0) {
                return true;
            }
            num /= 10;
        }
        return false;
    }

    public static long[] sumOfThree(long num) {
        if (num % 3 == 0) {
            return new long[]{num / 3 - 1, num / 3, num / 3 + 1};
        }
        return new long[]{};
    }

    public static int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;
        int[][] vals = new int[n][2];
        for (int i = 0; i < n; i++) {
            vals[i][0] = i;
            vals[i][1] = nums[i];
        }
        Arrays.sort(vals, (a, b) -> Integer.compare(b[1], a[1]));
        Arrays.sort(vals, 0, k, Comparator.comparingInt(a -> a[0]));
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = vals[i][1];
        }
        return ans;
    }

    public static int findLeft1(int[] arr, int num) {
        int left = 0, right = arr.length - 1, m = 0, ans = -1;
        while (left <= right) {
            m = left + (right - left) >> 1;
            if (arr[m] >= num) {
                ans = m;
                right = m - 1;
            } else {
                left = m + 1;
            }
        }
        return ans;
    }

    public static int findRight(int[] arr, int num) {
        int left = 0, right = arr.length - 1, m = 0, ans = -1;
        while (left <= right) {
            m = left + (right - left) >> 1;
            if (arr[m] <= num) {
                ans = m;
                left = m + 1;
            } else {
                right = m - 1;
            }
        }
        return ans;
    }

    public static int MAXN4 = 100001;
    public static int[] stack = new int[MAXN];
    public static int r;

    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        r = 0;
        for (int i = 0, cur; i < n; i++) {
            while (r > 0 && temperatures[stack[r - 1]] < temperatures[i]) {
                cur = stack[--r];
                ans[cur] = i - cur;
            }
            stack[r++] = i;
        }
        return ans;
    }

    public static int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        int n = nums2.length;
        int[] stack = new int[n];
        r = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, cur; i < n; i++) {
            cur = nums2[i];
            while (r > 0 && cur > stack[stack[r - 1]]) {
                int prev = stack[--r];
                map.put(nums2[prev], nums2[i]);
            }
            stack[r++] = i;
        }
        while (r > 0) {
            int id = stack[--r];
            map.put(nums2[id], -1);
        }
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }

    public static void backtrack1(String s, int start, List<String> path, List<String> result) {
        if (path.size() == 4 && start == s.length()) {
            result.add(String.join(".", path));
            return;
        }
        if (path.size() >= 4) {
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if (start + i > s.length()) {
                break;
            }
            String sub = s.substring(start, start + i);
            if (sub.startsWith("0") && sub.length() > 1 || Integer.parseInt(sub) > 255) {
                continue;
            }
            path.add(sub);
            backtrack1(sub, start + i, path, result);
            path.removeLast();
        }
    }

    public static String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }

    public static int addedInteger(int[] nums1, int[] nums2) {
        return Arrays.stream(nums2).min().getAsInt() - Arrays.stream(nums1).min().getAsInt();
    }

    public static int findMagicIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i) {
                return i;
            }
        }
        return -1;
    }

    public static String[] uncommonFromSentences(String str1, String str2) {
        Map<String, Integer> map = new HashMap<>();
        insert(str1, map);
        insert(str2, map);
        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                ans.add(entry.getKey());
            }
        }
        return ans.toArray(new String[0]);
    }

    public static void insert(String s, Map<String, Integer> map) {
        String[] arr = s.split(" ");
        for (String str : arr) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
    }

    public static int largestInteger(int num) {
        String str = String.valueOf(num);
        int n = str.length();
        char[] s = str.toCharArray();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (((s[i] & 1) == (s[j] & 1)) && s[i] < s[j]) {
                    char temp = s[i];
                    s[i] = s[j];
                    s[j] = temp;
                }
            }
        }
        return Integer.parseInt(new String(s));
    }

    public static int[] replaceElements(int[] arr) {
        int n = arr.length, maxRight = -1;
        for (int i = n - 1; i >= 0; i--) {
            int cur = arr[i];
            arr[i] = maxRight;
            maxRight = Math.max(maxRight, cur);
        }
        return arr;
    }

    public static int MAXN5 = 100001;
    public static int[] father2 = new int[MAXN4];
    public static boolean[] serect = new boolean[MAXN4];

    public static void build(int n, int first) {
        for (int i = 0; i < n; i++) {
            father2[i] = i;
            serect[i] = false;
        }
        father2[first] = 0;
        serect[0] = true;
    }

    public static void union1(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
            father2[fx] = fy;
            serect[fy] |= serect[fx];
        }
    }

    public static List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        build(n, firstPerson);
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[2]));
        int m = meetings.length;
        for (int l = 0, r; l < m; ) {
            r = l;
            while (r + 1 < m && meetings[l][2] == meetings[r + 1][2]) {
                r++;
            }
            for (int i = l; i <= r; i++) {
                union(meetings[i][0], meetings[i][1]);
            }
            for (int i = l, a, b; i <= r; i++) {
                a = meetings[i][0];
                b = meetings[i][1];
                if (!serect[find(a)]) {
                    father2[a] = a;
                }
                if (!serect[find(b)]) {
                    father2[b] = b;
                }
            }
            l = r + 1;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (serect[find(i)]) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static final int MOD = 1000000007;

    public static int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] pow2 = new int[n];
        pow2[0] = 1;
        for (int i = 1; i < n; i++) {
            pow2[i] = (pow2[i - 1] << 1) % MOD;
        }
        int left = 0, right = n - 1, ans = 0;
        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                ans = (ans + pow2[right - left]) % MOD;
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }

    public static boolean isPalindrome(int x) {
        if (x < 0 || x % 10 == 0 && x != 0) {
            return false;
        }
        if (x == 0) {
            return true;
        }
        int revertedNum = 0;
        while (x > revertedNum) {
            revertedNum = revertedNum * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNum || x == revertedNum / 10;
    }

    public static int[] findErrorNums1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int[] ans = new int[2];
        for (int i = 1; i <= nums.length; i++) {
            if (map.getOrDefault(i, 0) == 2) {
                ans[0] = i;
            }
            if (!map.containsKey(i)) {
                ans[1] = i;
            }
        }
        return ans;
    }

    public static boolean judgeCircle(String moves) {
        int x = 0, y = 0;
        char[] mov = moves.toCharArray();
        for (char move : mov) {
            if (move == 'U') {
                y--;
            } else if (move == 'D') {
                y++;
            } else if (move == 'L') {
                x--;
            } else if (move == 'R') {
                x++;
            }
        }
        return x == 0 && y == 0;

    }

    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int maxLen = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int cur = num;
                int curLen = 1;
                while (set.contains(cur + 1)) {
                    cur++;
                    curLen++;
                }
                maxLen = Math.max(maxLen, curLen);
            }
        }
        return maxLen;
    }

    public static int maxProduct(int[] nums) {
        int n = nums.length;
        int curMax = nums[0], curMin = nums[0], ans = nums[0];

        for (int i = 1; i < n; i++) {
            int num = nums[i];
            int prevMax = curMax, prevMin = curMin;

            curMax = Math.max(num, Math.max(prevMax * num, prevMin * num));
            curMin = Math.min(num, Math.min(prevMax * num, prevMin * num));

            ans = Math.max(ans, curMax);
        }
        return ans;
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        f2(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    public static void f2(int[] nums, int i, List<Integer> path, List<List<Integer>> ans) {
        if (i == nums.length) {
            ans.add(new ArrayList<>(path));
        } else {
            path.add(nums[i]);
            f2(nums, i + 1, path, ans);
            path.removeLast();
            f2(nums, i + 1, path, ans);
        }
    }

    public static int findLHS1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int key : map.keySet()) {
            if (map.containsKey(key + 1)) {
                ans = Math.max(ans, map.get(key) + map.get(key + 1));
            }
        }
        return ans;
    }

        public static void main(String[] args) {
        int[][] items1 = {{1, 1}, {4, 5}, {3, 8}};
        int[][] items2 = {{3, 1}, {1, 5}};
        int[] nums = new int[]{1, 3, 2, 2, 5, 2, 3, 7};
        System.out.println(findLHS1(nums));
    }

}
