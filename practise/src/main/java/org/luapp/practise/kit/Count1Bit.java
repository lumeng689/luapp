package org.luapp.practise.kit;

/**
 * Created by lum on 2015/3/9.
 */
public class Count1Bit {

    private static int count1(int x) {
        int count = 0;
        while (x > 0) {
            x = x & (x - 1);
            count++;
        }
        return count;
    }

    public static void print(int x) {
        System.out.println(x + "=" + Integer.toBinaryString(x) + ",number of 1: " + count1(x));
    }

    public static void main(String[] args) {
        print(999);
        print(998);
        print(1);
        print(0);
    }
}
