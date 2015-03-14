package org.luapp.practise.leetcode;

/**
 * Created by lumeng on 2015/3/14.
 */
public class SearchInsertPosition {

    public static int searchInsert(int[] A, int target) {
        int low =0;
        int high = A.length - 1;

        while ( low <= high) {
            int mid = (high+low) >>> 1;
            int v = A[mid];
            if (v == target) {
                return mid;
            } else if (v > target) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }

        return low;
    }

    public static void main(String[] args) {

        int[] a = new int[]{1,3,5,6};

        System.out.println(searchInsert(a,6));
        System.out.println(searchInsert(a,5));
        System.out.println(searchInsert(a,2));
        System.out.println(searchInsert(a,7));
        System.out.println(searchInsert(a,0));

        a = new int[]{1};

        System.out.println(searchInsert(a,6));
        System.out.println(searchInsert(a,5));
        System.out.println(searchInsert(a,2));
        System.out.println(searchInsert(a,7));
        System.out.println(searchInsert(a,0));

    }
}
