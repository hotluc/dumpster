package algorithm.binary;

/**
 *  二进制
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(1));
        System.out.println(isPowerOfThree(9));
        System.out.println(Integer.toBinaryString(3));
    }
    private void printBinary(int n) {
        while (n > 0) {
            System.out.println(Integer.toBinaryString(n));
            n >>= 1;
        }
    }
    private void show(int i) {
        int power = 0;
        while (1<<power <= i>>1) {
            power++;
        }
        System.out.println("<="+i+"最大的2次幂是2的"+power+"次方");
    }
    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
    public static boolean isPowerOfThree(int n) {
        return n > 0 && n % 3 == 0;
    }
    public static int rangeBitWiseAnd(int left, int right) {
        while (left < right) {
            right -= right&-right;
        }
        return right;
    }
}
