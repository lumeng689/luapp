package org.luapp.practise.leetcode;

import java.util.List;

/**
 * Created by lum on 2015/3/13.
 */
public class PathSumII {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        return null;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);


//        System.out.println(hasPathSum(root, 22));
    }
}
