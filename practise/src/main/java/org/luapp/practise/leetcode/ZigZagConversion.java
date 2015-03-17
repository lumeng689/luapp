package org.luapp.practise.leetcode;

/**
 * Created by lum on 2015/3/9.
 */
public class ZigZagConversion {

    public static String convert(String s, int nRows) {

        if (s == null || "".equals(s) || nRows == 0) {
            return "";
        }

        if (nRows == 1) {
            return s;
        }

        int size = 2 * nRows - 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nRows; i++) {
            for (int j = i; j < s.length(); j += size) {
                sb.append(s.charAt(j));
                if (i != 0 && i != nRows - 1 && j + size - 2 * i < s.length()) {
                    sb.append(s.charAt(j + size - 2 * i));
                }
            }
        }

        return sb.toString();
    }

    public static String convert2(String s, int nRows) {

        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3));
    }
}
