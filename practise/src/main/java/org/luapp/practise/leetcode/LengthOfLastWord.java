package org.luapp.practise.leetcode;

/**
 * Created by lum on 2015/3/12.
 */
public class LengthOfLastWord {
    public static int lengthOfLastWord(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }

        int i = s.length() - 1;
        int cnt = 0;
        boolean foundAlpha = false;
        while (i >= 0) {
            char c = s.charAt(i);
            if (c != ' ') {
                cnt++;
                foundAlpha = true;
            } else {
                if (foundAlpha) {
                    break;
                }
            }
            i--;
        }

        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("Hello World"));
        System.out.println(lengthOfLastWord("a "));
    }
}
