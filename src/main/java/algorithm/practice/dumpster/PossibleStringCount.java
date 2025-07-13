package algorithm.practice.dumpster;

public class PossibleStringCount {
    public static int stringCount(String word) {
        int ans = 1, len = word.length();
        char[] w = word.toCharArray();
        for (int l = 0, r; l < len; ) {
            r = l;
            while (r + 1 < len && w[r + 1] == w[r]) {
                r++;
            }
            int count = r - l + 1; // 当前区间长度
            if (count >= 2) {
                System.out.println("区间字符: " + w[l] + "，长度: " + count);
                ans += count-1;
            }
            l = r + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(System.getenv("aliQwen-api"));
    }
}
