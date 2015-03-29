package org.luapp.practise.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lumeng on 2015/3/28.
 */
public class RestoreIPAddresses {

    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();

        return result;
    }

    private static void print(List<String> result) {
        for(String s : result) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        print(restoreIpAddresses("25525511135"));
    }
}
