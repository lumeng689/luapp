package org.luapp.practise.leetcode;

import java.util.LinkedList;

/**
 * Created by lum on 2015/3/11.
 */
public class MinStack {

    private LinkedList<Integer> vals = new LinkedList<Integer>();
    private LinkedList<Integer> minVals = new LinkedList<Integer>();

    public void push(int x) {
        vals.push(x);

        if (minVals.isEmpty()) {
            minVals.push(x);
        } else {
            if (x <= minVals.getFirst()) {
                minVals.push(x);
            }
        }
    }

    public void pop() {
        if (vals.isEmpty()) {
            return;
        }
        int x = vals.removeFirst();
        System.out.println("pop : " + x);
        if (!minVals.isEmpty()) {
            if (x == minVals.getFirst()) {
                minVals.removeFirst();
            }
        }
    }

    public int top() {
        return vals.getFirst();
    }

    public int getMin() {
        if (minVals.isEmpty()) {
            return -1;
        }
        return minVals.getFirst();
    }

    public static void main(String[] args) {

        MinStack s = new MinStack();

        s.push(6);
        s.push(1);
        s.push(5);
        s.push(2);
        s.push(1);

        System.out.println("min is:" + s.getMin());
        s.pop();
        System.out.println("min is:" + s.getMin());
        s.pop();
        System.out.println("min is:" + s.getMin());
        s.pop();
        System.out.println("min is:" + s.getMin());
        s.pop();
        System.out.println("min is:" + s.getMin());
        s.pop();
        System.out.println("min is:" + s.getMin());
        s.pop();
        System.out.println("min is:" + s.getMin());

    }
}
