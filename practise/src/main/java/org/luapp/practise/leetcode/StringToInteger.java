package org.luapp.practise.leetcode;

/**
 * Implement atoi to convert a string to an integer.
 * <p/>
 * Created by lumeng on 2015/1/26.
 */
public class StringToInteger {

    public static int atoi(String str) {
        if (str == null) {
            return 0;
        }

        int len = str.length();

        if (len <= 0) {
            return 0;
        }

        int idx = 0;
        char[] digits = str.toCharArray();

        // skip whitespace
        while (digits[idx] == ' ') {
            idx++;
        }

        // get sign
        boolean neg = false;
        int limit = Integer.MIN_VALUE;

        if (digits[idx] == '-') {
            neg = true;
            idx++;
        } else if (digits[idx] == '+') {
            idx++;
        }

        if (!neg) {
            limit = -Integer.MAX_VALUE;
        }

        int multmin = limit / 10;

        // 存放计算后的int值
        int num = 0;

        for (int i = idx; i < len; i++) {
            if (digits[i] < '0' || digits[i] > '9') {
                break;
            }

            // 在*10之前判断*10会不会溢出
            if (num < multmin) {
                return -limit;
            }

            num = num * 10;
            int digit = digits[i] - '0';

            if (num < limit + digit) {
                return -limit;
            }
            num -= digit;
        }

        if (!neg) {
            num = -num;
        }

        return num;
    }

    public static void main(String[] args) {
        System.out.println(atoi(""));
        System.out.println(atoi("+0"));
        System.out.println(atoi("-0"));
        System.out.println(atoi("+100"));
        System.out.println(atoi("-100"));
        System.out.println(atoi("2147483647"));
        System.out.println(atoi("2147483648"));
        System.out.println(atoi("-2147483648"));
        System.out.println(atoi("-2147483649"));
        System.out.println(atoi("  -0012a42"));
        System.out.println(atoi("9223372036854775809"));
        System.out.println(atoi("-9223372036854775809"));
    }
}
