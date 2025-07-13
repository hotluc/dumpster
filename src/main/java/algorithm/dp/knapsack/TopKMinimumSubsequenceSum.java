package algorithm.dp.knapsack;

import java.util.ArrayList;

public class TopKMinimumSubsequenceSum {
    public static int[] topKSums(int[] nums, int k) {
        ArrayList<Integer> allSubsequences = new ArrayList<>();
        dfs(nums, 0, 0, allSubsequences);
        allSubsequences.sort(Integer::compareTo);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = allSubsequences.get(i);
        }
        return null;
    }
    public static void dfs(int[] nums, int i, int sum, ArrayList<Integer> allSubsequences) {
        if (i == nums.length) {
            allSubsequences.add(sum);
        } else {
            dfs(nums, i + 1, sum, allSubsequences);
            dfs(nums, i + 1, sum + nums[i], allSubsequences);
        }
    }
}
