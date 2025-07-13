package algorithm;

import java.util.*;

public class RangeFreqQuery {
    private Map<Integer, List<Integer>> occurrence;

    public RangeFreqQuery(int[] arr) {
        occurrence = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            occurrence.putIfAbsent(arr[i],new ArrayList<>());
            occurrence.get(arr[i]).add(i);
        }
    }
    public int query(int left, int right, int value) {
        List<Integer> pos = occurrence.getOrDefault(value, new ArrayList<>());
        int l = lowerBound(pos, left);
        System.out.println(l);
        int r = upperBound(pos, right);
        System.out.println(r);
        return r - l;
    }
    private int lowerBound(List<Integer> pos, int target) {
        int low = 0, high = pos.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (pos.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
    private int upperBound(List<Integer> pos, int target) {
        int low = 0, high = pos.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (pos.get(mid) <= target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56,56};
        RangeFreqQuery trd = new RangeFreqQuery(arr);
        trd.query(0,12,56);
        for (Integer key : trd.occurrence.keySet()) {
            System.out.println(key + " = " + trd.occurrence.get(key));
        }


    }

}
