package algorithm.practice.dumpster;

import java.util.ArrayList;
import java.util.List;

public class CanVisitAllRooms {
    public static int MAXN = 1020;
    public static int[] father = new int[MAXN];
    public static int sets;
    public static void build(int n){
        for (int i = 0; i <= n; i++) {
            father[i] = i;
        }
        sets = n;
    }
    public static int find(int i){
        if (i!=father[i]){
            father[i] = find(father[i]);
        }
        return father[i];
    }
    public static void union(int x, int y){
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
            father[fx] = fy;
            sets--;
        }
    }
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        build(n);
        for (int i = 0; i < n; i++) {
            for(int key: rooms.get(i)){
                union(i,key);
            }
        }
        return sets == 1;
    }

    public static void main(String[] args) {
        List<List<Integer>> rooms = new ArrayList<>();
        List<Integer> room = new ArrayList<>();
        List<Integer> room1 = new ArrayList<>();
        List<Integer> room2 = new ArrayList<>();
        List<Integer> room3 = new ArrayList<>();
        room.add(1);
        room.add(3);
        room1.add(3);
        room1.add(0);
        room1.add(1);
        room2.add(2);
        room3.add(0);
        rooms.add(room);
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        System.out.println(canVisitAllRooms(rooms));
    }
}
