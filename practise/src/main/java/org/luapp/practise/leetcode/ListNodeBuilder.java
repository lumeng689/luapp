package org.luapp.practise.leetcode;

/**
 * Created by lumeng on 2015/3/28.
 */
public class ListNodeBuilder {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode build(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }

        ListNode head = new ListNode(values[0]);
        ListNode node = head;
        for (int i = 1;i < values.length;i++) {
            node.next = new ListNode(values[i]);
            node = node.next;
        }

        return head;
    }

    public static void print(ListNode head) {
        System.out.print(head.val);
        head = head.next;
        while (head != null) {
            System.out.print("->");
            System.out.print(head.val);
            head = head.next;
        }

        System.out.println();
    }

}
