package org.luapp.practise.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lumeng on 2015/4/5.
 */
public class WordBreak {

    public static boolean wordBreak(String s, Set<String> dict) {

        if (s == null || s.length() == 0 || dict == null || dict.isEmpty()) {
            return false;
        }

        boolean[] res = new boolean[s.length() + 1];
        res[0] = true;

        for(int i =0;i < s.length();i++) {
            StringBuilder str = new StringBuilder(s.substring(0,i+1));
            for(int j =0;j<=i;j++) {
                if(res[j] && dict.contains(str.toString())) {
                    res[i+1]=true;
                    break;
                }
                str.deleteCharAt(0);
            }
        }

        return res[s.length()];
    }

    public static void main(String[] args) {

        Set<String> aa = new HashSet<>();
        aa.addAll(Arrays.asList(new String[]{"leet", "code"}));
        System.out.println(wordBreak("leetcode",aa));
    }
}
