package org.luapp.practise.leetcode;

/**
 * Created by lum on 2015/3/12.
 */
public class ReverseWordsInString {

    public static String reverseWords(String s) {
        if (s == null) {
            return s;
        }
        String[] arr = s.split(" ");
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (int i = arr.length - 1; i >= 0; i--) {
            if(!"".equals(arr[i])) {
                if (!first) {
                    sb.append(" ");
                }
                sb.append(arr[i]);
                first = false;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "the sky is blue";
//        System.out.println(reverseWords(s));

        s = "   a   b ";
        System.out.println(reverseWords(s));
    }
}
