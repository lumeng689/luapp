package org.luapp.practise.leetcode;

/**
 * Created by lumeng on 2015/3/14.
 */
public class SumRootToLeafNumbers {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static int dfs(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return sum * 10 + root.val;
        }

        int tmp = 0;
        if (root.left != null) {
            tmp += dfs(root.left, sum * 10 + root.val);
        }

        if (root.right != null) {
            tmp += dfs(root.right, sum * 10 + root.val);
        }

        return tmp;
    }

    public static int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return root.val;
        }

        return dfs(root.left, root.val) + dfs(root.right, root.val);
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        System.out.println(sumNumbers(root));

        root = new TreeNode(4);
        root.left = new TreeNode(9);
        root.left.right = new TreeNode(1);
        root.right = new TreeNode(0);
        System.out.println(sumNumbers(root));
    }
}
