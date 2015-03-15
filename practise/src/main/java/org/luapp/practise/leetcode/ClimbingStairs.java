package org.luapp.practise.leetcode;

/**
 * Created by lumeng on 2015/3/14.
 */
public class ClimbingStairs {
    public static int climbStairs(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {

            int c1 = 1;
            int c2 = 2;
            int fn = 0;

            for (int i = 3; i <= n; i++) {
                fn = c1 + c2;
                c1 = c2;
                c2 = fn;
            }
            return fn;
        }
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(0));
        System.out.println(climbStairs(1));
        System.out.println(climbStairs(2));
        System.out.println(climbStairs(3));
        System.out.println(climbStairs(10));
    }
}
