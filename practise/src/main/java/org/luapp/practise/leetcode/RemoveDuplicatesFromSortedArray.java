package org.luapp.practise.leetcode;

/**
 * Created by lum on 2015/3/19.
 */
public class RemoveDuplicatesFromSortedArray {

    public static int removeDuplicates(int[] A) {
        if (A == null) {
            return 0;
        }

        if (A.length < 2) {
            return A.length;
        }

        int i = 0,j = 1;
        while (j < A.length) {
            if (A[i] == A[j]) {
                j++;
            } else {
                i++;
                A[i] = A[j];
                j++;
            }
        }

        return i+1;
    }

    public static void print(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }

        System.out.print(A[0]);
        for (int i = 1;i < A.length;i++) {
            System.out.print("->" + A[i]);
        }

        System.out.println();
    }

    public static void main(String[] args) {
        int[] A = new int[]{1, 1, 2};
        print(A);
        System.out.println(removeDuplicates(A));
        print(A);

        A = new int[]{1, 1, 2,3,4,5,6,7,7,8,9,10,10,11,11,11,12};
        print(A);
        System.out.println(removeDuplicates(A));
        print(A);
    }
}
