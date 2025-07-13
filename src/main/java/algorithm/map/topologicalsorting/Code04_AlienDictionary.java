package algorithm.map.topologicalsorting;

import java.util.*;

// 火星词典
// 现有一种使用英语字母的火星语言
// 这门语言的字母顺序对你来说是未知的。
// 给你一个来自这种外星语言字典的字符串列表 words
// words 中的字符串已经 按这门新语言的字母顺序进行了排序 。
// 如果这种说法是错误的，并且给出的 words 不能对应任何字母的顺序，则返回 ""
// 否则，返回一个按新语言规则的 字典递增顺序 排序的独特字符串
// 如果有多个解决方案，则返回其中任意一个
// words中的单词一定都是小写英文字母组成的
// 测试链接 : https://leetcode.cn/problems/alien-dictionary/
// 测试链接(不需要会员) : https://leetcode.cn/problems/Jf1JuT/
public class Code04_AlienDictionary {
    public static String alienOrder(String[] words) {
        // 入度表，26种字符
        int[] indegree = new int[26];
        Arrays.fill(indegree, -1);
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                indegree[word.charAt(i) - 'a'] = 0;
            }
        }
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>());
        }
        return "";
    }
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
            indegree[edge[0]]++;
        }
        int[] queue = new int[numCourses];
        int l=0, r=0;
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue[r++] = i;
            }
        }
        int cnt = 0;
        while (l < r) {
            int cur = queue[l++];
            cnt++;
            for (int next : graph.get(cur)) {
                if (--indegree[next] == 0) {
                    queue[r++] = next;
                }
            }
        }
        return cnt == numCourses ? queue : new int[0];
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
            indegree[edge[0]]++;
        }
        int[] queue = new int[numCourses];
        int l=0, r=0;
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue[r++] = i;
            }
        }
        int cnt = 0;
        while (l < r) {
            int cur = queue[l++];
            cnt++;
            for (int next : graph.get(cur)) {
                if (--indegree[next] == 0) {
                    queue[r++] = next;
                }
            }
        }
        return cnt == numCourses;
    }
    public int sumOfUnique(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int sum = 0;
        for (int num : nums) {
            set.add(num);
        }
        for (int num : set) {
            sum += num;
        }
        return sum;
    }
    public static int secondHighest(String str) {
        int mask = 0;
        char[] s = str.toCharArray();
        for (char c : s) {
            if (Character.isDigit(c)) {
                mask |= 1 << (c - '0');
            }
        }
        for (int i =9,count = 0; i >=0; i--) {
            if (((mask >> i)&1)==1&&++count == 2) {
                return i;
            }
        }
        return -1;
    }
    public static int[] decompressRLElist(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length-1; i+=2) {
            while (nums[i]!=0){
                list.add(nums[i+1]);
                nums[i]--;
            }
        }
        return list.stream().mapToInt(i->i).toArray();
    }
    public static int countElements(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 1; i < nums.length-1; i++) {
            if (nums[i-1] < nums[i]&&nums[i]<nums[i+1]) {
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        System.out.println(countElements(new int[]{-3,3,3,90}));
    }
}
