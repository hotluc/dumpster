package algorithm.map.bfs;


import java.util.*;

public class Solution {
    public static int MAXN = 101;
    public static int MAXM = 101;
    public static int[][]queue = new int[MAXM*MAXN][2];
    public static int left,right;
    public static boolean[][] visited=new boolean[MAXM][MAXN];
    public static int[] move = new int[]{-1,0,1,0,-1};
    public static int maxDistance(int[][] grid){
        left=right=0;
        int n = grid.length;
        int m = grid[0].length;
        int sea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j]==1){
                    visited[i][j]=true;
                    queue[right][0]=i;
                    queue[right++][1]=j;

                }else {
                    visited[i][j]=false;
                    sea++;
                }
            }
        }
        if (sea==0||sea==n*m){
            return -1;
        }
        int level=0;
        while (left<right){
            level++;
            int size = right-left;
            for (int i = 0,x,y,nx,ny; i < size; i++) {
                x=queue[left][0];
                y=queue[left++][1];
                for (int j = 0; j < 4; j++) {
                    nx = x+move[j];
                    ny = y+move[j+1];
                    if (nx>=0&&nx<n&&ny>=0&&ny<m&&!visited[nx][ny]){
                        visited[nx][ny]=true;
                        queue[right][0]=nx;
                        queue[right++][1]=ny;
                    }
                }
            }
        }
        return level-1;
    }
    public static ListNode mergeTwoLists(ListNode list1,ListNode list2) {
        if (list1 == null||list2==null) {
            return list1==null?list2:list1;
        }
        ListNode head = list1.val<=list2.val?list1:list2;
        ListNode cur1 = head.next;
        ListNode cur2 = head==list1?list2:list1;
        ListNode pre = head;
        while (cur1!=null&&cur2!=null){
            if (cur1.val<=cur2.val){
                pre.next=cur1;
                cur1=cur1.next;
            }else {
                pre.next=cur2;
                cur2=cur2.next;
            }
            pre=pre.next;
        }
        pre.next=cur1!=null?cur1:cur2;
        return head;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans=null,cur=null;
        int sum,val,carry=0;

        while (l1!=null||l2!=null){
            sum=(l1==null?0:l1.val)+(l2==null?0:l2.val)+carry;
            val=sum%10;
            carry=sum/10;
            if (ans==null){
                ans=new ListNode(val);
                cur=ans;
            }else {
                cur.next=new ListNode(val);
                cur=cur.next;
            }
            l1=l1==null?null:l1.next;
            l2=l2==null?null:l2.next;
        }
        if (carry==1){
            cur.next=new ListNode(1);
        }
        return ans;
    }
    public boolean isSubPath(ListNode head, TreeNode root) {
        int m = 0;
        ListNode temp = head;
        while (temp!=null){
            m++;
            temp=temp.next;
        }
        int[] s2 = new int[m];
        m=0;
        while (head!=null){
            s2[m++]=head.val;
            head=head.next;
        }
        int[]next = nextArray(s2,m);
        return find(s2,next,root,0);
    }

    private int[] nextArray(int[] s, int m) {
        if (m==1){
            return new int[]{-1};
        }
        int[]next = new int[m];
        next[0]=-1;
        next[1]=0;
        int i=2,cn=0;
        while (i<m){
            if (s[i-1]==s[cn]){
                next[i++]=++cn;
            }else if (cn>0){
                cn=next[cn];
            }else {
                next[i++]=0;
            }
        }
        return next;
    }

    private boolean find(int[] s2, int[] next, TreeNode root, int i) {
        if (i==s2.length){
            return true;
        }
        if(root==null){
            return false;
        }
        while (i>=0&&root.val!=s2[i]){
            i=next[i];
        }
        return find(s2,next,root.left,i+1)||find(s2,next,root.right,i+1);
    }
    public static int minimumObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] distance = new int[m][n];
        int[] move = new int[]{-1,0,1,0,-1};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        ArrayDeque<int[]> deque = new ArrayDeque<>();
        deque.addFirst(new int[]{0,0});
        distance[0][0] = 0;
        while (!deque.isEmpty()){
            int[] record = deque.pollFirst();
            int x = record[0];
            int y = record[1];
            if (x == m-1&&y==n-1) {
                return distance[x][y];
            }
            for (int i = 0; i < 4; i++) {
                int nx = x+move[i],ny = y+move[i+1];
                if (nx>=0&&nx<m&&ny>=0&&ny<n&&distance[x][y]+grid[nx][ny]<distance[nx][ny]){
                    distance[nx][ny] = distance[x][y]+grid[nx][ny];
                    if (grid[nx][ny]==0){
                        deque.addFirst(new int[]{nx,ny});
                    }
                    else {
                        deque.addLast(new int[]{nx,ny});
                    }
                }
            }
        }
        return -1;
    }
    public static int minCost(int[][] grid) {
        int[][] move = new int[][]{{},{0,1},{0,-1},{1,0},{-1,0}};
        int m = grid.length;
        int n = grid[0].length;
        int[][] distance = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        ArrayDeque<int[]> deque = new ArrayDeque<>();
        deque.addFirst(new int[]{0,0});
        while (!deque.isEmpty()){
            int[] record = deque.pollFirst();
            int x = record[0];
            int y = record[1];
            if (x == m-1&&y==n-1) {
                return distance[x][y];
            }
            for (int i = 1; i <= 4; i++) {
                int nx = x+move[i][0],ny = y+move[i][1],weight=grid[x][y]!=i?1:0;
                if (nx>=0&&nx<m&&ny>=0&&ny<n&&distance[x][y]+weight<distance[nx][ny]){
                    distance[nx][ny] = distance[x][y]+weight;
                    if (grid[nx][ny]==1){
                        deque.addFirst(new int[]{nx,ny});
                    }
                    else {
                        deque.addLast(new int[]{nx,ny});
                    }
                }
            }
        }
        return 0;
    }
    public int[][] merge(int[][] intervals) {
        if (intervals.length==0){
            return new int[0][2];
        }
        Arrays.sort(intervals, Comparator.comparingInt(a->a[0]));
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            int l = interval[0], r = interval[1];
            if (merged.isEmpty() || merged.getLast()[1] < l) {
                merged.add(new int[]{l, r});
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], r);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{}};
        int[] nums = new int[]{2,0,2,1,1,0};
        Arrays.sort(intervals, Comparator.comparingInt(a->a[0]));
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

    }
    public static boolean hasCycle(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if (slow==fast){
                return true;
            }
        }
        return false;
    }
    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return slow;
            }
        }
        return null;
    }
    public static void flatten(TreeNode root) {

    }
}
