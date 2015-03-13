package org.luapp.practise.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lum on 2015/3/13.
 */
public class WordLadder {
    public static int ladderLength(String start, String end, Set<String> dict) {
        return 0;
    }

    public static void main(String[] args) {
        String start = "hit";
        String end = "cog";
        Set<String> dict = new HashSet<String>();
        dict.add("hot");
        dict.add("dot");
        dict.add("dog");
        dict.add("lot");
        dict.add("log");

        System.out.println(ladderLength(start, end, dict));
    }
}
