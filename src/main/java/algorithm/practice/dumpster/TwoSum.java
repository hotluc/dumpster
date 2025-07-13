package algorithm.practice.dumpster;

import java.util.*;

public class TwoSum {
    public static int[] twoSum(int[] numbers, int target) {
        int[] ans = new int[2];
        int n = numbers.length, left = 0, right = n - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                ans[0] = left + 1;
                ans[1] = right + 1;
                return ans;
            }
            if (sum < target) {
                left++;
            }
            if (sum > target) {
                right--;
            }
        }
        return ans;
    }

    public static int smallestValue(int n) {
        while (n > f(n)) {
            n = f(n);
        }
        return n;
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i * n < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int f(int n) {
        int ans = 0;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                while (n % i == 0) {
                    ans += i;
                    n /= i;
                }
            }
        }
        if (n > 1) {
            ans += n;
        }
        return ans;
    }

    public static int count = 0;

    public static int countOperations(int num1, int num2) {
        count++;
        if (num1 == 0 || num2 == 0) {
            return count;
        }
        int p1 = num1 - num2;
        int p2 = num2 - num1;
        System.out.println(p1 + " " + p2);
        return num1 >= num2 ? countOperations(p1, num2) : countOperations(num1, p2);
    }

    public static String decodeString(String s) {
        where = 0;
        return f(s.toCharArray(), 0);
    }

    public static String f(char[] s, int i) {
        StringBuilder ans = new StringBuilder();
        int cnt = 0;
        while (i < s.length && s[i] != ']') {
            if ((s[i] >= 'a' && s[i] <= 'z') || (s[i] >= 'A' && s[i] <= 'Z')) {
                ans.append(s[i++]);
            } else if (s[i] >= '0' && s[i] <= '9') {
                cnt = cnt * 10 + (s[i++] - '0');
            } else {
                ans.append(get(cnt, f(s, i + 1)));
                i = where + 1;
                cnt = 0;
            }
        }
        where = i;
        return ans.toString();
    }

    public static int where;

    public static String get(int cnt, String str) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < cnt; i++) {
            ans.append(str);
        }
        return ans.toString();
    }

    public static int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length, res = 0, t = 0;
        for (int i = 0; i < n; i++) {
            t += endTime[i] - startTime[i];
            int left = i <= k - 1 ? 0 : endTime[i - k];
            int right = i == n - 1 ? eventTime : startTime[i + 1];
            res = Math.max(res, right - left - t);
            if (i >= k - 1) {
                t -= endTime[i - k + 1] - startTime[i - k + 1];
            }
        }

        return res;
    }

    public static int myAtoi(String str) {
        boolean negative = false;
        int ans = 0;
        char[] s = str.toCharArray();
        for (int i = 0; i + 1 < s.length; i++) {
            if (s[i] == '-') {
                negative = true;
            }
            if (s[i + 1] == '-' || s[i + 1] == '+' || s[i] == '.') {
                break;
            }
            if (s[i] >= '0' && s[i] <= '9') {
                ans = ans * 10 + (s[i] - '0');
            }
        }
        return negative ? -ans : ans;
    }

    public static int secondsToRemoveOccurrences(String s) {
        int step = 0;
        if (!s.contains("01")) {
            return step;
        }
        return step + secondsToRemoveOccurrences(s.replaceAll("01", "10"));
    }

    public static int maxProduct(int[] nums) {
        int max1 = 0, max2 = 0;
        for (int num : nums) {
            if (num > max1) {
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max2 = num;
            }
        }
        return (max1 - 1) * (max2 - 1);
    }

    public static boolean checkPowersOfThree(int n) {
        int power = power3(n);
        int temp = (int) Math.pow(power, 3);
        while (n > 0) {
            if (n - temp >= 0) {
                n -= temp;
                System.out.println(n);
            }
            power--;
        }
        return power == 0;
    }

    public static int power3(int n) {
        int power = 0;
        while (Math.pow(3, power + 1) <= n) {
            power++;
        }
        return power;
    }

    public static String frequencySort(String str) {
        StringBuilder ans = new StringBuilder();
        Map<Character, Integer> map = new HashMap<>();
        char[] c = str.toCharArray();
        for (char value : c) {
            map.put(value, map.getOrDefault(value, 0) + 1);
        }
        List<Character> chars = new ArrayList<>(map.keySet());
        chars.sort((a, b) -> map.get(b) - map.get(a));
        for (char c1 : chars) {
            int count = map.get(c1);
            for (int i = 0; i < count; i++) {
                ans.append(c1);
            }
        }
        return ans.toString();
    }

    public static int[] resultsArray(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        for (int l = 0, r = 0; r < n; r++) {
            if (nums[r + 1] > nums[l]) {
                r++;
            }
            if (r - l >= k) {
                ans[l] = nums[r];
                l++;
            }
        }
        return ans;
    }

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
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

    public static int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int i = 0, j = 0, ans = 0;
        while (i < players.length && j < trainers.length) {
            if (players[i] <= trainers[j]) {
                ans++;
                i++;
                j++;
            } else {
                j++;
            }
        }
        System.out.println(Arrays.toString(players));
        System.out.println(Arrays.toString(trainers));
        return ans;
    }

    public static int maxRepOpt1(String text) {
        int[] cnt = new int[26];
        char[] texts = text.toCharArray();
        for (char c : text.toCharArray()) {
            cnt[c - 'a']++;
        }
        int ans = 1, n = text.length();
        for (int i = 0; i < n; i++) {
            int r = i;
            while (r < n && texts[i] == texts[r]) {
                r++;
            }
            int newR = r + 1;
            while (newR < n && texts[i] == texts[newR]) {
                newR++;
            }
            int len1 = r - i, len2 = newR - r - 1;
            if (cnt[texts[i] - 'a'] > len1 + len2) {
                ans = Math.max(ans, len1 + len2 + 1);
            } else {
                ans = Math.max(ans, len1 + len2);
            }
        }
        return ans;
    }

    public static int countDays(int days, int[][] meetings) {
        return 0;
    }
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int ans = 0;
        for (int i = 0,sum=0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                ans += map.get(sum - k);
            }
                map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] players = {1, 2, 3};
        int[] trainers = {10};
        int target = 3;
        System.out.println(subarraySum(players, target));
    }

}
