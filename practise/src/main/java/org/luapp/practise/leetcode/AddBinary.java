package org.luapp.practise.leetcode;

/**
 * Created by lum on 2015/3/12.
 */
public class AddBinary {

    public static String addBinary(String a, String b) {

        if (a == null) {
            return b;
        }

        if (b == null) {
            return a;
        }

        StringBuilder sb = new StringBuilder();

        int i = a.length() - 1;
        int j = b.length() - 1;

        int c = 0;
        while (i >= 0 || j >= 0) {
            int c1 = i >= 0 ? a.charAt(i) - '0' : 0;
            int c2 = j >= 0 ? b.charAt(j) - '0' : 0;

            int x = c1 + c2 + c;
            if (x > 1) {
                sb.append(x % 2);
                c = x / 2;
            } else {
                c = 0;
                sb.append(x);
            }

            i--;
            j--;
        }

        if (c == 1) {
            sb.append(c);
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(addBinary("11", "1"));
        System.out.println(addBinary("11", "11"));
    }
}
