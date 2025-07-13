package algorithm.map.bfs;

import java.util.HashSet;
import java.util.List;

/**
 * 双向广搜
 */
public class Solution1 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)){
            return 0;
        }
        //数量小那一层
        HashSet<String> smallLevel = new HashSet<>();
        //数量大那一层
        HashSet<String> bigLevel = new HashSet<>();
        //数量小的那个下一层
        HashSet<String> nextLevel = new HashSet<>();
        smallLevel.add(beginWord);
        bigLevel.add(endWord);
        for (int len = 2; !smallLevel.isEmpty(); len++) {
            for (String w: smallLevel){
                char[] word = w.toCharArray();
                for (int i = 0; i < w.length(); i++) {
                    char old = word[i];
                    for (char change = 'a'; change < 'z'; change++) {
                        if (change != old){
                            word[i] = change;
                            String next = String.valueOf(word);
                            if (bigLevel.contains(next)){
                                return len;
                            }
                            if (dict.contains(next)){
                                dict.remove(next);
                                nextLevel.add(next);
                            }
                        }
                    }
                    word[i] = old;
                }
            }
            if (nextLevel.size() <= smallLevel.size()){
                HashSet<String> tmp = new HashSet<>();
                smallLevel = nextLevel;
                nextLevel = tmp;
            }
            else {
                HashSet<String> tmp = new HashSet<>();
                smallLevel = bigLevel;
                bigLevel = smallLevel;
                nextLevel = tmp;
            }
            nextLevel.clear();;
        }
        return 0;
    }
}
