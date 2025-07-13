package algorithm.printtable;

public class AppleMinBags {
    public static int bagS1(int apple) {
        int ans = f(apple);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int f(int rest) {
        if (rest < 0) {
            return Integer.MAX_VALUE;
        }
        if (rest == 0) {
            return 0;
        }
        //使用8规格的袋子，剩余的苹果几个袋子，有可能返回无效解
        int p1 = f(rest - 8);
        //使用6规格的袋子，剩余的苹果几个袋子，有可能返回无效解
        int p2 = f(rest - 6);
        p1 += p1 != Integer.MAX_VALUE ? 1 : 0;
        p2 += p2 != Integer.MAX_VALUE ? 1 : 0;
        return Math.min(p1, p2);
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            System.out.println(i+ " : " + bagS1(i));
        }
    }
}
