package algorithm.authenticator;
// 判断一个数字是否是若干数量(数量>1)的连续正整数的和

public class Code03_IsSumOfConsecutiveNumbers {
    public static boolean is1(int num){
        for (int start = 1,sum; start <= num; start++) {
            sum = start;
            for (int i = start+1; i <= num; i++) {
                if (sum+i>num){
                    break;
                }
                if (sum+i == num){
                    return true;
                }
                sum+=i;
            }
        }
        return false;
    }

}
