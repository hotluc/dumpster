package algorithm.boyermoorevoting;

import java.util.List;

// 划分左右使其水王数相同
// 给定一个大小为n的数组nums
// 水王数是指在数组中出现次数大于n/2的数
// 返回其中的一个划分点下标，使得左侧水王数等于右侧水王数
// 如果数组不存在这样的划分返回-1
// 测试链接 : https://leetcode.cn/problems/minimum-index-of-a-valid-split/
public class Code04_SplitSameWaterKing {
    public static int minimumIndex(List<Integer> nums) {
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
        int n = nums.size();
        // lc : 水王数左侧出现的词频
        // rc : 水王数右侧出现的词频
        for (int i = 0,lc=0,rc=hp; i < n-1; i++) {
            if (nums.get(i) == candidate) {
                lc++;
                rc--;
            }
            if (lc>(i+1)/2&&rc>(n-i-1)/2){
                return i;
            }
        }
        return -1;
    }
}
