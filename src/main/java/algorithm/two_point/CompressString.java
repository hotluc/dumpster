package algorithm.two_point;
//443. 压缩字符串
//给你一个字符数组 chars ，请使用下述算法压缩：
//从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
//如果这一组长度为 1 ，则将字符追加到 s 中。
//否则，需要向 s 追加字符，后跟这一组的长度。
//压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符。
//请在 修改完输入数组后 ，返回该数组的新长度。
//你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
//https://leetcode.cn/problems/string-compression/description/
public class CompressString {
    public static int compress(char[] chars) {
        int n = chars.length,write = 0,left = 0;
        for (int read = 0; read < n; read++) {
            if(read == n-1||chars[read]!=chars[read+1]) {
                chars[write++] = chars[read];
                System.out.printf("read第%d位,字符是%c\n",read+1,chars[read]);
                System.out.printf("write第%d位,字符是%c\n",write+1,chars[write]);
                int num = read - left+1;
                System.out.println(num);
                if(num>1) {
                    int temp = write;
                    while (num > 0) {
                        chars[write++] = (char) (num%10+'0');
                        num/=10;
                    }
                    reverse(chars, temp, write-1);
                }
                left = read+1;
            }
        }
        return write;
    }
    private static void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }
    public static int findMaxLength(int[] nums) {
        int n = nums.length,ans = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] == nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums = {0,1,1,1,1,1,0,0,0};
        char[] chars = new char[]{'a','a','b','b','c','c','c'};
        System.out.println(findMaxLength(nums));
    }
}
