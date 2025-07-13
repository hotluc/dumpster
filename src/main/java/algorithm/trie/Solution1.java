package algorithm.trie;

import java.util.Arrays;

public class Solution1 {
    public static boolean exist(char[][] board, String word) {
        build(word);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board,i,j,1);
            }
        }
        clear();
        return fix==1;
    }

    private static int dfs(char[][] board, int i, int j, int t) {
        if (i<0||i==board.length||j<0||j==board[0].length||board[i][j]==0){
            return 0;
        }
        char temp = board[i][j];
        int road = temp-'A';
        t=tree[t][road];
        if (pass[t]==0){
            return 0;
        }
        if (end[t]!=null){
            fix++;
            end[t]=null;
        }
        board[i][j]=0;
        fix+=dfs(board,i-1,j,t);
        fix+=dfs(board,i+1,j,t);
        fix+=dfs(board,i,j-1,t);
        fix+=dfs(board,i,j+1,t);
        pass[t]-=fix;
        board[i][j]=temp;
        return fix;
    }
    public static void clear(){
        for (int i = 1; i <= cnt; i++) {
            Arrays.fill(tree[i],0);
            pass[i]=0;
            end[i]=null;
        }
    }


    public static int MAXN = 100010;
    public static int[][] tree = new int[MAXN][26];
    public static String[] end = new String[MAXN];
    public static int[] pass = new int[MAXN];
    public static int cnt=1;
    public static int fix=0;
    public static void build(String word) {
        int cur = 1;
        pass[cur]++;
        for (int i = 0, path; i < word.length(); i++) {
            path = word.charAt(i) -'A';
            if (tree[cur][path] == 0) {
                tree[cur][path] = ++cnt;
            }
            cur = tree[cur][path];
            pass[cur]++;
        }
        end[cur]=word;
    }

    public static void main(String[] args) {
        char[][]broad=new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word="ABCB";
        System.out.println(exist(broad,word));
    }
}
