package algorithm.two_point;

public class Solution {
    public static int trap(int[] nums){
        int l=1,r = nums.length-2,lMax = nums[0],rMax = nums[nums.length-1],ans =0;
        while (l<=r){
            if (lMax<=rMax){
                ans+=Math.max(0,lMax-nums[l]);
                lMax=Math.max(lMax,nums[l++]);
            }else {
                ans+=Math.max(0,rMax-nums[r]);
                rMax=Math.max(rMax,nums[r--]);
            }
        }
        return ans;
    }
    public static int scoreOfString(String s) {
        int sum = 0;
        for (int i = 0; i+1 < s.length(); i++) {
            sum += Math.abs((int)s.charAt(i)-(int)s.charAt(i+1));

        }
        System.out.println(sum);
        return sum;
    }
    public static boolean canArrange(int[] arr, int k) {
        int left = 0,right = arr.length - 1,sum = 0;
        while (left < right){
            sum += arr[left++] + arr[right--];
            if (sum % k != 0){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6};
        int k = 10;
        System.out.println(canArrange(arr,k));
        scoreOfString("hello");
    }
}
