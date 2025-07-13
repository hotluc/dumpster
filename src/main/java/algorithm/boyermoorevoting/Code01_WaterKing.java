package algorithm.boyermoorevoting;

// 出现次数大于n/2的数
// 给定一个大小为n的数组nums
// 水王数是指在数组中出现次数大于n/2的数
// 返回其中的水王数，如果数组不存在水王数返回-1
// 测试链接 : https://leetcode.cn/problems/majority-element/
public class Code01_WaterKing {
    public static int majorityElement(int[] nums) {
        int candidate = 0, hp = 0;
        for (int num : nums) {
            if (hp == 0) {
                candidate = num;
                hp = 1;
            } else if (num != candidate) {
                hp--;
            } else {
                hp++;
            }
        }
        if (hp == 0) {
            return -1;
        }
        // 复用hp，统计真实出现的次数
        hp = 0;
        for (int num : nums) {
            if (num == candidate) {
                hp++;
            }
        }
        return hp > nums.length / 2 ? candidate : -1;
    }
}

