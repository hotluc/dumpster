package algorithm.linklist;
// 复制带随机指针的链表
// 测试链接 : https://leetcode.cn/problems/copy-list-with-random-pointer
public class Code03_CopyListWithRandomPointer {
    // 不要提交这个类
    public static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node(int v) {
            val = v;
        }
    }
    // 提交如下的方法
    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head,next = null;
        // 1 -> 2 -> 3 -> ...
        // 变成 : 1 -> 1' -> 2 -> 2' -> 3 -> 3' -> ...
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = cur.next;
        }
        return head;
    }
}
