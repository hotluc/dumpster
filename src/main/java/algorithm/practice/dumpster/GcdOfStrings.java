package algorithm.practice.dumpster;

import java.util.*;

public class GcdOfStrings {
    public static String gcdOfStrings(String str1, String str2) {
        String ans = getRepeatedSubstring(str1);
        return ans.equals(getRepeatedSubstring(str2)) ? ans : "";
    }
    public static String getRepeatedSubstring(String s) {
        int n = s.length();
        int[] Z = getZArray(s);

        for (int i = 1; i < n; i++) {
            if (Z[i] == n - i && n % i == 0) {
                return s.substring(0, i);
            }
        }
        return s;
    }

    private static int[] getZArray(String s) {
        int n = s.length();
        int[] Z = new int[n];
        int l = 0, r = 0;

        for (int i = 1; i < n; i++) {
            if (i <= r) {
                Z[i] = Math.min(r - i + 1, Z[i - l]);
            }
            while (i + Z[i] < n && s.charAt(Z[i]) == s.charAt(i + Z[i])) {
                Z[i]++;
            }
            if (i + Z[i] - 1 > r) {
                l = i;
                r = i + Z[i] - 1;
            }
        }
        return Z;
    }
    public static int findGCD(int[] nums) {
        int max = nums[0], min = nums[0];
        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        return gcd(max, min);
    }
    public static int gcd(int a,int b){
        return b==0?a:gcd(b,a%b);
    }
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                }
                else {
                    row.add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
                }
            }
            ans.add(row);
        }
        return ans;
    }
    public static int maxEvents(int[][] events) {
        int n = events.length;
        Arrays.sort(events, Comparator.comparing(o->o[0]));
        int min = events[0][0], max = events[0][1];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, events[i][1]);
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int i=0,ans=0;
        for (int day = min; day <= max; day++) {
             while (i<n && events[i][0] == day) {
                 heap.add(events[i++][1]);
             }
             while (!heap.isEmpty()&&heap.peek()<day) {
                 heap.poll();
                 ans++;
             }
        }
        return ans;
    }
    public static int maxValue(int[][] events, int k) {
        int n = events.length, ans = 0;
        Arrays.sort(events, Comparator.comparing(o->o[1]));
        PriorityQueue<Integer> heap1 = new PriorityQueue<>();
        PriorityQueue<Integer> heap2 = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            heap1.offer(events[i][0]);
        }
        while (k>0) {

        }
        return ans;
    }
    public static void main(String[] args) {
        String str1 = "LEET";
        String str2 = "CODE";
        int[][] events = {{1,2,4},{3,4,3},{2,3,1}};
        System.out.println(Integer.toBinaryString(Integer.bitCount(23)));
    }
}
