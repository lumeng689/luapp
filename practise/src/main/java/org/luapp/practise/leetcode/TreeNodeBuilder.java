package org.luapp.practise.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lumeng on 2015/3/21.
 */
public class TreeNodeBuilder {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 将格式  {2,3,3,4,5,#,4} 转换为树结构
     *
     * @param str
     * @return
     */
    public static TreeNode build(String str) {
        // 不能为空，不能不带括号
        if (str == null || str.length() <= 2) {
            return null;
        }

        String[] nums = str.substring(1, str.length() - 1).split(",");

        List<TreeNode> list = new ArrayList<TreeNode>();

        for (int i = 0; i < nums.length; i++) {
            String num = nums[i];

            TreeNode node = null;
            if (!"#".equals(num)) {
                node = new TreeNode(Integer.valueOf(num));
            }

            list.add(node);
        }

        for (int i = 0; i < list.size(); i++) {
            TreeNode node = list.get(i);

            if (2 * i + 1 < list.size()) {
                node.left = list.get(2 * i + 1);
            }

            if (2 * i + 2 < list.size()) {
                node.right = list.get(2 * i + 2);
            }
        }

        return list.get(0);
    }

    public static void main(String[] args) {
        TreeNode root = build("{2,3,3,4,5,#,4}");

        System.out.println(root.hashCode());
    }
}
