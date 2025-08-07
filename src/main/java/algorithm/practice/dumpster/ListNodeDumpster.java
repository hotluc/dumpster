package algorithm.practice.dumpster;

public class ListNodeDumpster {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public static class Node{
        int val;
        Node next;
        Node random;
        public Node(int x) {
            val = x;
            next = null;
            random = null;
        }
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode a = headA, b = headB;
        int diff = 0;
        while (a.next != null) {
            a = a.next;
            diff++;
        }
        while (b.next != null) {
            b = b.next;
            diff--;
        }
        if (a != b) {
            return null;
        }
        if (diff > 0) {
            a = headA;
            b = headB;
        } else {
            a = headB;
            b = headA;
        }
        diff = Math.abs(diff);
        while (diff-- > 0) {
            a = a.next;
        }
        while (a != b) {
            a = a.next;
            b = b.next;
        }
        return a;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head, end = teamEnd(start,k);
        if (end == null) {
            return head;
        }
        head = end;
        reverse(start, end);
        ListNode lastTeamEnd = start;
        while (lastTeamEnd.next != null) {
            start = lastTeamEnd.next;
            end = teamEnd(start,k);
            if (end == null) {
                return head;
            }
            reverse(start, end);
            lastTeamEnd.next = end;
            lastTeamEnd = start;
        }
        return head;
    }
    public static ListNode teamEnd(ListNode s,int k) {
        while (--k!=0&&s!= null) {
            s = s.next;
        }
        return s;
    }
    public static void reverse(ListNode s,ListNode e) {
        e=e.next;
        ListNode prev = null,cur = s;
        while (cur != e) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        s.next = e;
    }
    public static void reverse1(ListNode s,ListNode e) {
        ListNode prev = null,cur = s;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
    }
    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        // 1 -> 2 -> 3 -> ...
        // 变成 : 1 -> 1' -> 2 -> 2' -> 3 -> 3' -> ...
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node copy = null;
        // 利用上面新老节点的结构关系，设置每一个新节点的random指针
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            copy.random = cur.random != null ? cur.random.next : null;
            cur = next;
        }
        Node ans = head.next;
        cur = head;
        // 新老链表分离 : 老链表重新连在一起，新链表重新连在一起
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next != null ? next.next : null;
            cur = next;
        }
        // 返回新链表的头节点
        return ans;
    }
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
        ListNode l1,l2,r1,r2,next,lastTeamEnd;
        for (int step = 1; step < n; step<<=1) {
            l1 = head;
            r1 = findEnd(l1,step);
            l2 = r1.next;
            r2 = findEnd(r1,step);
            next = r2.next;
            r1.next = null;
            r2.next = null;
            merge(l1,l2,r1,r2);
            head = start;
            lastTeamEnd = end;
            while (next!=null) {
                l1 = next;
                r1 = findEnd(l1,step);
                l2 = r1.next;
                if (l2==null){
                    lastTeamEnd.next = l1;
                    break;
                }
                r2 = findEnd(r1,step);
                next = r2.next;
                r1.next = null;
                r2.next = null;
                merge(l1,l2,r1,r2);
                lastTeamEnd.next = start;
                lastTeamEnd = end;
            }
        }
        return head;
    }
    public static ListNode findEnd(ListNode s, int k) {
        while (s.next != null&&--k!=0) {
            s = s.next;
        }
        return s;
    }
    public static ListNode start,end;
    public static void merge(ListNode l1,ListNode r1,ListNode l2, ListNode r2) {
        ListNode pre;
        if (l1.val<=l2.val) {
            start = l1;
            pre = l1;
            l1 = l1.next;
        }
        else {
            start = l2;
            pre = l2;
            l2 = l2.next;
        }
        while (l1!=null&&l2!=null) {
            if (l1.val<=l2.val) {
                pre.next = l1;
                pre = l1;
                l1 = l1.next;
            }
            else {
                pre.next = l2;
                pre = l2;
                l2 = l2.next;
            }
        }
        if (l1!=null) {
            pre.next = l1;
            end = l1;
        }else {
            pre.next = l2;
            end = l2;
        }

    }
}