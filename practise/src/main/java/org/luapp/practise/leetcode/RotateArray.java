package org.luapp.practise.leetcode;

/**
 * Created by lum on 2015/3/12.
 */
public class RotateArray {

    public static void rotate(int[] nums, int k) {
        int temp;

        k = k % nums.length;

        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        for (int i = 0, j = k - 1; i < j; i++, j--) {
            temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        for (int i = k, j = nums.length - 1; i < j; i++, j--) {
            temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

    }

    public static void print(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i != 0) {
                System.out.print(",");
            }
            System.out.print(nums[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        print(nums);
        rotate(nums, 3);
        print(nums);

        nums = new int[]{-1};
        print(nums);
        rotate(nums, 2);
        print(nums);

    }
}
