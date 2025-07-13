package algorithm.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static List<String>findWords(char[][] board,String[]words){
        build(words);
        List<String>ans = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board,i,j,1,ans);
            }
        }
        clear();
        return ans;
    }

    private static void clear() {
        for (int i = 0; i < cnt; i++) {
            Arrays.fill(tree[i],0);
        }
        Arrays.fill(pass,0);
        Arrays.fill(end,null);
    }

    private static int dfs(char[][] board, int i, int j, int t, List<String> ans) {
        if (i<0||i==board.length||j<0||j==board[0].length||board[i][j]==0){
            return 0;
        }
        char temp = board[i][j];
        int road = temp-'a';
        t=tree[t][road];
        if (pass[t]==0){
            return 0;
        }
        int fix=0;
        if (end[t]!=null){
            fix++;
            ans.add(end[t]);
            end[t]=null;
        }
        board[i][j]=0;
        fix+=dfs(board,i-1,j,t,ans);
        fix+=dfs(board,i+1,j,t,ans);
        fix+=dfs(board,i,j-1,t,ans);
        fix+=dfs(board,i,j+1,t,ans);
        pass[t]-=fix;
        board[i][j]=temp;
        return fix;
    }

    public static int MAXN = 100001;
    public static int[][] tree = new int[MAXN][26];
    public static String[] end = new String[MAXN];
    public static int[] pass = new int[MAXN];
    public static int cnt;
    public static void build(String[] words) {
        cnt = 1;
        for (String word:words){
            int cur = 1;
            pass[cur]++;
            for (int i = 0,path; i < word.length(); i++) {
                path = word.charAt(i) - 'a';
                if (tree[cur][path] == 0) {
                    tree[cur][path] = ++cnt;
                }
                cur = tree[cur][path];
                pass[cur]++;
            }
            end[cur]=word;
        }
    }
}
