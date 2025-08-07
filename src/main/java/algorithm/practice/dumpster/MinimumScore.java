package algorithm.practice.dumpster;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MinimumScore {
    public int maxSum(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums){
            if (num>0){
                set.add(num);
            }
        }
        if (set.isEmpty()){
            return Arrays.stream(nums).max().getAsInt();
        }
        return set.stream().mapToInt(Integer::intValue).sum();
    }
    public static int[] resultsArray(int[] nums, int k) {
        int n = nums.length,cnt = 0;
        int[] ans = new int[n-k+1];
        Arrays.fill(ans,-1);
        for (int i = 0; i < n;i++) {
            cnt = i == 0 || nums[i] - nums[i - 1] != 1 ? 1 : cnt + 1;
            if (cnt >= k) {
                ans[i - k + 1] = nums[i];
            }
        }
        return ans;
    }
    public static int minimumScore(int[] nums, int[][] edges) {
        return 0;
    }
    public static void main(String[] args) {

    }
}
