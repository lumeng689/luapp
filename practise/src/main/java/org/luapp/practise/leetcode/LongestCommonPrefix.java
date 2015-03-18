package org.luapp.practise.leetcode;

/**
 * Created by lum on 2015/3/18.
 */
public class LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        if (strs.length == 1) {
            return strs[0];
        }

        StringBuilder prefix = new StringBuilder();
        int idx = 0;
        while(true) {

            if (idx >= strs[0].length()) {
                return prefix.toString();
            }

            char c = strs[0].charAt(idx);
            boolean match = true;
            for (int i = 1; i < strs.length; i++) {
                if (strs[i] == null || idx >= strs[i].length()) {
                    return prefix.toString();
                }
                if (strs[i].charAt(idx) != c)  {
                    match = false;
                    break;
                }
            }

            if (match) {
                prefix.append(c);
                idx++;
            } else {
                return  prefix.toString();
            }
        }
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"abc", "afg", "aqh"};
        System.out.println(longestCommonPrefix(strs));
        strs = new String[]{"aa", "a"};
        System.out.println(longestCommonPrefix(strs));
    }
}
