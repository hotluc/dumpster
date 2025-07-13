package algorithm.dp;

/**
 * 状态压缩dp
 */
public class StateCompressionDP {
    public static boolean canIWin(int n,int m ){
        if (m==0) {
            return true;
        }
        if (n*(n+1)/2<m){
            return false;
        }
        int[] dp = new int[1<<(n+1)];
        return f(n,1<<(n+1)-1,m,dp);
    }

    private static boolean f(int n, int status, int rest, int[] dp) {
        if (rest<=0) {
            return false;
        }
        if (dp[status]!=0) {
            return dp[status]==1;
        }
        boolean ans = false;
        for (int i = 1; i <= n ; i++) {
            if ((status & (1 << i)) != 0&&!f(i,status^(1<<i),rest-i,dp)) {
                ans = true;
                break;
            }
        }
        dp[status] = ans ? 1 : -1;
        return ans;
    }
}
