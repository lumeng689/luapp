package org.luapp.practise.leetcode;

/**
 * Created by lumeng on 2015/1/28.
 */
public class CompareVersionNumbers {

    public static int compareVersion(String version1, String version2) {
        int len1 = version1.length();
        int len2 = version2.length();

        int i = 0, j = 0;
        while (i < len1 || j < len2) {
            long a = 0, b = 0;
            while (i < len1 && version1.charAt(i) != '.') {
                a = a * 10 + version1.charAt(i) - '0';
                i++;
            }
            i++;
            while (j < len2 && version2.charAt(j) != '.') {
                b = b * 10 + version2.charAt(j) - '0';
                j++;
            }
            j++;

            if (a > b) {
                return 1;
            } else if (a < b) {
                return -1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(compareVersion("0.1", "1.1"));
        System.out.println(compareVersion("1.1", "1.2"));
        System.out.println(compareVersion("1.2", "13.37"));
    }
}
