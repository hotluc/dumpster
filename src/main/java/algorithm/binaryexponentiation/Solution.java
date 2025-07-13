package algorithm.binaryexponentiation;

import java.util.*;

/**
 * 快速幂，二进制取幂（Binary Exponentiation，也称平方法），是一个在 O(log n) 的时间内计算 a^n 的小技巧，而暴力的计算需要 O(n) 的时间。
 */
public class Solution {
    public static int fib(int n) {
        if (n < 2) {
            return n;
        }
        int[][] matrix = {{1, 1}, {1, 0}};
        int[][] res = power(matrix, n - 1);
        return res[0][0];
    }

    public static int power(int a, int b) {
        int ans = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                ans *= a;
            }
            a *= a;
            b >>= 1;
        }
        return ans;
    }

    public static int[][] power(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    public static int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }
    public static double power(double a, long b) {
        double ans = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                ans *= a;
            }
            a *= a;
            b >>= 1;
        }
        return ans;
    }
    public double myPow(double x, int n) {
        long N = n;
        return N>= 0 ? power(x, N) : 1.0 / power(x, -N);
    }
    public static void main(String[] args) {
        System.out.println(power(3, 18)); // 1024

        System.out.println(Math.pow(3, 18));
        for (int i = 1; i <= 9; i++) {
            System.out.println("铺满 2*" + i + " 层的砖块有 " + f(i, 0) + " 种方法");
        }
        int[] nums = new int[]{1, 2, 4, 8};
        System.out.println(largestDivisibleSubset(nums));

    }

    public static int getCount1(int n) {
        int count = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n >>= 1;
        }
        return count;
    }

    public static int f(int n, int h) {
        if (n == 0) {
            return h == 0 ? 1 : 0;
        }
        if (n == 1) {
            return 1;
        }
        if (h == 1) {
            return f(n - 1, 0) + f(n - 1, 1);
        } else {
            return f(n - 1, 0) + f(n - 2, 0) + 2 * f(n - 2, 1);
        }
    }

    public static int MOD = 1337;

    public static int superPow(int a, int[] b) {
        return dfs(a, b, b.length - 1);
    }

    private static int dfs(int a, int[] b, int i) {
        if (i == -1) {
            return 1;
        }
        return power(dfs(a, b, i - 1), 10) * power(a, b[i]) % MOD;
    }

    private static int qpow(int a, int b) {
        int ans = 1;
        a %= MOD;
        while (b > 0) {
            if ((b & 1) == 1) {
                ans *= a % MOD;
            }
            a *= a % MOD;
            b >>= 1;
        }
        return ans;
    }

    public static int eraseOverlapIntervals(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        int time = 0;
        for (int[] c : courses) {
            if (time + c[0] <= c[1]) {
                heap.add(c[0]);
                time += c[0];
            } else {
                if (!heap.isEmpty() && heap.peek() > c[0]) {
                    time += c[0] - heap.poll();
                    heap.add(c[0]);
                }
            }
        }

        return heap.size();
    }

    public static List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int start = 0, end = nums.length;
        while (start < end) {
            int low = start;
            start++;
            while (start < end && nums[start] == nums[start - 1] + 1) {
                start++;
            }
            int high = start - 1;
            StringBuilder builder = new StringBuilder(Integer.toString(nums[low]));
            if (low < high) {
                builder.append("->");
                builder.append(nums[high]);
            }
            res.add(builder.toString());
        }
        return res;
    }

    public static int largestDivisibleSubset(int[] nums) {
        int n = nums.length, ans = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        if (ans > 0) {
            for (int len = nums.length; len > 0; len--) {

            }
        }
        return ans;
    }

}
