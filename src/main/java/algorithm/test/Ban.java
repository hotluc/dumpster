package algorithm.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ban {

    public static void swap(List<String> begin, List<String> end){
        // 数量小的一侧
        Set<String> smallLevel = new HashSet<>(begin);
        // 数量大的一侧
        Set<String> bigLevel = new HashSet<>(end);
        System.out.println("小的"+smallLevel);
        System.out.println("大的"+bigLevel);
        // 由数量小的一侧，所扩展出的下一层列表
        if (smallLevel.size() >= bigLevel.size()){
            Set<String> temp = smallLevel;
            smallLevel = bigLevel;
            bigLevel = temp;
        }
        System.out.println("交换后的小"+smallLevel);
        System.out.println("交换后的大"+bigLevel);
    }
    public static void main(String[] args) {
        List<String> begin = new ArrayList<>();
        begin.add("leecode");
        begin.add("sm");
        begin.add("leecod");
        begin.add("s2m");
        List<String> end = new ArrayList<>();
        end.add("leecode1");
        end.add("sm1");
        end.add("leecode2");
        swap(begin, end);
    }
}
