package algorithm.dp.knapsack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TargetNum {
    public static int f1(int[] nums, int target) {
        return f1(nums, target, 0, 0);
    }

    private static int f1(int[] nums, int target, int i, int sum) {
        if (i == nums.length) {
            return sum == target ? 1 : 0;
        }
        return f1(nums, target, i + 1, sum + nums[i]) + f1(nums, target, i + 1, sum - nums[i]);
    }
    //普通尝试，记忆化搜索
    public static int findTargetWays(int[] nums, int target) {
        Map<Integer, Map<Integer,Integer>> dp = new HashMap<>();
        return f2(nums, target, 0, 0,dp);
    }
    private static int f2(int[] nums, int target, int i, int sum, Map<Integer, Map<Integer,Integer>> dp) {
        if (i == nums.length) {
            return sum == target ? 1 : 0;
        }
        if (dp.containsKey(i)&&dp.get(i).containsKey(i)) {
            return dp.get(i).get(sum);
        }
        int ans = f2(nums, target, i + 1, sum + nums[i],dp) + f2(nums, target, i + 1, sum - nums[i],dp);
        dp.putIfAbsent(i, new HashMap<>());
        dp.get(i).put(sum, ans);
        return ans;
    }
    //平移技巧
    public static int findTargetWays3(int[] nums,int target) {
        int sum = Arrays.stream(nums).sum();
        if(target>sum||target<-sum){
            return 0;
        }
        int n = nums.length,m = 2*sum+1;
        int[][] dp = new int[n+1][m];
        //int ans = f2(nums, target, i + 1, sum + nums[i],dp) + f2(nums, target, i + 1, sum - nums[i],dp);
        dp[n][target+sum] = 1;
        for(int i=n-1;i>=0;i--){
            for (int j=-sum;j<=sum;j++){
                dp[i][j+sum] = dp[i+1][j+nums[i]+sum]+dp[i+1][j-nums[i]+sum];
                if (j+nums[i]+sum<m){
                    dp[i][j+sum] = dp[i+1][j+nums[i]+sum];
                }
                if (j-nums[i]+sum>=0){
                    dp[i][j+sum] += dp[i+1][j-nums[i]+sum];
                }
            }
        }
        return dp[0][sum];
    }
    public static int findTargetWays4(int[] nums,int target) {
        int sum = Arrays.stream(nums).sum();
        if(target>sum||((target&1)^(sum^1))==1){
        return 0;
        }
        return subSets(nums,(target+sum)>>1);
    }
    public static int subSets(int[] nums, int target) {
        if (target<0){
            return 0;
        }
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] += dp[j-num];
            }
        }
        return dp[target];
    }
    public static int minimumOperations(int[] nums) {
        boolean[] visited = new boolean[128];
        for (int i = nums.length-1; i >=0 ; i--) {
            if (visited[nums[i]]) {
                System.out.println(i/3+1);
                System.out.println(nums[i/3+1]);
                return i/3+1;
            }
            visited[nums[i]] = true;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,2,3,3,5,7};
        minimumOperations(nums);
    }
}
