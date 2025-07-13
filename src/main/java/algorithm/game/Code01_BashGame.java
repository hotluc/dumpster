package algorithm.game;
// 巴什博弈(Bash Game)
// 一共有n颗石子，两个人轮流拿，每次可以拿1~m颗石子
// 拿到最后一颗石子的人获胜，根据n、m返回谁赢
public class Code01_BashGame {
    public static int MAXN = 1001;
    public static String[][] dp = new String[MAXN][MAXN];
    public static String bashGame(int n,int m) {
        if (n == 0) {
            return "后手";
        }
        if (dp[n][m] != null) {
            return dp[n][m];
        }
        String answer = "后手";
        for (int pick = 1; pick <= m; pick++) {
            if (bashGame(n-pick,m).equals("后手")){
                //后续过程的先手就是此时的先手
                answer = "先手";
                break;
            }
        }
        dp[n][m] = answer;
        return answer;
    }
    // 正式方法
    public static String bashGame2(int n, int m) {
        return n % (m + 1) != 0 ? "先手" : "后手";
    }

    public static void main(String[] args) {
        int V = 500,testTime = 5000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random()*V);
            int m = (int) (Math.random()*V)+1;
            String answer1 = bashGame(n,m);
            String answer2 = bashGame2(n,m);
            if (!answer1.equals(answer2)) {
                System.out.println("出错了");
            }
        }
        System.out.println("测试结束");

    }
}
