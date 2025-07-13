package algorithm.greedyalgorithm;
// 砍竹子II
// 现需要将一根长为正整数bamboo_len的竹子砍为若干段
// 每段长度均为正整数
// 请返回每段竹子长度的最大乘积是多少
// 答案需要对 1000000007 取模
// 测试链接 : https://leetcode.cn/problems/jian-sheng-zi-ii-lcof/
public class Code01_CuttingBamboo {
    // 快速幂，求余数
    // 求x的n次方，最终得到的结果 % mod
    public static long power(int x,int n,int mod){
        long ans = 1;
        while(n >0){
            if ((n & 1) == 1){
                ans = (ans*x)%mod;
            }
            x = (x*x)%mod;
            n >>= 1;
        }
        return ans;
    }
}
