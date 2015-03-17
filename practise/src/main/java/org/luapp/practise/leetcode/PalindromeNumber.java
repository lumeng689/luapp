package org.luapp.practise.leetcode;

/**
 * 判断是否是回文数字
 * <p/>
 * Created by lumeng on 2015/1/26.
 */
public class PalindromeNumber {
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }

        while (x != 0) {
            int left = x / div;
            int right = x % 10;

            if (left != right) {
                return false;
            }

            x = (x % div) / 10;
            div = div / 100;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(0));
        System.out.println(isPalindrome(12));
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(1234));
        System.out.println(isPalindrome(12321));
    }
}
