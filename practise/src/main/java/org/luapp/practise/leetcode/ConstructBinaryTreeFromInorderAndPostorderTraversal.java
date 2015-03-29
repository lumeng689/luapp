package org.luapp.practise.leetcode;

import org.luapp.practise.leetcode.TreeNodeBuilder.TreeNode;

/**
 * Created by lumeng on 2015/3/28.
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

    public static  TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder == null || inorder == null || postorder.length != inorder.length || postorder.length < 1) {
            return null;
        }

        int len = postorder.length;
        // 前序遍历的第一个节点是根节点
        TreeNode root = new TreeNode(postorder[len - 1]);

        if (postorder.length == 1) {
            return root;
        }

        int idx = findRoot(inorder, postorder[len -1]);

        if (idx == -1) {
            throw new RuntimeException("illegal");
        }

        if (idx > 0) {
            int[] leftinorder = new int[idx];
            System.arraycopy(inorder, 0, leftinorder, 0, idx);
            int[] leftpostorder = new int[idx];
            System.arraycopy(postorder, 0, leftpostorder, 0, idx);

            root.left = buildTree(leftinorder,leftpostorder);
        }

        if (idx < inorder.length) {
            int[] rightinorder = new int[postorder.length - idx - 1];
            System.arraycopy(inorder, idx + 1, rightinorder, 0, len - idx - 1);
            int[] rightpostorder = new int[len - idx - 1];
            System.arraycopy(postorder, idx, rightpostorder, 0, len - idx - 1);

            root.right = buildTree( rightinorder,rightpostorder);
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
        TreeNode root = buildTree( new int[]{4, 7, 2, 1, 5, 3, 8, 6},new int[]{7,4,2,5,8,6,3,1});
        System.out.println(root);
    }
}
