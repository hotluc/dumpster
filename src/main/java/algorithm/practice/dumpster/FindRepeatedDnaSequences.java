package algorithm.practice.dumpster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindRepeatedDnaSequences {
    public static int L = 10;
    public static Map<Character, Integer> map = new HashMap<>(){
        {
            put('A', 0);
            put('C', 1);
            put('G', 2);
            put('T', 3);
        }
    };
    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        int n = s.length(),x = 0;
        if (n <= L) {
            return ans;
        }
        for (int i = 0; i < L-1;i++) {
            x = (x<<2)|map.get(s.charAt(i));
        }
        Map<Integer,Integer> cnt = new HashMap<>();
        for (int i = 0; i <= n-L;i++) {
            x = ((x << 2) | map.get(s.charAt(i + L - 1))) & ((1 << (L * 2)) - 1);
            cnt.put(x,cnt.getOrDefault(x,0) + 1);
            if (cnt.get(x) == 2) {
                ans.add(s.substring(i,i+L));
            }
        }
        return ans;
    }
    public static boolean repeatedSubstringPattern(String str) {
        int[] cnt = new int[26];
        char[] s = str.toCharArray();
        for (char c : s) {
            cnt[c - 'a']++;
        }
        if (str.length()==1){
            return false;
        }
        for (int i = 1; i < cnt.length; i++) {
            int prev = cnt[0];
            if (cnt[i]!=0&&cnt[i] != prev) {
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) {
        System.out.println(repeatedSubstringPattern("bb"));
    }
}
