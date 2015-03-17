package org.luapp.practise.leetcode;

/**
 * Created by lum on 2015/3/10.
 */
public class FactorialTrailingZeroes {

    /**
     * 求含质因子5的个数
     *
     * @param n
     * @return
     */
    public static int trailingZeroes(int n) {
        int ans = 0;
        long x = 5;
        long c = n;
        while (c >= x) {
            ans += n / x;
            x *= 5;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(trailingZeroes(5));
        System.out.println(trailingZeroes(100));
        System.out.println(trailingZeroes(1000));
        System.out.println(trailingZeroes(10000));
        System.out.println(trailingZeroes(2147483647));
    }
}
