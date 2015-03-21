package org.luapp.practise.leetcode;

/**
 * Created by lumeng on 2015/3/21.
 */
public class RemoveElement {

    public static int removeElement(int[] A, int elem) {

        if (A == null || A.length == 0) {
            return 0;
        }

        if (A.length == 1) {
            if (A[0] == elem) {
                return 0;
            } else {
                return 1;
            }
        }

        int i = 0,j = A.length - 1;

        while(i<= j) {
            if (A[i] == elem) {
                for(int k = i;k < j;k++) {
                    A[k] = A[k+1];
                }
                j--;
            } else {
                i++;
            }
        }

        return j + 1;
    }

    public static void main(String[] args) {
//        System.out.println(removeElement(new int[]{1},1));
        System.out.println(removeElement(new int[]{3,3,3},3));
//        System.out.println(removeElement(new int[]{1,2,3,4,5,6,3},3));
    }
}
