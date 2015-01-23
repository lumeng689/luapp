package org.luapp.practise.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Question1  Two Sum
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution.
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 * <p/>
 * Created by lumeng on 2015/1/23.
 */
public class TwoSum {

    public static int[] twoSum(int[] numbers, int target) {
        int[] ret = new int[2];

        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0;i < numbers.length;i++) {
            map.put(numbers[i],i);
        }

        for (int i = 0;i < numbers.length;i++) {
            Integer idx = map.get(target - numbers[i]);
            if (idx != null && i != idx) {
                ret[0] = i+1;
                ret[1] = idx+1;
                break;
            }
        }

        System.out.println("index1="+ret[0]);
        System.out.println("index2="+ret[1]);

        return ret;
    }

    private static int findIn(int[] numbers,int start,int end,int key) {
        for (int i = start;i < end;i++) {
            if (numbers[i] == key) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{2, 7, 11, 15};
        int target = 9;
        twoSum(numbers, target);

        numbers = new int[]{3,2,4};
        target = 6;
        twoSum(numbers, target);

        numbers = new int[]{0,4,3,0};
        target = 0;
        twoSum(numbers, target);
    }
}
