package org.luapp.practise.leetcode;

/**
 * Created by lum on 2015/3/13.
 */
public class ValidPalindrome {
    public static boolean isPalindrome(String s) {

        if (s == null || "".equals(s)) {
            return true;
        }

        int i = 0,j = s.length() -1;
        boolean ret = true;

        char[] chars = s.toLowerCase().toCharArray();

        while (i < j) {

            if (!(chars[i] >= '0' && chars[i] <='9' || chars[i] >= 'a' && chars[i] <= 'z')) {
                i++;
                continue;
            }
            if (!(chars[j] >= '0' && chars[j] <='9' || chars[j] >= 'a' && chars[j] <= 'z')) {
                j--;
                continue;
            }

            if (chars[i] == chars[j]) {
                i++;
                j--;
            } else {
                ret = false;
                break;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));
        System.out.println(isPalindrome("1a2"));
    }
}
