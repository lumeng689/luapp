package org.luapp.practise.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lum on 2015/3/20.
 */
public class BinaryTreeLevelOrderTraversal {

    public static  class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (root == null) {
            return result;
        }

        LinkedList<TreeNode> tree = new LinkedList<TreeNode>();
        tree.add(root);

        while (true) {
            LinkedList<TreeNode> level = new LinkedList<TreeNode>();
            List<Integer> rowValue = new ArrayList<Integer>();

            while (!tree.isEmpty()) {
                TreeNode r = tree.pop();
                rowValue.add(r.val);
                if (r.left != null) {
                    level.add(r.left);
                }
                if (r.right != null) {
                    level.add(r.right);
                }
            }

            result.add(rowValue);

            if (level.isEmpty()) {
                break;
            }

            tree.addAll(level);
        }

        return result;
    }

    public static void print(List<List<Integer>> values) {

        for (int i = 0;i < values.size();i++) {
            List<Integer> value = values.get(i);
            for (int j = 0;j < value.size();j++) {
                System.out.print(" " + value.get(j));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        print(levelOrder(root));

    }
}
