package org.luapp.practise.leetcode;

/**
 * Reverse digits of an integer.
 *
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 * <p/>
 * Created by lumeng on 2015/1/23.
 */
public class AddTwoNumbers {

    public static int reverse(int x) {

        // 一位数的直接返回
        if (x < 10 && x > -10) {
            return x;
        }

        long j = 0;

        while (x != 0) {
            int r = x % 10;
            if (r == 0 && j == 0) {
                //NOP
            } else {
                j = j * 10 + r;
            }

            x = x / 10;
        }

        if (j > Integer.MAX_VALUE || j < Integer.MIN_VALUE) {
            return 0;
        }

        return (int) j;
    }

    public static void main(String[] args) {
//        System.out.println(reverse(10));
        System.out.println(reverse(-321));
//        System.out.println(reverse(321));
//        System.out.println(reverse(100));
//        System.out.println(reverse(1000000003));
//        System.out.println(reverse(1534236469));
//        System.out.println(reverse(-2147483648));
    }
}
