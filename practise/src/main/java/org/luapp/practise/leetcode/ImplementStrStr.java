package org.luapp.practise.leetcode;

/**
 * Created by lumeng on 2015/3/21.
 */
public class ImplementStrStr {

    public static int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }

        if (haystack.length() < needle.length()) {
            return -1;
        }

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            boolean match = true;
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    match = false;
                    break;
                }
            }

            if (match) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(strStr("", ""));
        System.out.println(strStr("aaa", "a"));
        System.out.println(strStr("aaa", "b"));
        System.out.println(strStr("aaa", "aaa"));
        System.out.println(strStr("mississippi", "issipi"));
        System.out.println(strStr("mississippi", "issippi"));
    }
}
