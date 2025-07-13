package algorithm.dp.dimendsional.one;

import java.util.Arrays;

// 最低票价
// 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行
// 在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出
// 每一项是一个从 1 到 365 的整数
// 火车票有 三种不同的销售方式
// 一张 为期1天 的通行证售价为 costs[0] 美元
// 一张 为期7天 的通行证售价为 costs[1] 美元
// 一张 为期30天 的通行证售价为 costs[2] 美元
// 通行证允许数天无限制的旅行
// 例如，如果我们在第 2 天获得一张 为期 7 天 的通行证
// 那么我们可以连着旅行 7 天(第2~8天)
// 返回 你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费
// 测试链接 : https://leetcode.cn/problems/minimum-cost-for-tickets/
public class Code02_MinimumCostForTickets {
    // 无论提交什么方法都带着这个数组      0  1  2
    public static int[] durations = { 1, 7, 30 };
    //暴力尝试
    public static int minCostTickets1(int[] days, int[] costs) {
        return dfs1(days,costs,0);
    }
    //days[i..... 最少花费是多
    private static int dfs1(int[] days, int[] costs, int i) {
        if (i == days.length) {
            return 0;
        }
        // i下标 : 第days[i]天，有一场旅行
        // i.... 最少花费是多少
        int ans = Integer.MAX_VALUE;
        for (int k = 0,j = i; k < 3; k++) {
            while (j<days.length && days[i] +durations[k] >days[j]) {
                j++;
            }
            ans = Math.min(ans, costs[k]+dfs1(days,costs,j));
        }
        return ans;
    }
    // 暴力尝试改记忆化搜索
    // 从顶到底的动态规划
    public static int minCostTickets2(int[] days, int[] costs) {
        int[] dp = new int[days.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        return dfs2(days,costs,0,dp);
    }
    private static int dfs2(int[] days, int[] costs, int i, int[] dp) {
        if (i == days.length) {
            return 0;
        }
        if (dp[i] != Integer.MAX_VALUE) {
            return dp[i];
        }
        // i下标 : 第days[i]天，有一场旅行
        // i.... 最少花费是多少
        int ans = Integer.MAX_VALUE;
        for (int k = 0,j = i; k < 3; k++) {
            while (j<days.length && days[i] +durations[k] >days[j]) {
                j++;
            }
            ans = Math.min(ans, costs[k]+dfs1(days,costs,j));
        }
        dp[i] = ans;
        return ans;
    }
    // 严格位置依赖的动态规划
    // 从底到顶的动态规划
    public static int MAXN = 366;
    public static int[] dp = new int[MAXN];
    public static int minCostTickets3(int[] days, int[] costs) {
        int n = days.length;
        Arrays.fill(dp, 0,n+1,Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = n-1; i >= 0; i--) {
            for (int k = 0,j = i; k < 3; k++) {
                while (j<days.length && days[i] +durations[k] >days[j]) {
                    j++;
                }
                dp[i] = Math.min(dp[i], costs[k]+dp[j]);
            }
        }
        return dp[0];
    }
    public static int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        int[] dp = new int[n + 1];
        dp[1] = nums[0];
        dp[2] = Math.max(nums[0], nums[1]);

        for (int i = 3; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i-1]);
        }
        System.out.println(dp[n]);
        return dp[n];
    }

    public static void main(String[] args) {
        rob(new int[]{2,7,9,3,1});
    }
}
