package org.luapp.practise.leetcode;

import java.util.*;

/**
 * Created by lumeng on 2015/3/12.
 */
public class LargestNumber {
    public static String largestNumber(int[] num) {

        for (int i = 0; i < num.length-1; i++) {
            for(int j = i+1;j < num.length;j++) {
                String s1 = num[i] + "" + num[j];
                String s2 = num[j] + "" + num[i];

                int ret = s1.compareTo(s2);
                if (ret < 0) {
                    int temp = num[i];
                    num[i] = num[j];
                    num[j] = temp;
                }
            }
        }

        if (num[0]==0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num.length; i++) {
            sb.append(num[i]);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        int[] num = new int[]{3, 30, 34, 5, 9};
        System.out.println(largestNumber(num));
        num = new int[]{0,0};;
        System.out.println(largestNumber(num));
    }

}
