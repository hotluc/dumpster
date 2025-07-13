package algorithm.recursion.nest;

public class DecodeString {
    public static int where;

    public static String decodeString(String str) {
        where = 0;
        return f(str.toCharArray(), 0);
    }
    private static String f(char[] s, int i) {
        StringBuilder ans = new StringBuilder();
        int cnt = 0;
        while (i < s.length && s[i] != ']') {
            if ((s[i] >= 'a' && s[i] <= 'z')||(s[i] >= 'A' && s[i] <= 'Z') ) {
                ans.append(s[i++]);
            }               else if (s[i] >= '0' && s[i] <= '9') {
                cnt = cnt * 10 + s[i++] - '0';
            }
            else {
                ans.append(get(cnt,f(s,i+1)));
                i = where+1;
                cnt = 0;
            }
}
        where = i;
        return ans.toString();
    }

    private static String get(int cnt, String str) {
        return String.valueOf(str).repeat(Math.max(0, cnt));
    }
}