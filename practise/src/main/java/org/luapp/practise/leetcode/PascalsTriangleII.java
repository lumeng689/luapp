package org.luapp.practise.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lum on 2015/3/18.
 */
public class PascalsTriangleII {
    public static List<Integer> getRow(int rowIndex) {

        List<Integer> result = new ArrayList<Integer>(rowIndex + 1);
        for (int i = 0; i <= rowIndex; i++) {
            result.add(0);
        }
        result.set(0, 1);
        for (int i = 1; i <= rowIndex; i++) {
            result.set(i, 1);
            for (int j = i - 1; j > 0; j--) {
                result.set(j, result.get(j) + result.get(j - 1));
            }
        }

        return result;
    }

    public static void print(List<Integer> row) {
        if (row == null || row.size() == 0) {
            return;
        }

        for (int i = 0; i < row.size(); i++) {
            if (i != 0) {
                System.out.print(",");
            }
            System.out.print(row.get(i));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        print(getRow(3));
    }
}
