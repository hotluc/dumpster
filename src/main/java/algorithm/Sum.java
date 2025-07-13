package algorithm;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Sum {
    public static void sum(String a, String b) {
        byte[] bytes = a.getBytes(StandardCharsets.UTF_8);

        byte[] bytes1 = b.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] += bytes1[i];
        }
        System.out.println(Arrays.toString(bytes));
    }

    public static void main(String[] args) {
        int[] num={2,1,5};
        //System.out.println(addBinaryL("1010", "1011"));
        //System.out.println(addStrings("0", "0"));
        addToArrayForm(num,806);

    }

    public static String addBinaryL(String a, String b) {
        StringBuilder res = new StringBuilder(); // 返回结果
        int i = a.length() - 1; // 标记遍历到 a 的位置
        int j = b.length() - 1; // 标记遍历到 b 的位置
        int carry = 0; // 进位
        while (i >= 0 || j >= 0 || carry != 0) { // a 没遍历完，或 b 没遍历完，或进位不为 0
            int digitA = i >= 0 ? a.charAt(i) - '0' : 0; // 当前 a 的取值
            int digitB = j >= 0 ? b.charAt(j) - '0' : 0; // 当前 b 的取值
            int sum = digitA + digitB + carry; // 当前位置相加的结果
            carry = sum >= 2 ? 1 : 0; // 是否有进位
            sum = sum >= 2 ? sum - 2 : sum; // 去除进位后留下的数字
            res.append(sum); // 把去除进位后留下的数字拼接到结果中
            i--;  // 遍历到 a 的位置向左移动
            j--;  // 遍历到 b 的位置向左移动
        }
        return res.reverse().toString(); // 把结果反转并返回
    }

    public static String addBinary(String a, String b) {
        StringBuilder stringBuilder = new StringBuilder();
        int m = a.length() - 1, n = b.length() - 1, carry = 0;
        while (m >= 0 || n >= 0 || carry != 0) {
            int ai = m >= 0 ? a.charAt(m) - '0' : 0;
            int bi = n >= 0 ? b.charAt(n) - '0' : 0;
            int sum = ai + bi + carry; // 当前位置相加的结果
            carry = sum / 2;
            sum %= 2; // 去除进位后留下的数字
            stringBuilder.append(sum);
            m--;
            n--;

        }
        return stringBuilder.reverse().toString();
    }

    public static String addStrings(String num1, String num2) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int digitA = i >= 0 ? num1.charAt(i) - '0' : 0;
            int digitB = j >= 0 ? num2.charAt(j) - '0' : 0;
            int sum = digitA + digitB + carry;
            carry = sum / 10;
            sum %= 10;
            stringBuilder.append(sum);
            i--;
            j--;
        }
        return stringBuilder.reverse().toString();
    }

    public static List<Integer>  addToArrayForm(int[] num, int k) {
        List<Integer> ans = new ArrayList<>();
        int index = num.length-1,carry=0;
        while (index >= 0 || k != 0||carry!=0){
            int digitA = index >= 0 ? num[index] : 0;
            int digitB = k%10;
            int sum = digitA + digitB + carry;
            carry = sum/10; // 是否有进位
            sum %=10;
            ans.add(sum);
            index --;
            k /= 10;
        }
        Collections.reverse(ans);

        return ans;

    }

}