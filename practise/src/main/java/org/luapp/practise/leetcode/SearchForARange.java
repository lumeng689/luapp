package org.luapp.practise.leetcode;

/**
 * Created by lumeng on 2015/3/14.
 */
public class SearchForARange {

    public static int[] searchRange(int[] A, int target) {
        int[] result = {-1, -1};

        if (A == null || A.length == 0) {
            return result;
        }

        if (A.length == 1) {
            if(A[0] == target) {
                return new int[]{0,0};
            } else {
                return result;
            }
        }

        int low = 0;
        int high = A.length - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int val = A[mid];

            if (val > target) {
                high = mid - 1;
            } else if (val < target) {
                low = mid + 1;
            } else {
                if (mid + 1 >= A.length || A[mid + 1] > target) {
                    result[1] = mid;
                    high = mid - 1;
                } else if (mid - 1 < 0 || A[mid - 1] < target) {
                    result[0] = mid;
                    low = mid + 1;
                } else {
                    high = mid + 1;
                    low = mid -1;;
                }
            }
        }

        return result;
    }


    public static void main(String[] args) {

        int[] a = new int[]{5, 7, 7, 8, 8, 10};
        int[] ret = searchRange(a, 8);
        System.out.println(ret[0] + "," + ret[1]);

        a = new int[]{1};
        ret = searchRange(a, 0);
        System.out.println(ret[0] + "," + ret[1]);

        a = new int[]{1};
        ret = searchRange(a, 1);
        System.out.println(ret[0] + "," + ret[1]);

        a = new int[]{3,3,3};
        ret = searchRange(a, 3);
        System.out.println(ret[0] + "," + ret[1]);
    }
}
