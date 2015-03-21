package org.luapp.practise.leetcode;

import java.util.ArrayList;
import java.util.List;

import org.luapp.practise.leetcode.TreeNodeBuilder.TreeNode;

/**
 * Created by lumeng on 2015/3/21.
 */
public class BinaryTreeLevelOrderTraversalII {

    private static void traverseLevel(List<List<Integer>> result, List<TreeNode> nodes) {
        List<Integer> items = new ArrayList<Integer>();

        List<TreeNode> nextLevel = new ArrayList<TreeNode>();

        for (TreeNode node : nodes) {
            items.add(node.val);
            if (node.left != null) {
                nextLevel.add(node.left);
            }
            if (node.right != null) {
                nextLevel.add(node.right);
            }
        }

        if (!nextLevel.isEmpty()) {
            traverseLevel(result, nextLevel);
        }

        result.add(items);
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (root == null) {
            return result;
        }

        List<TreeNode> nodes = new ArrayList<TreeNode>();
        nodes.add(root);

        traverseLevel(result, nodes);

        return result;
    }

    public static void print(List<List<Integer>> values) {

        for (int i = 0; i < values.size(); i++) {
            List<Integer> value = values.get(i);
            for (int j = 0; j < value.size(); j++) {
                System.out.print(" " + value.get(j));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        print(levelOrderBottom(TreeNodeBuilder.build("{3,9,20,#,#,15,7}")));
    }
}
