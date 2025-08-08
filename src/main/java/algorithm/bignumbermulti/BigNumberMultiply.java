package algorithm.bignumbermulti;

public class BigNumberMultiply {
    public static String multiply(String num1, String num2){
        int n1 = num1.length(),n2= num2.length();
        int[] res = new int[n1 + n2];
        for (int i = n1 - 1; i >= 0; i--) {
            int a = num1.charAt(i) - '0';
            for (int j = n2 - 1; j >= 0; j--) {
                int b = num2.charAt(j) - '0';
                int mul = a * b + res[i + j + 1]; // 当前位置已有值加上当前乘积
                res[i + j + 1] = mul % 10;       // 当前位
                res[i + j] += mul / 10;          // 进位加到高一位
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < res.length - 1 && res[i] == 0) i++;  // 跳过前导0
        for (; i < res.length; i++) sb.append(res[i]);
        return sb.toString();
    }

    public static void main(String[] args) {
        String num1 = "12345678901234567890";
        String num2 = "98765432109876543210";
        String result = multiply(num1, num2);
        System.out.println("Result: " + result); // 输出结果
    }
}
