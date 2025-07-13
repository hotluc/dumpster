package algorithm.practice.dumpster;

public class GetEncryptedString {
    public static String getEncryptedString(String str, int k) {
        char[] s = str.toCharArray();
        int n = s.length;
        for (int i = 0; i < n; i++) {
            swap(s, i, (i + k) % n);
        }
        return new String(s);
    }

    public static void swap(char[] s, int i, int j) {
        if (s[i] != s[j]) {
            System.out.println(s[i] + "=" + s[j]);
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }

    }
    public static int returnToBoundaryCount(int[] nums) {
        int ans = 0,sum=0;
        for (var num: nums) {
            sum += num;
            if (sum==0){
                ans++;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(getEncryptedString("dart", 3));
    }
}
