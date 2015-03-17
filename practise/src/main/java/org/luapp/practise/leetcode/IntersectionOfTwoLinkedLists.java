package org.luapp.practise.leetcode;

/**
 * Created by lum on 2015/3/16.
 */
public class IntersectionOfTwoLinkedLists {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        int lenA = getLen(headA);
        int lenB = getLen(headB);

        if (lenA > lenB) {
            int cnt = lenA - lenB;
            while (cnt > 0) {
                headA = headA.next;
                cnt--;
            }
        } else if (lenA < lenB) {
            int cnt = lenB - lenA;
            while (cnt > 0) {
                headB = headB.next;
                cnt--;
            }
        }

        while (headA != null && headB != null) {
            if (headA.val == headB.val) {
                return headA;
            } else {
                headA = headA.next;
                headB = headB.next;
            }
        }

        return null;
    }

    public static int getLen(ListNode head) {
        if (head == null) {
            return 0;
        }
        int cnt = 0;
        while (head != null) {
            cnt++;
            head = head.next;
        }

        return cnt;
    }

    public static void print(ListNode head) {
        if (head == null) {
            System.out.println("null");
        } else {
            System.out.print(head.val);
            ListNode temp = head.next;
            while (temp != null) {
                System.out.print("->" + temp.val);
                temp = temp.next;
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        ListNode temp = head1;

        for (int i = 2; i <= 5; i++) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }

        print(head1);

        ListNode head2 = new ListNode(2);
        ListNode temp2 = head2;

        for (int i = 3; i <= 5; i++) {
            temp2.next = new ListNode(i);
            temp2 = temp2.next;
        }

        print(head2);

        print(getIntersectionNode(head1,head2));
    }
}
