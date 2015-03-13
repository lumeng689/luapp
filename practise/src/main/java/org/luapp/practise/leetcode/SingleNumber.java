package org.luapp.practise.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lum on 2015/3/13.
 */
public class SingleNumber {

    public static int singleNumber(int[] A) {
        int ans = 0;
        for (int i =0;i <A.length;i++) {
            ans ^= A[i];
        }

        return ans;
    }

    public static int singleNumber2(int[] A) {

        Map<Integer, Integer> s = new HashMap<Integer, Integer>();
        for (int i = 0; i < A.length; i++) {
            Integer cnt = s.get(A[i]);
            if (cnt == null) {
                s.put(A[i], 1);
            } else {
                s.put(A[i], cnt + 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : s.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return -1;
    }

    public static int singleNumber3(int[] A) {

        boolean found = false;
        for (int i = 0; i < A.length; i++) {
            found = false;
            for (int j = 0; j < A.length; j++) {
                if (i != j && A[i] == A[j]) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                return A[i];
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println(singleNumber(a));
        System.out.println(singleNumber2(a));
        System.out.println(singleNumber3(a));
    }
}
