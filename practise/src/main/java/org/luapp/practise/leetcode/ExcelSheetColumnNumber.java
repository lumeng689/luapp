package org.luapp.practise.leetcode;

/**
 * Related to question Excel Sheet Column Title
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 * For example:
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 *
 * Created by lumeng on 2015/1/27.
 */
public class ExcelSheetColumnNumber {

    public static int titleToNumber(String s) {
        char[] alphas = s.toCharArray();

        int num = 0;

        for (int i = 0;i < alphas.length;i++) {
            num = num * 26 + (alphas[i] - 'A' + 1);
        }

        return num;
    }

    public static void main(String[] args) {
        System.out.println(titleToNumber("A"));
        System.out.println(titleToNumber("B"));
        System.out.println(titleToNumber("Z"));
        System.out.println(titleToNumber("AA"));
        System.out.println(titleToNumber("AZ"));
        System.out.println(titleToNumber("BA"));
    }
}
