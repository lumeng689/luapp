package org.luapp.practise.leetcode;

/**
 * Created by lum on 2015/3/9.
 */
public class RomanToInteger {

//    public static char[] nums = new char[]{
//            'I',// 1
//            'V',// 5
//            'X',// 10
//            'L',// 50
//            'C',// 100
//            'D',// 500
//            'M'//  1000
//    };

    public static int romanToInt(String s) {
        if (s == null) {
            return 0;
        }
        char[] nums = s.trim().toUpperCase().toCharArray();
        int x = 0;

        for (int i = 0; i < nums.length; i++) {
            char c = nums[i];
            boolean nonLast = (i < (nums.length - 1));
            switch (c) {
                case 'M':// 1000
                    x = x + 1000;
                    break;
                case 'D':// 500
                    x = x + 500;
                    break;
                case 'C':// 100
                    if (nonLast) {
                        if (nums[i + 1] == 'M') {
                            x = x + 900;
                            i++;
                        } else if (nums[i+1] == 'D') {
                            x = x+400;
                            i++;
                        } else {
                            x = x+100;
                        }
                    } else {
                        x = x + 100;
                    }
                    break;
                case 'L':// 50
                    x = x + 50;
                    break;
                case 'X':// 10
                    if (nonLast) {
                        if (nums[i + 1] == 'C') {
                            x = x + 90;
                            i++;
                        } else if (nums[i+1] == 'L') {
                            x = x+40;
                            i++;
                        } else {
                            x = x+10;
                        }
                    } else {
                        x = x + 10;
                    }
                    break;
                case 'V':// 5
                    x = x + 5;
                    break;
                case 'I':// 1
                    if (nonLast) {
                        char c2 = nums[i + 1];
                        if (c2 == 'V') {
                            i = i + 1;
                            x = x + 4;
                        } else if (c2 == 'X') {
                            i = i + 1;
                            x = x + 9;
                        } else {
                            x = x + 1;
                        }
                    } else {
                        x = x + 1;
                    }
                    break;
            }
        }

        return x;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("I"));
        System.out.println(romanToInt("IV"));
        System.out.println(romanToInt("IX"));
        System.out.println(romanToInt("XVI"));
        System.out.println(romanToInt("CI"));
        System.out.println(romanToInt("MCMXCVI"));
//System.out.println(romanToInt(""));
//System.out.println(romanToInt(""));
//System.out.println(romanToInt(""));
//System.out.println(romanToInt(""));
    }
}
