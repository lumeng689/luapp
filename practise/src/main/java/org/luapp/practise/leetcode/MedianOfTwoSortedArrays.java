package org.luapp.practise.leetcode;

/**
 * Created by lumeng on 2015/3/29.
 */
public class MedianOfTwoSortedArrays {

    public static double findMedianSortedArrays(int A[], int B[]) {
        boolean isANull = A == null || A.length == 0;
        boolean isBNull = B == null || B.length == 0;

        if (isANull && isBNull) {
            return 0.0;
        }

        if (isANull) {
            return B[B.length >> 1];
        }

        if (isBNull) {
            return B[B.length >> 1];
        }

        int total = A.length + B.length;
        // 奇数
        if (total % 2 == 1) {
            return findKth(A, 0, A.length, B, 0, B.length, total >> 1 + 1);
        } else {
            return (findKth(A, 0, A.length, B, 0, B.length, total >> 1) + findKth(A, 0, A.length, B, 0, B.length, (total >> 1) + 1)) / 2;
        }
    }

    public static double findKth(int A[], int startA, int endA, int B[], int startB, int endB, int k) {
        int m = endA - startA;
        int n = endB - startB;

        if (m > n) {
            findKth(B, startB, endB, A, startA, endA, k);
        }

        if (m == 0) {
            return B[startB + k - 1];
        }

        if (k == 1) {
            return Math.min(A[startA], B[startB]);
        }

        int pa = Math.min(k >> 1, m);
        int pb = k - pa;

        if (A[startA + pa - 1] < B[startB + pb - 1]) {
            return findKth(A, startA + pa, endA, B, startB, endB, k - pa);
        } else if (A[startA + pa - 1] > B[startB + pb - 1]) {
            return findKth(A, startA, endA, B, startB + pb, endB, k - pb);
        }

        return A[startA + pa - 1];
    }

    public static double findMedianSortedArrays1(int A[], int B[]) {

        boolean isANull = A == null || A.length == 0;
        boolean isBNull = B == null || B.length == 0;

        if (isANull && isBNull) {
            return 0.0;
        }

        if (isANull) {
            return B[B.length >> 1];
        }

        if (isBNull) {
            return B[B.length >> 1];
        }

        int[] C = new int[A.length + B.length];

        int i = 0, j = 0, k = 0;
        while (i < A.length && j < B.length) {
            if (A[i] < B[j]) {
                C[k] = A[i];
                i++;
            } else {
                C[k] = B[j];
                j++;
            }
            k++;
        }

        if (i < A.length) {
            for (; i < A.length; i++) {
                C[k] = A[i];
                k++;
            }
        }

        if (j < B.length) {
            for (; j < B.length; j++) {
                C[k] = B[j];
                k++;
            }
        }

        return C[C.length >> 1];
    }

    public static double findMedianSortedArrays2(int A[], int B[]) {

        boolean isANull = A == null || A.length == 0;
        boolean isBNull = B == null || B.length == 0;

        if (isANull && isBNull) {
            return 0.0;
        }

        if (isANull) {
            return B[B.length >> 1];
        }

        if (isBNull) {
            return B[B.length >> 1];
        }

        int mid = (A.length + B.length) >> 1;

        int i = 0, j = 0, cnt = 0;
        while (i < A.length && j < B.length) {
            if (A[i] < B[j]) {
                if (cnt++ == mid) {
                    return A[i];
                }
                i++;
            } else {
                if (cnt++ == mid) {
                    return B[j];
                }
                j++;
            }
        }

        if (cnt < mid && i < A.length) {
            return A[i + mid - cnt];
        }
        return B[j + mid - cnt];
    }

    public static void main(String[] args) {
//        System.out.println(findMedianSortedArrays(new int[]{1, 3, 5, 7}, new int[]{2, 4, 6}));
//        System.out.println(findMedianSortedArrays(new int[]{1, 3, 5, 7}, new int[]{2, 4, 6, 8}));
//        System.out.println(findMedianSortedArrays(new int[]{1, 3, 5, 7}, new int[]{2, 4, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21}));
        System.out.println(findMedianSortedArrays(new int[]{1, 3, 5, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20}, new int[]{2, 4, 6, 8}));
//        System.out.println(findMedianSortedArrays1(new int[]{1, 3, 5, 7}, new int[]{2, 4, 6}));
//        System.out.println(findMedianSortedArrays1(new int[]{1, 3, 5, 7}, new int[]{2, 4, 6, 8}));
//        System.out.println(findMedianSortedArrays1(new int[]{1, 3, 5, 7}, new int[]{2, 4, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21}));
//        System.out.println(findMedianSortedArrays1(new int[]{1, 3, 5, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20}, new int[]{2, 4, 6, 8}));
//        System.out.println(findMedianSortedArrays2(new int[]{1, 3, 5, 7}, new int[]{2, 4, 6}));
//        System.out.println(findMedianSortedArrays2(new int[]{1, 3, 5, 7}, new int[]{2, 4, 6, 8}));
//        System.out.println(findMedianSortedArrays2(new int[]{1, 3, 5, 7}, new int[]{2, 4, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21}));
//        System.out.println(findMedianSortedArrays2(new int[]{1, 3, 5, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20}, new int[]{2, 4, 6, 8}));
    }
}
