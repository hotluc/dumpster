package algorithm.tree;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        TreeNode.preOrder(root);
        flatten(root);
    }

    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int i) {
        if (root == null) {
            return 0;
        }
        int sum = i * 10 + root.val;
        if (root.left == null || root.right == null) {
            return sum;
        } else {
            return dfs(root.left, i) + dfs(root.right, i);
        }
    }

    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        ListNode dummy = new ListNode(root.val);
        flatten(root.left);
        dummy.next = new ListNode(root.left.val);
        flatten(root.right);
        dummy.next = new ListNode(root.right.val);
    }

    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while (cur.next != null) {
            if (set.contains(cur.next.val)) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    public static ListNode deleteDuplicates1(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    public static ListNode reverse(ListNode head) {
        ListNode cur = head, pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode pre = null, next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;

            pre = head;
            head = next;

        }
        return pre;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return null;
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    public ListNode deleteMiddle(ListNode head) {
        if (head == null||head.next==null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;
        while (fast!=null&&fast.next!=null){
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;

        }
        pre.next=pre.next.next;
        return head;
    }
}