package algorithm.linklist;
// 按值传递、按引用传递
// 从堆栈角度解释链表节点
// 以堆栈视角来看链表反转
public class ListReverse {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
        // 反转单链表测试链接 : https://leetcode.cn/problems/reverse-linked-list/
        public static ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode next = null;
            while (head != null) {
                next = head.next;
                head.next = prev;
                prev = head;
                head = next;
            }
            return prev;
        }
    }
}
