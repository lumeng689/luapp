package org.luapp.practise.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lum on 2015/3/10.
 */
public class PascalTriangle {

    private static int c(int n, int m) {
        // 公式 n!/(m!(n-m)!)
        return 0;
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<Integer>();
            List<Integer> preList = null;
            if (i > 0) {
                preList = lists.get(i - 1);
            }

            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i) {
                    list.add(1);
                } else {
                    list.add(preList.get(j - 1) + preList.get(j));
                }
            }
            lists.add(list);
        }
        return lists;
    }

    private static void print(List<List<Integer>> lists) {
        for (int i = 0; i < lists.size(); i++) {
            List<Integer> list = lists.get(i);
            for (int j = 0; j < list.size(); j++) {
                if (j != 0) {
                    System.out.print(",");
                }
                System.out.print(list.get(j));
            }
            System.out.println();
        }
        System.out.println("------------------------");
    }

    public static void main(String[] args) {
        print(generate(0));
        print(generate(1));
        print(generate(2));
        print(generate(5));
        print(generate(10));
    }
}
