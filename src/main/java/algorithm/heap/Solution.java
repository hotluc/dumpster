package algorithm.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Solution {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode mergeKLists(ArrayList<ListNode> list) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>(Collections.reverseOrder());
        for (ListNode h : list) {
            if (h != null) {
                heap.add(h);
            }

            if (heap.isEmpty()) {
                return null;
            }
            ListNode head = heap.poll();
            ListNode pre = head;
            if (pre.next != null) {
                heap.add(pre.next);
            }
            while (!heap.isEmpty()) {
                ListNode cur = heap.poll();
                pre.next = cur;
                pre = cur;
            }
        }
        return null;
    }
}
