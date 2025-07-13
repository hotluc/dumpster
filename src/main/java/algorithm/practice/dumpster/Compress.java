package algorithm.practice.dumpster;

import java.util.*;

public class Compress {

    public int compress(char[] chars) {
        int len = chars.length;
        int write = 0;

        for (int l = 0, r = 0; l < len; l = r + 1) {
            r = l;
            while (r + 1 < len && chars[r + 1] == chars[r]) {
                r++;
            }

            // 写入当前字符
            chars[write++] = chars[l];

            int count = r - l + 1;
            if (count > 1) {
                // 写入数量的每一位字符（如 "12" -> '1', '2'）
                for (char c : String.valueOf(count).toCharArray()) {
                    chars[write++] = c;
                }
            }

            // 下一段从 r + 1 开始，已经在 for 循环体中控制了
        }

        return write;
    }

    public int compress1(char[] chars) {
        int len = chars.length, write = 0;
        for (int l = 0, r; l < len; ) {
            r = l;
            while (r + 1 < chars.length && chars[r + 1] == chars[r]) {
                r++;
            }
            chars[write++] = chars[l];
            int count = r - l + 1;
            if (count > 1) {
                for (char c : String.valueOf(count).toCharArray()) {
                    chars[write++] = c;
                }
            }
            l = r + 1;
        }
        return write;
    }

