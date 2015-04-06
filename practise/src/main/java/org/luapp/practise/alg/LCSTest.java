package org.luapp.practise.alg;

/**
 * Created by lumeng on 2015/4/6.
 */
public class LCSTest {

    private static void lcsLength(String x,String y) {
        int m  = x.length();
        int n = y.length();

        int[][] b = new int[m+1][n+1];
        int[][] c = new int[m+1][n+1];

        for( int i = 1;i <= m;i++) {
            c[i][0] = 0;
        }
        for(int j = 1;j <= n;j++) {
            c[0][j] = 0;
        }

        for(int i = 1;i <= m;i++) {
            for(int j = 1;j <=n;j++) {

            }
        }
    }

    public static void main(String[] args) {

    }
}
