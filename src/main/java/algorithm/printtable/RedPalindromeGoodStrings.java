package algorithm.printtable;

public class RedPalindromeGoodStrings {
    public static int num1(int n) {
        char[] path = new char[n];
        return f(path,0);
    }

    private static int f(char[] path, int i) {
        if (i == path.length) {
            int cnt = 0;
            for (int l = 0; l < path.length; l++) {
                for (int j = l + 1; j < path.length; j++) {
                    if (is(path, l, j)) {
                        cnt++;
                    }
                    if (cnt > 1) {
                        return 0;
                    }
                }
            }
            return cnt==1?1:0;
        }
        else {
            int ans = 0;
            path[i] = 'r';
            ans += f(path, i + 1);
            path[i] = 'e';
            ans += f(path, i + 1);
            path[i] = 'd';
            ans += f(path, i + 1);
            return ans;
        }
    }
    private static boolean is(char[] s, int l, int r) {
        while (l++ < r--) {
            if (s[l] != s[r]) {
                return false;
            }
        }
        return true;
    }
}
