package org.luapp.practise.alg;

/**
 * 算法导论 DP 切割钢条
 * <p/>
 * Created by lumeng on 2015/4/5.
 */
public class CutRodTest {

    private static int[] PROFIT = new int[]{-1, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

    /**
     * 递归版本
     *
     * @param p
     * @param n
     * @return
     */
    private static int cutRod(int[] p, int n) {
        if (n == 0) {
            return 0;
        }

        int q = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            q = Math.max(q, p[i] + cutRod(p, n - i));
        }

        return q;
    }

    private static int memoizedCutRod(int[] p, int n) {
        int[] r = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            r[i] = Integer.MIN_VALUE;
        }

        return memoizedCutForAux(p, n, r);
    }

    private static int memoizedCutForAux(int[] p, int n, int[] r) {
        if (r[n] >= 0) {
            return r[n];
        }

        int q = Integer.MIN_VALUE;

        if (n == 0) {
            q = 0;
        } else {
            for (int i = 1; i <= n; i++) {
                q = Integer.max(q, p[i] + memoizedCutForAux(p, n - i, r));
            }
        }

        r[n] = q;
        return q;
    }

    /**
     * 自底向上计算
     *
     * @param p
     * @param n
     * @return
     */
    private static int bottomUpCutRod(int[] p,int n) {
        int[] r = new int[n+1];
        r[0] = 0;

        for (int j = 1;j <= n;j++) {
            int q = Integer.MIN_VALUE;
            for (int i = 1;i <=j;i++) {
                q = Integer.max(q,p[i] + r[j-i]);
            }
            r[j] = q;
        }

        return r[n];
    }

    private static void extendedBottomUpCutRod(int[] p,int n) {
        int[] r = new int[n+1];
        int[] s = new int[n+1];
        r[0] = 0;

        for(int j = 1;j <= n;j++) {
            int q = Integer.MIN_VALUE;
            for(int i = 1;i <=j;i++) {
                if(q < p[i] + r[j-i]) {
                    q = p[i] + r[j-i];
                    s[j] = i;
                }
            }
            r[j] = q;
        }

        System.out.println("r:" + r[n]);
        System.out.println("s:" + s[n]);

    }

    public static void main(String[] args) {
        System.out.println(cutRod(PROFIT, 4));
        System.out.println(memoizedCutRod(PROFIT, 4));
        System.out.println(bottomUpCutRod(PROFIT, 4));
        extendedBottomUpCutRod(PROFIT, 0);
        extendedBottomUpCutRod(PROFIT, 1);
        extendedBottomUpCutRod(PROFIT, 2);
        extendedBottomUpCutRod(PROFIT, 3);
        extendedBottomUpCutRod(PROFIT, 4);
        extendedBottomUpCutRod(PROFIT, 5);
        extendedBottomUpCutRod(PROFIT, 6);
    }
}
