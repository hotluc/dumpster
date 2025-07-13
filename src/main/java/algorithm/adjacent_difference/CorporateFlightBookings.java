package algorithm.adjacent_difference;

import java.util.Arrays;
import java.util.Comparator;

// 航班预订统计
// 这里有n个航班，它们分别从 1 到 n 进行编号。
// 有一份航班预订表bookings ，
// 表中第i条预订记录bookings[i] = [firsti, lasti, seatsi]
// 意味着在从 firsti到 lasti
//（包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi个座位。
// 请你返回一个长度为 n 的数组answer，里面的元素是每个航班预定的座位总数。
// 测试链接 : https://leetcode.cn/problems/corporate-flight-bookings/
public class CorporateFlightBookings {
    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] cnt = new int[n+2];
        for (int[] booking : bookings) {
            cnt[booking[0]]+=booking[2];
            cnt[booking[1]+1]-=booking[2];
        }
        for (int i = 1; i <= cnt.length; i++) {
            cnt[i] += cnt[i-1];
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = cnt[i+1];
        }
        return res;
    }
}
