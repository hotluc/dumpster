package algorithm.practice.dumpster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BackspaceCompare {
    public static boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (S.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }
            while (j >= 0) {
                if (T.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }
            System.out.println(skipS);
            System.out.println(skipT);
            if (i >= 0 && j >= 0) {
                if (S.charAt(i) != T.charAt(j)) {
                    return false;
                }
            } else {
                if (i >= 0 || j >= 0) {
                    return false;
                }
            }
            i--;
            j--;
        }
        return true;
    }

    public static int[] sortEvenOdd(int[] nums) {
        List<Integer> even = new ArrayList<>(), odd = new ArrayList<>();
        for (int num : nums) {
            if (num % 2 == 0) {
                even.add(num);
            }
            if (num % 2 == 1) {
                odd.add(num);
            }
        }
        Collections.sort(even);
        odd.sort(Collections.reverseOrder());
        int[] result = new int[nums.length];
        int evenIndex = 0, oddIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                result[i] = even.get(evenIndex++);
            }
            if (i % 2 != 0) {
                result[i] = odd.get(oddIndex++);
            }
        }
        return result;
    }

    public static int minSubArrayLen(int[] nums, int k) {
        int ans = 0;
        for (int l = 0, r = 0, sum = 1; r < nums.length; r++) {
            sum *= nums[r];
            while (sum >= k&&l<r) {
                sum/=nums[l++];
            }
            ans += r - l + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(minSubArrayLen(new int[]{1, 2, 3, 4, 5}, 100));
    }
}
