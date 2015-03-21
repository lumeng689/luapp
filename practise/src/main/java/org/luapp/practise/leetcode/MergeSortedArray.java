package org.luapp.practise.leetcode;

/**
 * Created by lumeng on 2015/3/21.
 */
public class MergeSortedArray {

    public static void merge(int A[], int m, int B[], int n) {
        int i = 0, j = 0;

        while (i < m && j < n) {
            if (A[i] <= B[j]) {
                i++;
            } else {
                for (int k = m; k > i; k--) {
                    A[k] = A[k - 1];
                }
                A[i] = B[j];
                i++;
                m++;
                j++;
            }
        }

        if (j < n) {
            for (int k = 0; k < n - j; k++) {
                A[m + k] = B[j + k];
            }
        }

    }

//    public static void print()

    public static void main(String[] args) {
        merge(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 9, new int[]{2, 4, 6, 8, 10}, 5);
    }
}
