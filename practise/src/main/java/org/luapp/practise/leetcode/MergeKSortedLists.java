package org.luapp.practise.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.luapp.practise.leetcode.ListNodeBuilder.ListNode;

/**
 * Created by lumeng on 2015/3/28.
 */
public class MergeKSortedLists {

    public static ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0) {
            return null;
        }

        if (lists.size() == 1) {
            return lists.get(0);
        }

        return dc(lists, 0, lists.size() - 1);
    }

    public static ListNode dc(List<ListNode> lists, int l, int h) {
        if (l < h) {
            int m = (l + h) / 2;
            return merge2Lists(dc(lists, l, m), dc(lists, m + 1, h));
        }

        return lists.get(l);
    }

    public static ListNode merge2Lists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        if (l1 != null) {
            cur.next = l1;
        }

        if (l2 != null) {
            cur.next = l2;
        }

        return dummy.next;
    }

    public static ListNode mergeKLists1(List<ListNode> lists) {

        if (lists == null || lists.size() == 0) {
            return null;
        }

        if (lists.size() == 1) {
            return lists.get(0);
        }

        ListNode minNode = null;
        int idx = -1;

        for (int i = 0; i < lists.size(); i++) {
            ListNode node = lists.get(i);
            if (node != null) {
                if (minNode == null || node.val < minNode.val) {
                    idx = i;
                    minNode = node;
                }
            }
        }

        if (idx == -1) {
            return null;
        }

        lists.set(idx, minNode.next);
        minNode.next = mergeKLists(lists);
        return minNode;

    }

    public static ListNode mergeKLists2(List<ListNode> lists) {
        if (lists == null || lists.size() == 0) {
            return null;
        }

        if (lists.size() == 1) {
            return lists.get(0);
        }

        ListNode minNode = null;
//        int idx = -1;
//
//        for(int i = 0;i < lists.size();i++) {
//            ListNode node = lists.get(i);
//            if (node != null) {
//                if (minNode == null || node.val < minNode.val) {
//                    idx = i;
//                    minNode = node;
//                }
//            }
//        }
//
//        if (idx == -1) {
//            return null;
//        }
//
//        lists.set(idx, minNode.next);

        Iterator<ListNode> it = lists.iterator();

        while (it.hasNext()) {
            ListNode node = it.next();
            if (node == null) {
                it.remove();
            } else {
                if (minNode == null || node.val < minNode.val) {
                    minNode = node;
                }
            }
        }

        ListNode head = minNode;
        minNode = minNode.next;
        head.next = mergeKLists(lists);
        return head;
    }

    public static void main(String[] args) {

        List<ListNode> lists = new ArrayList<ListNode>();
        lists.add(ListNodeBuilder.build(new int[]{1, 3, 5, 7, 9}));
        lists.add(ListNodeBuilder.build(new int[]{2, 4, 6, 8, 10}));
        lists.add(ListNodeBuilder.build(new int[]{7,9,11,13,15,17}));
        lists.add(ListNodeBuilder.build(new int[]{3, 4, 5, 6, 7, 8, 9}));
        lists.add(ListNodeBuilder.build(new int[]{10, 11, 12}));

//        ListNodeBuilder.print(mergeKLists(lists));

        lists = new ArrayList<ListNode>();
        lists.add(null);
        lists.add(ListNodeBuilder.build(new int[]{1}));
        ListNodeBuilder.print(mergeKLists(lists));

//        ListNodeBuilder.print(merge2Lists(lists.get(0), lists.get(1)));
    }
}
