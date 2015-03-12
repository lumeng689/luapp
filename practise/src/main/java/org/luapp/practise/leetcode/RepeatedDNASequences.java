package org.luapp.practise.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lum on 2015/3/12.
 */
public class RepeatedDNASequences {
    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<String>();
        if (s.length() < 10) {
            return result;
        }

        for (int i = 0;i < s.length() - 10;i ++ ) {
            int count = 0;
            for (int j = 10;j < s.length();j++) {
                if (s.charAt(i) == s.charAt(j-10)) {
                    count++;
                } else {
                    if (count >=10) {
                        result.add(s.substring(i,i+count+1));

                        System.out.println(s.substring(i,i+count+1));
                    }
                    break;
                }
            }
        }

        return result;
    }

    public static int[] getNextN(char[] pat) {
        int[] next = new int[pat.length];
        int i = 0, j = -1;
        next[0] = -1;
        while(i < next.length - 1) {
            if (j == -1 || pat[i] == pat[j]) {
                i++;
                j++;
                next[i] = j;
            } else {
                j  = next[j];
            }
        }

        for (int k = 0;k < pat.length;k++) {
            System.out.print(pat[k] + " ");
        }

        System.out.println();

        for (int k = 0;k < next.length;k++) {
            System.out.print(next[k] + " ");
        }

        System.out.println();

        return next;
    }

    public static int[] getNextN2(char[] pat) {
        int[] next = new int[pat.length];
        next[0] = -1;
        int index = 0;
        for (int i = 1;i < next.length;i++) {
            index = next[i-1];
            if (pat[i] == pat[index + 1] ) {
                next[i] = index+1;
            } else {
                next[i] = -1;
            }
        }

        for (int k = 0;k < pat.length;k++) {
            System.out.print(pat[k] + " ");
        }

        System.out.println();

        for (int k = 0;k < next.length;k++) {
            System.out.print(next[k] + " ");
        }

        System.out.println();

        return next;
    }

    public static void print(List<String> strs) {
        for (int i = 0; i < strs.size(); i++) {
            System.out.print(strs.get(i));
            if (i != 0) {
                System.out.print(",");
            }
        }


        System.out.println();
    }

    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        print(findRepeatedDnaSequences(s));
        getNextN(s.toCharArray());
        getNextN2(s.toCharArray());
    }
}
