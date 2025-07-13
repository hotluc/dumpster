package algorithm.recursion.nest;

import java.util.ArrayList;
import java.util.List;

/**
 * 嵌套递归的解法
 */
public class BaseCaculatorIII {
    public static int where;

    public static int calculate(String str) {
        where = 0;
        return f(str.toCharArray(), 0);
    }

    private static int f(char[] s, int i) {
        int cur = 0;
        List<Integer> numbers = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        while (i < s.length && s[i] != '(') {
            if (s[i] >= '0' && s[i] <= '9') {
                cur = cur * 10 + s[i++] - '0';
            } else if (s[i] != '(') {
                push(numbers, ops, cur, s[i++]);
                cur = 0;
            } else {
                cur = f(s, i + 1);
                i = where + 1;
            }
        }
        push(numbers, ops, cur, '+');
        where = i;
        return compute(numbers, ops);
    }

    private static void push(List<Integer> numbers, List<Character> ops, int cur, char op) {
        int n = numbers.size();
        if (n == 0 || ops.get(n - 1) == '+' || ops.get(n - 1) == '-') {
            numbers.add(cur);
            ops.add(op);
        } else {
            int topNumber = numbers.get(n - 1);
            char topOp = ops.get(n - 1);
            if (topOp == '*') {
                numbers.set(n - 1, topNumber * cur);
            } else {
                numbers.set(n - 1, topNumber / cur);
            }
            ops.set(n - 1, op);
        }
    }

    private static int compute(List<Integer> numbers, List<Character> ops) {
        int n = numbers.size();
        int ans = numbers.getFirst();
        for (int i = 1; i < n; i++) {
            ans += ops.get(n - 1) == '+' ? numbers.get(i) : -numbers.get(i);
        }
        return ans;
    }
}
