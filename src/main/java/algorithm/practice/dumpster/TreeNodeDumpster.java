package algorithm.practice.dumpster;

import java.lang.reflect.Proxy;
import java.util.*;

public class TreeNodeDumpster {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (root == null) {
            return null;
        }
        if (depth == 1) {
            return new TreeNode(val, root, null);

        }
        if (depth == 2) {
            root.left = new TreeNode(val, root.left, null);
            root.right = new TreeNode(val, null, root.right);
        }
        else {
            root.left = addOneRow(root.left, val, depth - 1);
            root.right = addOneRow(root.right, val, depth - 1);
        }
        return root;
    }
    public static void flatten(TreeNode root) {
        List<TreeNode> ans = new LinkedList<>();
        preorder(root,ans);
        int n = ans.size();
        for (int i = 1; i < n; i++) {
            TreeNode prev = ans.get(i-1),curr = ans.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }
    public static void preorder(TreeNode root,List<TreeNode> ans) {
        if (root != null) {
            ans.add(root);
            preorder(root.left, ans);
            preorder(root.right, ans);
        }
    }
    public static TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private static TreeNode build(int[] nums, int left, int right) {
        if (left > right)
            return null;

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);

        return root;
    }
    public static boolean isSymmetric(TreeNode root) {
        List<Integer> ans1 = new ArrayList<>();
        List<Integer> ans2 = new ArrayList<>();
        in(root,ans1);
        out(root,ans2);
        for (int i = 0; i < ans1.size(); i++) {
            if (!Objects.equals(ans1.get(i), ans2.get(i))) {
                return false;
            }
        }
        return true;
    }
    public static void in (TreeNode root,List<Integer> ans) {
        if (root != null){
            ans.add(root.val);
            in(root.left,ans);
            in(root.right,ans);
        }
    }
    public static void out (TreeNode root,List<Integer> ans) {
        if (root != null){
            ans.add(root.val);
            out(root.right,ans);
            out(root.left,ans);
        }

    }
    public static TreeNode increasingBST(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        inorderTraversal(root, ans);
        TreeNode dummyNode = new TreeNode(-1),currentNode = dummyNode;
        for (int val : ans) {
            currentNode.right = new TreeNode(val);
            currentNode = currentNode.right;
        }
        return dummyNode.right;
    }
    public static void inorderTraversal(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left,ans);
        ans.add(root.val);
        inorderTraversal(root.right,ans);
    }
    public static boolean isPowerTwo(int val){
        return (val&-val)==val;
    }
    public static void main(String[] args) {
    }
}