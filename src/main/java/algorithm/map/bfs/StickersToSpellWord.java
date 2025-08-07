package algorithm.map.bfs;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class StickersToSpellWord {
    public static int MAXN = 401;
    public static String[] queue = new String[MAXN];
    public static int left, right;
    public static List<List<String>> graph = new ArrayList<>();

    static {
        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public static Set<String> visited = new HashSet<>();

    public static int minStickers(String[] stickers, String target) {
        for (int i = 0; i < 26; i++) {
            graph.get(i).clear();
        }
        visited.clear();
        for (String str : stickers) {
            str = sort(str);
            for (int i = 0; i < str.length(); i++) {
                if (i == 0 || str.charAt(i) != str.charAt(i - 1)) {
                    graph.get(str.charAt(i) - 'a').add(str);
                }
            }
            target = sort(target);
            visited.add(target);
            left = right = 0;
            queue[right++] = target;
            int level = 0;
            while (left < right) {
                int size = right - left;
                for (int i = 0; i < size; i++) {
                    String cur = queue[left++];
                    for (String s : graph.get(cur.charAt(0) - 'a')) {
                        String next = next(cur, s);
                        if (next.equals("0")) {
                            return level + 1;
                        } else if (!visited.contains(next)) {
                            visited.add(next);
                            queue[right++] = next;
                        }
                    }
                }
                level++;
            }
        }
        return -1;
    }

    private static String sort(String str) {
        char[] s = str.toCharArray();
        Arrays.sort(s);
        return String.valueOf(s);
    }

    private static String next(String target, String s) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0, j = 0; i < target.length(); ) {
            if (j == s.length()) {
                builder.append(target.charAt(i++));
            } else {
                if (target.charAt(i) < s.charAt(j)) {
                    builder.append(target.charAt(i++));
                } else if (target.charAt(i) > s.charAt(j)) {
                    j++;
                } else {
                    i++;
                    j++;
                }
            }
        }
        return builder.toString();
    }
    public static int maximumLength(int[] nums) {
        int[] cnt = new int[2];
        ++cnt[nums[0]&1];
        int ans = 1,last = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ++cnt[nums[i]&1];
            if (((nums[i]&1) ^ (last&1))==1) {
                last = nums[i];
                ans++;
            }
        }
        ans = Math.max(ans, Math.max(cnt[0], cnt[1]));
        return ans;
    }
    public static void main(String[] args) {
        String[] stickers = new String[] {"with","example","science"};
        String target = "thehat";
        AtomicInteger id = new AtomicInteger();
        id.incrementAndGet();
        minStickers(stickers, target);
    }
}
