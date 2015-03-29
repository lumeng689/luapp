package org.luapp.practise.leetcode;

import org.luapp.practise.leetcode.TreeNodeBuilder.TreeNode;

/**
 * Created by lumeng on 2015/3/28.
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length || preorder.length < 1) {
            return null;
        }

        // 前序遍历的第一个节点是根节点
        TreeNode root = new TreeNode(preorder[0]);

        if (preorder.length == 1) {
            return root;
        }

        int idx = findRoot(inorder, preorder[0]);

        if (idx == -1) {
            throw new RuntimeException("illegal");
        }

        if (idx > 0) {
            int[] leftpreorder = new int[idx];
            System.arraycopy(preorder, 1, leftpreorder, 0, idx);

            int[] leftinorder = new int[idx];
            System.arraycopy(inorder, 0, leftinorder, 0, idx);

            root.left = buildTree(leftpreorder, leftinorder);
        }

        if (idx < inorder.length) {

            int[] rightpreorder = new int[preorder.length - idx - 1];
            System.arraycopy(preorder, idx + 1, rightpreorder, 0, preorder.length - idx - 1);
            int[] rightinorder = new int[preorder.length - idx - 1];
            System.arraycopy(inorder, idx + 1, rightinorder, 0, preorder.length - idx - 1);

            root.right = buildTree(rightpreorder, rightinorder);
        }

        return root;
    }

    private static int findRoot(int[] inorder, int val) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == val) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        TreeNode root = buildTree(new int[]{1, 2, 4, 7, 3, 5, 6, 8}, new int[]{4, 7, 2, 1, 5, 3, 8, 6});
        System.out.println(root);
    }
}
