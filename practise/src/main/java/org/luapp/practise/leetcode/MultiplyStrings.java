package org.luapp.practise.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lumeng on 2015/3/14.
 */
public class MultiplyStrings {

    public static String multiply(String num1, String num2) {
//        StringBuilder sb = new StringBuilder();

        if (num1 == null || "".equals(num1)) {
            return num2;
        }

        if (num2 == null || "".equals(num2)) {
            return num1;
        }

        List<String> tempResult = new ArrayList<String>();

        for (int i = num1.length() -1; i >=0; i--) {
            StringBuilder sb = new StringBuilder();
            int cf = 0;
            char c1 = num1.charAt(i);
            for (int k = num1.length() -1; k > i; k--) {
                sb.append(0);
            }
            for (int j = num2.length() -1; j >=0; j--) {
                char c2 = num2.charAt(j);
                int result = (c1 - '0') * (c2 - '0') + cf;
                cf = result / 10;
                sb.append(result % 10);
            }

            if (cf > 0) {
                sb.append(cf);
            }

            tempResult.add(sb.toString());
        }

        StringBuilder sb = new StringBuilder();

        int cf = 0;
        // max
        int max = tempResult.get(tempResult.size() - 1).length();
        int idx = 0;
        while (idx < max) {
            int r = cf;
            for (int i = 0; i < tempResult.size(); i++) {
                String s = tempResult.get(i);
                if (idx < s.length()) {
                    r += s.charAt(idx) - '0';
                }
            }

            sb.append(r % 10);
            cf = r / 10;
            idx++;
        }

        if (cf > 0) {
            sb.append(cf);
        }

        String ret = sb.reverse().toString();
        if (ret.charAt(0) == '0') {
            return "0";
        } else {
            return ret;
        }
    }

    public static void main(String[] args) {
//        System.out.println(multiply("11", "11"));
        System.out.println(multiply("33", "33"));
        System.out.println(multiply("12345", "67895"));
        System.out.println(multiply("9133", "0"));
    }
}
