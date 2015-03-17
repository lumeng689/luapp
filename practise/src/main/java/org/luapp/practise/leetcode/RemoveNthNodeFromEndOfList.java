package org.luapp.practise.leetcode;

/**
 * Created by lum on 2015/3/16.
 */
public class RemoveNthNodeFromEndOfList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        // 移动指示针
        ListNode nPointer = head;
        int i = 0;
        while (nPointer != null && i < n) {
            nPointer = nPointer.next;
            i++;
        }
        // 如果指定n大于队列长度，报错
        if (i != n) {
            System.out.println("error");
            return null;
        }

        ListNode pre = head;
        ListNode lPointer = head;
        while (nPointer != null) {
            nPointer = nPointer.next;
            pre = lPointer;
            lPointer = lPointer.next;
        }
        if(lPointer == head) {
            head = head.next;
        } else {
            pre.next = lPointer.next;
        }
        return head;
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
        ListNode head = new ListNode(1);
        ListNode temp = head;

        for (int i = 2; i <= 5; i++) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }

        print(head);
        print(removeNthFromEnd(head, -1));
    }
}
