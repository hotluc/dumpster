package algorithm.multiply;

import java.util.*;

public class Solution {
    public static int MAXN = 10000;
    public static int[] stack = new int[MAXN];
    public static int r = 0;
    public static String multiply(String num1, String num2) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int digitA = i >= 0 ? num1.charAt(i) - '0' : 0;
            int digitB = j >= 0 ? num2.charAt(i) - '0' : 0;
            int sum = digitA * digitB + carry;
            carry = sum / 10;
            sum %= 10;
            stringBuilder.append(sum);
            i--;
            j--;
        }
        return stringBuilder.reverse().toString();
    }

    public static int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        int ans = 0;
        for (int left = 0, right = 1; right < nums.length; left++, right++) {
            ans = Math.max(ans, nums[right] - nums[left]);
        }
        return ans;
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        for (int l = 0, r = 0, sum = 0; r < nums.length; r++) {
            sum += nums[r];
            while (sum - nums[l] >= target) {
                sum -= nums[l++];
            }
            if (sum >= target) {
                ans = Math.min(ans, r - l + 1);
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
    public static String largestNumber(int[] nums) {
        int n = nums.length;
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strings,(a,b)-> (b+a).compareTo(a+b));
        if (strings[0].equals("0")){
            return "0";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(String s:strings){
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] == nums[i+1]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
    }
}
