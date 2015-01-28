package org.luapp.practise.leetcode;

/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * For example:
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 *
 * Created by lumeng on 2015/1/27.
 */
public class ExcelSheetColumnTitle {

    public static String convertToTitle(int n) {

        char[] alpha = new char[]{'Z','A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y'};

        if (n <= 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            int remain = n % 26;
            sb.append(alpha[remain]);

            n = n / 26;
            if (remain == 0) {
                n = n-1;
            }
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
//        System.out.println(convertToTitle(-1));
        System.out.println(convertToTitle(1));
        System.out.println(convertToTitle(2));
        System.out.println(convertToTitle(3));
        System.out.println(convertToTitle(4));
        System.out.println(convertToTitle(5));
        System.out.println(convertToTitle(6));
        System.out.println(convertToTitle(7));
        System.out.println(convertToTitle(8));
        System.out.println(convertToTitle(9));
        System.out.println(convertToTitle(10));
        System.out.println(convertToTitle(11));
        System.out.println(convertToTitle(12));
        System.out.println(convertToTitle(13));
        System.out.println(convertToTitle(14));
        System.out.println(convertToTitle(15));
        System.out.println(convertToTitle(16));
        System.out.println(convertToTitle(17));
        System.out.println(convertToTitle(18));
        System.out.println(convertToTitle(19));
        System.out.println(convertToTitle(20));
        System.out.println(convertToTitle(21));
        System.out.println(convertToTitle(22));
        System.out.println(convertToTitle(23));
        System.out.println(convertToTitle(24));
        System.out.println(convertToTitle(25));
        System.out.println(convertToTitle(26));
        System.out.println(convertToTitle(27));
        System.out.println(convertToTitle(28));
        System.out.println(convertToTitle(51));
        System.out.println(convertToTitle(52));
        System.out.println(convertToTitle(53));
    }
}
