package algorithm.maxsubarray;

public class MaxmumSubArray {
    public static int maxSubArray(int[] nums){
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int ans = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
    //空间压缩
    public static int maxSubArray1(int[] nums){
        int n = nums.length;
        int ans = nums[0];
        for (int i = 1,pre = nums[0]; i < n; i++) {
            pre = Math.max(nums[i], pre + nums[i]);
            ans = Math.max(ans, pre);
        }
        return ans;
    }
}
