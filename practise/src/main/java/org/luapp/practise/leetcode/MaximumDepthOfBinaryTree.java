package org.luapp.practise.leetcode;

/**
 * Created by lum on 2015/3/9.
 */
public class MaximumDepthOfBinaryTree {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        return Math.max(maxDepth(root.left) + 1,maxDepth(root.right) + 1);
    }

    public static void main(String[] args) {

    }
}
