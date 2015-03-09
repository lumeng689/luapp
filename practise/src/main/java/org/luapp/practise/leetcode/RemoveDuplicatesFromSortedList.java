package org.luapp.practise.leetcode;

/**
 * Created by lum on 2015/3/9.
 */
public class RemoveDuplicatesFromSortedList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode deleteDuplicates(ListNode head) {

        ListNode temp = head;

        if (head == null) {
            return head;
        }

        ListNode next = temp.next;

        while (next != null) {
            if (temp.val == next.val) {
                next = next.next;
                temp.next = next;
            } else {
                temp = next;
                next = temp.next;
            }
        }

        return head;
    }

    public static void print(ListNode r) {
        System.out.print(r.val);
        ListNode n = r.next;
        while (n != null) {
            System.out.print("->" + n.val);
            n = n.next;
        }
    }

    public static void main(String[] args) {
        ListNode r = new ListNode(1);

        ListNode next = r;

        next.next = new ListNode(1);
        next = next.next;
        next.next = new ListNode(2);
        next = next.next;
        next.next = new ListNode(3);
        next = next.next;
        next.next = new ListNode(3);
        next = next.next;
        next.next = new ListNode(5);
        next = next.next;
        next.next = new ListNode(5);
        next = next.next;

        print(deleteDuplicates(r));
    }


}
