package algorithm.linklist;
// 排序链表
// 要求时间复杂度O(n*logn)，额外空间复杂度O(1)，还要求稳定性
// 数组排序做不到，链表排序可以
// 测试链接 : https://leetcode.cn/problems/sort-list/
public class Code06_SortList {
    // 不要提交这个类
    public static class ListNode {
        public int val;
        public ListNode next;
    }

    // 提交如下的方法
    // 时间复杂度O(n*logn)，额外空间复杂度O(1)，有稳定性
    // 注意为了额外空间复杂度O(1)，所以不能使用递归
    // 因为mergeSort递归需要O(log n)的额外空间
    public static ListNode sortList(ListNode head) {
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        // l1...r1 每组的左部分
        // l2...r2 每组的右部分
        // next 下一组的开头
        // lastTeamEnd 上一组的结尾
        ListNode l1, r1, l2, r2, next, lastTeamEnd;
        for (int step = 1; step < n; step <<= 1) {

        }
        return head;
    }

    // l1...r1 -> null : 有序的左部分
    // l2...r2 -> null : 有序的右部分
    // 整体merge在一起，保证有序
    // 并且把全局变量start设置为整体的头，全局变量end设置为整体的尾
    public static void merge(ListNode l1, ListNode r1, ListNode l2, ListNode r2) {

    }
}