    public static int maximum69Number(int num) {
        String str = String.valueOf(num);
        char[] s = str.toCharArray();
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '6') {
                s[i] = '9';
                break;
            }
        }
        return Integer.parseInt(new String(s));

    }

    public static int minFlips(String s) {
        String s1 = "10000";

        return 0;

    }

    public static String thousandSeparator(int n) {
        String s = String.valueOf(n);
        if (s.length() <= 3) {
            return s;
        }
        /*StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length()-3; i+=3) {
            sb.insert(i, ',');
        }*/
        StringBuilder sb = new StringBuilder(s);
        for (int i = s.length() - 3; i > 0; i -= 3) {
            sb.insert(i, '.');
        }
        return sb.toString();
    }

    public static boolean checkInclusion(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] count1 = new int[26], count2 = new int[26];
        for (int i = 0; i < n; i++) {
            count1[s1.charAt(i) - 'a']++;
            count2[s2.charAt(i) - 'a']++;
        }
        if (Arrays.equals(count1, count2)) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            ++count2[s2.charAt(i) - 'a'];
            --count2[s2.charAt(i - n) - 'a'];
            if (Arrays.equals(count1, count2)) {
                return true;
            }
        }

        return false;
    }

    public static int[] shortestSeq(int[] big, int[] small) {
        int n = big.length, m = small.length;
        if (n < m) {
            return new int[]{};
        }
        int[] cnt = new int[1000000];
        for (int i : small) {
            cnt[i]--;
        }
        int debt = small.length, len = Integer.MAX_VALUE, start = 0, end = 0;
        for (int l = 0, r = 0; r < n; r++) {
            if (cnt[big[r]]++ < 0) {
                debt--;
            }
            if (debt == 0) {
                while (cnt[big[l]] > 0) {
                    cnt[big[l++]]--;
                }
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    start = l;
                    end = r;
                }
            }
        }
        System.out.println(len);
        return len == Integer.MAX_VALUE ? new int[0] : new int[]{start, end};
    }

    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        return 0;
    }

    public static int jump(int[] nums) {
        int n = nums.length, cur = 0, next = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            if (cur < i) {
                ans++;
                cur = next;
            }
            next = Math.max(next, i + nums[i]);
        }
        return ans;
    }

    public static long smallestNumber(long num) {
        if (num == 0) {
            return 0;
        }
        boolean isNegative = num < 0;
        num = Math.abs(num);
        List<Integer> digits = new ArrayList<>();
        while (num > 0) {
            digits.add((int) (num % 10));
            num /= 10;
        }
        Collections.sort(digits);
        if (isNegative) {
            Collections.reverse(digits);
        } else {
            if (digits.getFirst() == 0) {
                for (int i = 1; i < digits.size(); i++) {
                    if (digits.get(i) != 0) {
                        Collections.swap(digits, 0, i);
                        break;
                    }
                }
            }
        }
        long res = 0;
        for (int digit : digits) {
            res = res * 10 + digit;
        }
        return isNegative ? -res : res;
    }

    public static int MAV = 100001;
    public static int[] factor = new int[MAV];
    public static int MAXN = 20001;
    public static int[] father = new int[MAXN];
    public static int[] size = new int[MAXN];
    public static int n;

    public static void build() {
        for (int i = 0; i < n; i++) {
            father[i] = i;
            size[i] = 1;
        }
        Arrays.fill(factor, -1);

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
            father[fx] = fy;
            size[fy] += size[fx];
        }
    }

    public static int maxSize() {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, size[i]);
        }
        return ans;
    }

    public static int largestComponentSize(int[] nums) {
        n = nums.length;
        build();
        for (int i = 0, x; i < n; i++) {
            x = nums[i];
            for (int j = 2; j * j <= x; j++) {
                if (x % j == 0) {
                    if (factor[j] == -1) {
                        factor[j] = i;
                    } else {
                        union(factor[j], i);
                    }
                }
                while (x % j == 0) {
                    x /= j;
                }
            }
            if (x > 1) {
                if (factor[x] == -1) {
                    factor[x] = i;
                } else {
                    union(factor[x], i);
                }
            }
        }
        return maxSize();
    }
    public static int commonFactors(int a, int b) {
        int ans = 0;
        for (int i = 1; i <= b; i++) {
            if (a % i == 0&&b % i == 0) {
                ans++;
            }
        }
        return ans;
    }
    public static int countEven(int num) {
        int ans = 0;
        for (int i = 2; i <= num; i++) {
            if (is(i)){
                ans++;
            }
        }
        return ans;
    }
    public static boolean is(int n){
        int sum = 0;
        while (n>0){
            sum += n%10;
            n = n/10;
        }
        return sum % 2 == 0;
    }
    class Solution {
        public boolean isAdditiveNumber(String num) {
            int n = num.length();
            for (int secondStart = 1; secondStart < n - 1; ++secondStart) {
                if (num.charAt(0) == '0' && secondStart != 1) {
                    break;
                }
                for (int secondEnd = secondStart; secondEnd < n - 1; ++secondEnd) {
                    if (num.charAt(secondStart) == '0' && secondStart != secondEnd) {
                        break;
                    }
                    if (valid(secondStart, secondEnd, num)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean valid(int secondStart, int secondEnd, String num) {
            int n = num.length();
            int firstStart = 0, firstEnd = secondStart - 1;
            while (secondEnd <= n - 1) {
                String third = stringAdd(num, firstStart, firstEnd, secondStart, secondEnd);
                int thirdStart = secondEnd + 1;
                int thirdEnd = secondEnd + third.length();
                if (thirdEnd >= n || !num.substring(thirdStart, thirdEnd + 1).equals(third)) {
                    break;
                }
                if (thirdEnd == n - 1) {
                    return true;
                }
                firstStart = secondStart;
                firstEnd = secondEnd;
                secondStart = thirdStart;
                secondEnd = thirdEnd;
            }
            return false;
        }

        public String stringAdd(String s, int firstStart, int firstEnd, int secondStart, int secondEnd) {
            StringBuffer third = new StringBuffer();
            int carry = 0, cur = 0;
            while (firstEnd >= firstStart || secondEnd >= secondStart || carry != 0) {
                cur = carry;
                if (firstEnd >= firstStart) {
                    cur += s.charAt(firstEnd) - '0';
                    --firstEnd;
                }
                if (secondEnd >= secondStart) {
                    cur += s.charAt(secondEnd) - '0';
                    --secondEnd;
                }
                carry = cur / 10;
                cur %= 10;
                third.append((char) (cur + '0'));
            }
            third.reverse();
            return third.toString();
        }
    }
    public static void main(String[] args) {
        int[] big = {521704, 897261, 279103, 381783, 668374, 934085, 254258, 726184, 496153, 804155};
        int[] small = {897261, 9385, 381783, 496153};
        System.out.println(countEven(30));
    }
}
