package algorithm.bitmap;

import java.util.HashSet;

/**
 * 位图
 */
public class BitMap {
    public int[] set;

    /**
     * 初始化位图，n是0~n-1的范围
     * @param n
     */
    public BitMap(int n) {
        set = new int[(n + 31) / 32];
    }

    /**
     * 把num添加到位图中
     * @param num
     */
    public void add(int num){
        set[num / 32] |= 1 << (num % 32);
    }

    /**
     * 从位图中把num移除
     * @param num
     */
    public void remove(int num){
        set[num / 32] &= ~(1 << (num % 32));
    }

    /**
     * 反转num在位图中的状态
     * @param num
     */
    public void reverse(int num){
        set[num / 32] ^= 1 << (num % 32);
    }
    /**
     * 判断num是否在位图中
     * @param num
     * @return
     */
    public boolean contains(int num){
        return (set[num / 32] >> (num % 32) & 1) == 1;
    }
    //对数器测试
    public static void main(String[] args) {
        int n = 1000;
        int testTime = 100000;
        System.out.println("测试开始");
        BitMap bitMap = new BitMap(n);
        HashSet<Integer> set = new HashSet<>();
        System.out.println("调用阶段开始");
        for (int i = 0; i < testTime; i++) {
            double decide = Math.random();
            int num = (int) (Math.random() * n);
            for (int j = 0; j < n; j++) {
                if (decide < 0.333) {
                    bitMap.add(num);
                    set.add(num);
                } else if (decide < 0.666) {
                    bitMap.remove(num);
                    set.remove(num);
                } else {
                    bitMap.reverse(num);
                    if (set.contains(num)) {
                        set.remove(num);
                    } else {
                        set.add(num);
                    }
                }

            }
        }
        System.out.println("调用阶段结束");
        System.out.println("验证阶段开始");
        for (int i = 0; i < n; i++) {
            if (bitMap.contains(i) != set.contains(i)){
                System.out.println("出错了");
            }
        }
        System.out.println("验证阶段结束");
        System.out.println("测试结束");
    }
}
