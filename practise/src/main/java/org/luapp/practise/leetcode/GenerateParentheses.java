package org.luapp.practise.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lumeng on 2015/3/28.
 */
public class GenerateParentheses {

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();

        if (n <= 0) {
            return result;
        }

        String str = "";
        dfs(result, str, n, n);

        return result;
    }

    private static void dfs(List<String> result, String str, int left, int right) {
        if (left > right) {
            return;
        }
        if (left == 0 && right == 0) {
            result.add(str);
        }

        if (left > 0) {
            dfs(result, str + "(", left - 1, right);
        }
        if (right > 0) {
            dfs(result, str + ")", left, right - 1);
        }
    }

    public static List<String> generateParenthesis2(int n) {
        return null;
    }

    public static void print(List<String> list) {
        for (String s : list) {
            System.out.println(s);
        }

        System.out.println();
    }

    public static void main(String[] args) {
        print(generateParenthesis(-1));
        print(generateParenthesis(0));
        print(generateParenthesis(3));
        print(generateParenthesis(10));
    }
}
