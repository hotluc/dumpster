package algorithm.dp.getSolution;

import java.util.*;

public class Solution {
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int n = req_skills.length;
        int m =  people.size();
        Map<String,Integer> map = new HashMap<>();
        int cnt = 0;
        for (String skill : req_skills) {
            map.put(skill, cnt++);
        }
        int[] arr = new int[m];
        for (int i = 0,status; i < m; i++) {
            status = 0;
            for (String skill : people.get(i)) {
                if (map.containsKey(skill)) {
                    status |= 1<<map.get(skill);
                }
            }
            arr[i] = status;
        }
        int[][] dp = new int[m][1<<n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i],-1);
        }
        int size = f(arr,m,n,0,0,dp);
        int[] ans = new int[size];
        for (int j = 0,i = 0,s = 0; s != (1<<n)-1; i++) {
            if (i+1==m||dp[i][s]!=dp[i+1][s]){
                ans[j++] = i;
                s|=arr[i];
            }
        }
        return ans;
    }
    private static int f(int[] arr,int m,int n,int i,int s,int[][] dp){
        if (s == (1<<n)-1){
            return 0;
        }
        if (i == m){
            return Integer.MAX_VALUE;
        }
        if (dp[i][s] != -1){
            return dp[i][s];
        }
        int p1 = f(arr,m,n,i+1,s,dp);
        int p2 = Integer.MAX_VALUE;
        int next2 = f(arr,m,n,i+1,s|arr[i],dp);
        if (next2 != Integer.MAX_VALUE){
            p2 = 1+next2;
        }
        int ans = Math.min(p1,p2);
        dp[i][s] = ans;
        return ans;
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for (String str:strs){
            char[] chars = str.toCharArray();
            Arrays.sort(strs);
            String strr = str;
            List<String> list = map.getOrDefault(strr,new ArrayList<>());
            map.put(strr,list);
        }
        return new ArrayList<>(map.values());
    }
    /*public int longestConsecutive(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i+1]>nums[i]){
                map.putIfAbsent()
            }
        }
    }*/
    public static int singleNumber(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum ^= num;
        }
        return sum;
    }
    public static int lengthOfLTS(int[] nums) {
        int n = nums.length,ans = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            ans = Math.max(ans,dp[i]);
        }
        return ans;
    }
    public static List<Integer> lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return new ArrayList<>();

        int[] dp = new int[n];      // 记录以 nums[i] 结尾的 LIS 长度
        int[] pre = new int[n];     // 记录前驱索引
        Arrays.fill(pre, -1);       // 初始化为 -1，表示无前驱

        int maxLength = 0;         // 最长 LIS 长度
        int maxIndex = -1;         // 最长 LIS 的末尾索引

        for (int i = 0; i < n; i++) {
            dp[i] = 1;  // 初始长度为1（仅自身）
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    pre[i] = j;    // 记录前驱位置
                }
            }
            // 更新全局最大值及其索引
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                maxIndex = i;
            }
        }

        // 通过前驱数组回溯构造 LIS
        List<Integer> lis = new ArrayList<>();
        while (maxIndex != -1) {
            lis.add(nums[maxIndex]);
            maxIndex = pre[maxIndex];
        }
        Collections.reverse(lis);  // 反转得到正序
        return lis;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,4,4,5,7,7,6,9,10};
        System.out.println(lengthOfLIS(nums));
    }
}
