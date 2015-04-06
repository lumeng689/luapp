package org.luapp.practise.leetcode;

/**
 * Created by lumeng on 2015/3/28.
 */
public class SwapNodesInPairs {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode swapPairs(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummpy = new ListNode(0);
        ListNode ret = head;
        ListNode cur = head.next;
        while(ret!= null && ret.next!= null) {
            ListNode next = ret.next.next;
            cur.next = ret.next;
            cur = cur.next;
        }

        return null;
    }

    public static void print(ListNode head) {
        System.out.print(head.val);
        head = head.next;
        while (head != null) {
            System.out.print("->");
            System.out.print(head.val);
        }

        System.out.println();
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4};
        ListNode head = new ListNode(a[0]);

        ListNode node = head;

        for (int i = 1; i < a.length; i++) {
            node.next = new ListNode(a[i]);
            node = node.next;
        }

        print(swapPairs(head));
    }
}
