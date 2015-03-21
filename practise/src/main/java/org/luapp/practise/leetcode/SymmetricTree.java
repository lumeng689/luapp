package org.luapp.practise.leetcode;

import org.luapp.practise.leetcode.TreeNodeBuilder.TreeNode;

/**
 * Created by lum on 2015/3/19.
 */
public class SymmetricTree {

    public static boolean isSymmetric(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }

        if (t1 == null && t2 != null || t1 != null && t2 == null || t1.val != t2.val) {
            return false;
        }

        return isSymmetric(t1.left, t2.right) && isSymmetric(t1.right, t2.left);
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isSymmetric(root.left, root.right);
    }

    public static void main(String[] args) {
        TreeNode root = TreeNodeBuilder.build("{2,3,3,4,5,#,4}");
        System.out.println(isSymmetric(root));
    }
}
