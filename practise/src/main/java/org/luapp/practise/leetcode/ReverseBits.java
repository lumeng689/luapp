package org.luapp.practise.leetcode;

/**
 * Created by lumeng on 2015/3/9.
 */
public class ReverseBits {

    // you need treat n as an unsigned value
    public static int reverseBits(int n) {
//        int j = 0;
//        int cnt = 0;
//
//        while(n > 0 || cnt < 32) {
//            int r = (n % 2);
//            j = (j*2 + r);
//            n = n>>1;
//            cnt++;
//        }
//
//        return j&0x0FFFFFFFF;

        int j = 0;

        for (int i = 0; i < 32; i++) {
            j <<= 1;
            j |= n & 1;
            n >>= 1;
        }

        return j & 0xFFFFFFFF;
    }

    public static void main(String[] args) {
        System.out.println(reverseBits(43261596));// should return 964176192
        System.out.println(reverseBits(964176192));
        System.out.println(reverseBits(0xFFFFFFFF));
    }
}
