package algorithm.practice.dumpster;

import java.util.Arrays;

public class IncreasingTriplet {

    public static boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= first) {
                first = num; // 最小值
                System.out.println("first="+first);
            } else if (num <= second) {
                second = num; // 第二小的值
                System.out.println("second="+second);
            } else {
                // 找到第三个更大的数
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {20,100,10,12,5,13};
        System.out.println(increasingTriplet(nums));
    }
}
