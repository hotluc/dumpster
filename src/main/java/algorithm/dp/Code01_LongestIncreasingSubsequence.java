package algorithm.dp;

public class Code01_LongestIncreasingSubsequence {
    public static int maxAscendingSum(int[] nums) {
        int max = nums[0],current = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]) {
                current += nums[i];
            }
            else {
                current = nums[i];
            }
            max = Math.max(max, current);
        }
        return max;
    }
    public static String maximumOddBinaryNumber(String s) {
        int cnt = (int) s.chars().filter(c->c=='1').count();
        return "1".repeat(cnt-1)+"0".repeat(s.length()-cnt)+"1";
    }
    public static boolean isHappy(int n) {
        int len = 1;
        int offset = 1;
        int tmp = n / 10;
        while (tmp > 0) {
            len++;
            offset *= 10;
            tmp /= 10;
        }
        int first = n/offset;
        System.out.println(len);
        System.out.println(first);
        return false;
    }
    public static int countSegments(String s) {
        int segmentCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((i == 0 || s.charAt(i - 1) == ' ') && s.charAt(i) != ' ') {
                segmentCount++;
            }
        }
        return segmentCount;
    }
    public static void main(String[] args) {
        System.out.println(climbStairs(3));
    }
    public static int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];
        dp[0] = nums[0];//i==1
        dp[1] = Math.max(nums[0], nums[1]);//i==2
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[n];
    }
}
