package org.luapp.practise.leetcode;

/**
 * Created by lum on 2015/3/18.
 */
public class MergeTwoSortedLists {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode l3 = null;
        if (l1.val <= l2.val) {
            l3 = new ListNode(l1.val);
            l1 = l1.next;
        } else {
            l3 = new ListNode(l2.val);
            l2 = l2.next;
        }

        ListNode mergedList = l3;

        while (l1 != null || l2 != null) {

            if (l1 == null) {
                l3.next = l2;
                break;
            }

            if (l2 == null) {
                l3.next = l1;
                break;
            }

            if (l1.val <= l2.val) {
                l3.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                l3.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            l3 = l3.next;
        }

        return mergedList;
    }

    public static void print(ListNode r) {
        System.out.print(r.val);
        ListNode n = r.next;
        while (n != null) {
            System.out.print("->" + n.val);
            n = n.next;
        }
        System.out.println();
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

        print(r);

        ListNode r2 = new ListNode(2);

        next = r2;

        next.next = new ListNode(4);
        next = next.next;
        next.next = new ListNode(6);
        next = next.next;
        next.next = new ListNode(8);
        next = next.next;
        next.next = new ListNode(10);
        next = next.next;
        next.next = new ListNode(12);
        next = next.next;
        next.next = new ListNode(16);
        next = next.next;
        print(r2);
        print(mergeTwoLists(r, r2));
    }
}
