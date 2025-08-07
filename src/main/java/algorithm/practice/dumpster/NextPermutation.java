package algorithm.practice.dumpster;

import java.util.HashMap;
import java.util.Map;

public class NextPermutation {
    public static int totalFruit(int[] fruits) {
        int n = fruits.length,left = 0,ans =0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int right = 0; right < n; right++) {
            cnt.put(fruits[right], cnt.getOrDefault(fruits[right], 0) + 1);
            while (cnt.size()>2){
                cnt.put(fruits[left], cnt.getOrDefault(fruits[left], 0) - 1);
                if (cnt.get(fruits[left]) == 0){
                    cnt.remove(fruits[left]);
                }
                left++;
            }
            ans = Math.max(ans, right-left+1);
        }
        return ans;
    }
    public static int findLeft(int[] nums,int target){
        int n = nums.length,left = 0,right=n-1,ans =0,mid;
        while (left<=right){
            mid = left+(right-left)/2;
            if (nums[mid]>=target){
                ans = mid;
                right = mid-1;
            }
            else {
                left = mid+1;
            }
        }
        return ans;
    }
    public static int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length,ans = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < fruits.length; i++) {
            for (int j = 0; j < baskets.length; j++) {
                if (fruits[i] >= baskets[j]&&!visited[j]) {
                    visited[j] = true;
                    ans--;
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] fruits = {4,2,5};
        int[] baskets = {3,5,4};
        System.out.println(numOfUnplacedFruits(fruits, baskets));
    }
}
