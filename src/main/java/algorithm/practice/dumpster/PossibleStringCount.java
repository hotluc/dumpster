package algorithm.practice.dumpster;

import java.util.*;

public class PossibleStringCount {
    public static int stringCount(String word) {
        int ans = 1, len = word.length();
        char[] w = word.toCharArray();
        for (int l = 0, r; l < len; ) {
            r = l;
            while (r + 1 < len && w[r + 1] == w[r]) {
                r++;
            }
            int count = r - l + 1; // 当前区间长度
            if (count >= 2) {
                System.out.println("区间字符: " + w[l] + "，长度: " + count);
                ans += count-1;
            }
            l = r + 1;
        }
        return ans;
    }
    public static int maximumCostSubstring(String s, String chars, int[] vals) {
        int[] mapping = new int[26];
        for (int i = 0; i < 26; i++) {
            mapping[i] = i+1;
        }
        for (int i = 0; i < vals.length; i++) {
            mapping[chars.charAt(i)-'a']=vals[i];
        }
        int ans = 0,f=0;
        for (char c : s.toCharArray()) {
            f = Math.max(f,0)+mapping[c-'a'];
            ans = Math.max(ans,f);
        }
        return ans;
    }
    public static int monkeyMove(int n) {
        int mod = 1000000007;
        return (pow(2,n,mod)-2+mod)%mod;
    }
    public static int pow(long a,int b,int mod){
        long ans = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                ans = (ans * a )% mod;
            }
            a = (a * a )% mod;
            b>>=1;
        }
        return (int)ans;
    }
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            for (int num2 : nums2) {
                map.put(num + num2, map.getOrDefault(num + num2, 0) + 1);
            }
        }
        int ans = 0;
        for (int num : nums3) {
            for (int num2 : nums4) {
                ans += map.getOrDefault(-num - num2, 0);
            }
        }
        return ans;
    }
    public static int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i-1][0]+s1.codePointAt(i-1);
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j-1]+s2.codePointAt(j-1);
        }
        for (int i = 1; i <= m; i++) {
            int code1 = s1.codePointAt(i-1);
            for (int j = 1; j <= n; j++) {
                int code2 = s2.codePointAt(j-1);
                if (code1 == code2) {
                    dp[i][j] = dp[i-1][j-1];
                }
                else {
                    dp[i][j] = Math.min(dp[i-1][j]+code1, dp[i][j-1]+code2);
                }
            }
        }
        return dp[m][n];
    }
    public static int maximumGroups(int[] grades) {
        Arrays.sort(grades);
        System.out.println(Arrays.toString(grades));
        return 0;
    }
    public static String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = nums[i].charAt(i);
            sb.append(c=='0'?'1':'0');
        }
        return sb.toString();
    }
    public static void print(int num){
        System.out.println(Integer.toBinaryString(num));
        System.out.println(Integer.toBinaryString(num^-num));
    }
    public static int MAXN = 1000000;
    public static int[]  father = new int[MAXN];
    public static void build(int n){
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }
    public static int find(int x){
        if (x != father[x]) {
            father[x] = find(father[x]);
        }
        return father[x];
    }
    public static void union(int x, int y){
        int fx = find(x), fy = find(y);
        if (fx != fy) {
            father[fx] = fy;
        }
    }
    public static long countPairs(int n, int[][] edges) {
        build(n);
        for (int[] edge : edges) {
            union(edge[0], edge[1]);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = find(i);
            map.put(i, map.getOrDefault(root, 0) + 1);
        }
        long total = (long) n *(n-1)/2;
        long reachable = 0;
        for (int size : map.values()) {
            reachable += (long) size * (size - 1) / 2;
        }
        return total-reachable;
    }
    public static int countHillValley(int[] nums) {
       int ans = 0,pre = nums[0];
       for (int i = 1; i < nums.length-1; i++) {
           int cur = nums[i],next = nums[i+1];
           if (cur == next) {
               continue;
           }
           if (pre!=cur&&(pre<cur)==(cur>next)) {
               ans++;
           }
           pre = cur;
       }
       return ans;
    }
    public static int minimumSubarrayLength(int[] nums, int k) {
        int n = nums.length,ans = Integer.MAX_VALUE;
        int [] bits = new int[30];
        for (int left=0,right = 0; right < n; right++) {
            for (int i = 0; i < 30; i++) {
                bits[i] += (nums[right] >> i) & 1;
            }
            while (left<=right&&calc(bits)>=k) {
                ans = Math.min(ans, right-left+1);
                for (int i = 0; i < 30; i++) {
                    bits[i] -= (nums[left] >> i) & 1;
                }
                left++;
            }
        }
        return ans==Integer.MAX_VALUE?-1:ans;
    }
    private static int calc(int[] bits){
        int ans = 0;
        for (int i = 0; i < bits.length; i++) {
            if (bits[i] > 0){
                ans |= 1<<i;
            }
        }
        return ans;
    }
    public static int maxXor , count;
    public static int countMaxOrSubsets(int[] nums) {
        maxXor = 0;
        count = 0;
        dfs(nums, 0, 0);
        return count;
    }

    public static void dfs(int[] nums, int index, int currentXor) {
        if (index == nums.length) {
            if (currentXor == 0) return;

            if (currentXor > maxXor) {
                maxXor = currentXor;
                count = 1;
            } else if (currentXor == maxXor) {
                count++;
            }
            return;
        }

        dfs(nums, index + 1, currentXor);

        dfs(nums, index + 1, currentXor | nums[index]);
    }
    public static int reverseDegree(String str) {
        int ans = 0;
        char[] s = str.toCharArray();
        for (int i = 0; i < s.length; i++) {
            ans += (26-(s[i] - 'a'))*(i+1);
        }
        return ans;
    }
    public static int maxDistance(List<List<Integer>> arrays) {
        int res = 0;
        int minVal = arrays.getFirst().getFirst();
        int maxVal = arrays.getFirst().getLast();

        for (int i = 1; i < arrays.size(); i++) {
            int currMin = arrays.get(i).getFirst();
            int currMax = arrays.get(i).getLast();
            res = Math.max(res, Math.max(Math.abs(maxVal - currMin), Math.abs(currMax - minVal)));

            minVal = Math.min(minVal, currMin);
            maxVal = Math.max(maxVal, currMax);
        }
        return res;
    }
    public static int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        Arrays.sort(envelopes,(a,b)->a[0]!=b[0]?a[0]-b[0]:b[1]-a[1]);
        int[] ends = new int[n];
        int len = 0;
        for (int i = 0,find,num; i < n; i++) {
            num = envelopes[i][1];
            find = bs(ends,len,num);
            if (find == -1) {
                ends[len++] = num;
            }
            else {
                ends[find] = num;
            }
        }
        return len;
    }
    public static int bs(int[] ends,int len,int num) {
        int l = 0,r = len-1,m,ans = -1;
        while (l<=r) {
            m = (l+r)/2;
            if (ends[m] >= num) {
                ans = m;
                r = m-1;
            }
            else {
                l = m+1;
            }
        }
        return ans;
    }
    public static int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        return ans;
    }
    public static int longestSubarray(int[] nums) {
        int ans = 0,max = 0,cnt=0;
        for (int num : nums) {
            if (num > max) {
                max = num;
                cnt = 1;
                ans = 1;
            }
            else if (num == max) {
                cnt++;
                ans=Math.max(ans,cnt);
            }
            else {
                cnt=0;
            }
        }
        return ans;

    }
    public static String minWindow(String str, String target) {
        char[] s = str.toCharArray(),t=target.toCharArray();
        int[] cnt = new int[256];
        for (char c : t) {
            cnt[c-'a']--;
        }
        int ans= Integer.MAX_VALUE,start = 0,debt = target.length();
        for (int l=0,r = 0; r < s.length; r++) {
            if (cnt[s[r]]++<0) {
                debt--;
            }
            if (debt==0) {
                while (cnt[s[l]]>0){
                  cnt[s[l++]]--;
                }
                if (r-l+1<ans){
                    ans=r-l+1;
                    start=l;
                }
            }
        }
        return ans==Integer.MAX_VALUE?"":str.substring(start,start+ans);
    }
    public static int numIslands(char[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    dfs(grid,i,j);
                }
            }
        }
        return ans;
    }
    public static void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length|| grid[i][j] !='1') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid,i+1,j);
        dfs(grid,i-1,j);
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);
    }
    public static List<Integer> partitionLabels(String s) {
        List<Integer> ans = new ArrayList<>();
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a']=i;
        }
        int start = 0,end = 0;
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                ans.add(end-start+1);
                start = i+1;
            }
        }
        return ans;
    }
    public List<List<Integer>> generate1(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                // 每行的第一个和最后一个元素是 1
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    // 中间的数是上一行两个相邻数的和
                    int val = result.get(i - 1).get(j - 1) + result.get(i - 1).get(j);
                    row.add(val);
                }
            }
            result.add(row);
        }
        return result;
    }
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j==0||j==i){
                    row.add(1);
                }
                else {
                    int val = ans.get(i-1).get(j-1)+ans.get(i-1).get(j);
                    row.add(val);
                }
            }
            ans.add(row);
        }
        return ans;
    }
    public static int longestCommonSubsequence(String text1, String text2) {
        char[] t1 = text1.toCharArray(),t2 = text2.toCharArray();
        int m = t1.length,n = t2.length;
        int[][] dp = new int[m+1][n+1];
        for (int len1 = 1; len1 <= m; len1++) {
            for (int len2 = 1; len2 <= n; len2++) {
                if (t1[len1-1] == t2[len2-1]) {
                    dp[len1][len2] = dp[len1 -1][len2 -1] + 1;
                }
                else {
                    dp[len1][len2] = Math.max(dp[len1 -1][len2],dp[len1][len2 -1]);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,3,2,2};
        System.out.println(longestSubarray(nums));
    }
}
