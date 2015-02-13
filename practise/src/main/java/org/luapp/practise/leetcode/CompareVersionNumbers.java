package org.luapp.practise.leetcode;

/**
 * Created by lumeng on 2015/1/28.
 */
public class CompareVersionNumbers {

    public static int compareVersion(String version1, String version2) {
        int len = version1.length() > version2.length()? version1.length():version2.length();

        char[] v1 = new char[len];
        char[] v2 = new char[len];

//        while(true) {
//
//        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(compareVersion("0.1","1.1"));
        System.out.println(compareVersion("1.1","1.2"));
        System.out.println(compareVersion("1.2","13.37"));
    }
}
