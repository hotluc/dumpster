package algorithm.dp.numberpositiondp;

//给你两个正整数 low 和 high 。
//对于一个由 2 * n 位数字组成的整数 x ，如果其前 n 位数字之和与后 n 位数字之和相等，则认为这个数字是一个对称整数。
//返回在 [low, high] 范围内的 对称整数的数目 。
// 测试链接 : https://leetcode.cn/problems/count-symmetric-integers/
public class CodeCountSymmetricIntegers {
    public static int MOD = 1000000007;

    public static int countSymmetricIntegers(int low, int high) {
        double sum = 10e15;
        System.out.println(sum);
        return 0;
    }

    public static void main(String[] args) {
        countSymmetricIntegers(0, 0);
    }

    public static long mod = 1000000007;

    public static int countGoodNumbers(long n) {
        return (int) ((quickMulti(5, (n + 1) / 2) * quickMulti(4, n / 2)) % mod);
    }

    private static long quickMulti(int x, long y) {
        long ans = 1, mul = x;
        while (y > 0) {
            if ((y & 1) == 1) {
                ans = (ans * mul) % mod;
            }
            mul = mul * mul % mod;
            y >>= 1;
        }
        return ans;
    }
}
