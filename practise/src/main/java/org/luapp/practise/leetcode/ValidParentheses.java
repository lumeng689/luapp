package org.luapp.practise.leetcode;

import java.util.Stack;

/**
 * Created by lum on 2015/3/18.
 */
public class ValidParentheses {

    public static boolean isValid(String s) {

        if (s == null || s.length() == 0 || s.length() % 2 == 1) {
            return false;
        }

        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char c2 = stack.pop();
                if (c2=='('&&c==')' || c2=='[' && c==']' || c2=='{' && c == '}') {
                    continue;
                } else {
                    return false;
                }
            }
        }

        if (!stack.isEmpty()) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(]"));
        System.out.println(isValid("([)]"));
    }
}
