package algorithm.dp.optimize.enumeration;

import java.util.Arrays;
import java.util.Comparator;

// 规划兼职工作
// 你打算利用空闲时间来做兼职工作赚些零花钱，这里有n份兼职工作
// 每份工作预计从startTime[i]开始、endTime[i]结束，报酬为profit[i]
// 返回可以获得的最大报酬
// 注意，时间上出现重叠的 2 份工作不能同时进行
// 如果你选择的工作在时间X结束，那么你可以立刻进行在时间X开始的下一份工作
// 测试链接 : https://leetcode.cn/problems/maximum-profit-in-job-scheduling/
public class Code01_MaximumProfitInJobScheduling {
    public static int MAX = 50001;
    public static int[][] jobs = new int[MAX][3];
    public static int[] dp = new int[MAX];
    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        for (int i = 0; i < n; i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }
        //工作按结束时间排序
        Arrays.sort(jobs, 0, n, Comparator.comparingInt(a -> a[1]));
        dp[0] = jobs[0][2];
        for (int i = 1,start; i < n; i++) {
            start = jobs[i][0];
            dp[i] = jobs[i][2];
            if (jobs[0][1] <= start) {
                dp[i] += dp[find(i - 1, start)];
            }
            dp[i] = Math.max(dp[i], dp[i - 1]);
        }
        return dp[n - 1];
    }
    //job[0..i-1]范围中，结束时间<=start的最大索引
    public static int find(int i, int start) {
        int ans = 0;
        int l = 0;
        int r = i;
        while (l <= r) {
            int m = (l + r) / 2;
            if (jobs[m][1] <= start) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;

    }
}
