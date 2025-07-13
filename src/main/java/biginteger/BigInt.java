package biginteger;

/**
 * 大整数
 */
public class BigInt {
    public static String add(String num1, String num2) {
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

    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String ans = "0";
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;

        return "1";
    }

    public static void main(String[] args) {
        System.out.println();
        System.out.println(add("2147483647686866678789679697008", "214748364777776384656"));
    }

}
