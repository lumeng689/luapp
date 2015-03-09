package org.luapp.practise.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Given a binary tree, find its minimum depth.
 * <p/>
 * The minimum depth is the number of nodes along the shortest path
 * from the root node down to the nearest leaf node.
 * <p/>
 * Created by lumeng on 2015/2/10.
 */
public class MinimumDepthOfBinaryTree {

    private static final class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        if (root.left != null && root.right != null) {
            return Math.min(minDepth(root.left) + 1, minDepth(root.right) + 1);
        }

        if (root.left != null) {
            return minDepth(root.left) + 1;
        }

        return minDepth(root.right) + 1;
    }

    private static int childDepth(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            int left = 1 + childDepth(node.left);
            int right = 1 + childDepth(node.right);
            System.out.println("left is " + left);
            System.out.println("right is " + right);
            return Math.max(left, right);
        }
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(-1);

        Random r = new Random(System.currentTimeMillis());

        List<TreeNode> nodes = new ArrayList<TreeNode>();

        for (int i = 2; i < 100; i++) {
            nodes.add(new TreeNode(i));
        }

        root.left = new TreeNode(0);
        root.right = new TreeNode(1);

        TreeNode t1 = root.left;
        TreeNode t2 = root.right;

        for (int i = 2; i < 100; i++) {

            int q = r.nextInt();

            switch (q % 4) {
                case 0:
                    if (t1.left != null) {
                        t1 = t1.left;
                    }
                    t1.left = new TreeNode(i);
                    break;
                case 1:
                    if (t1.right != null) {
                        t1 = t1.right;
                    }
                    t1.right = new TreeNode(i);
                    break;
                case 2:
                    if (t2.left != null) {
                        t2 = t2.left;
                    }
                    t2.left = new TreeNode(i);
                    break;
                case 3:
                    if (t2.right != null) {
                        t2 = t2.right;
                    }
                    t2.right = new TreeNode(i);
                    break;
            }
        }

        System.out.println(minDepth(root));
    }
}
