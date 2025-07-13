package algorithm.partial_sum;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀和
 */
public class Solution {
    public long beautifulSubarrays(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int ans = 0;
        for (int i = 0, xor = 0; i < nums.length; i++) {
            xor ^= nums[i];
            ans += map.getOrDefault(xor, 0);
            map.put(xor, map.getOrDefault(xor, 0) + 1);

        }
        return ans;
    }

    public long beautifulSubarrays1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int ans = 0;
        for (int i = 0, xor = 0; i < nums.length; i++) {
            xor ^= nums[i];
            ans += map.getOrDefault(xor, 0);
            map.put(xor, map.getOrDefault(xor, 0) + 1);

        }
        return ans;
    }

    public static int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int ans = 0;
        for (int i = 0, sum = 0; i < nums.length; i++) {
            sum += nums[i] == 1 ? 1 : -1;
            if (map.containsKey(sum)) {
                ans = Math.max(ans, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return ans;
    }
}
