package org.luapp.practise.leetcode;

/**
 * Created by lum on 2015/3/10.
 */
public class NumberOf1Bits {

    private static int hammingWeight(int i) {
//        int count = 0;
//        x = x & 0xFFFFFFFF;
//        do {
//            int f = x & 1;
//            if (f == 1) {
//                count++;
//            }
//            x >>= 1;
//        } while (x > 0);
//        return count;
        i = i - ((i >>> 1) & 0x55555555);
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
        i = (i + (i >>> 4)) & 0x0f0f0f0f;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        return i & 0x3f;
    }

    public static void print(int x) {
        System.out.println(x + "=" + Integer.toBinaryString(x) + ",number of 1: " + hammingWeight(x));
    }

    public static void main(String[] args) {
        print(999);
        print(998);
        print(1);
        print(0);
        print(0xFFFFFFFF);
        print(-1);
    }
}
