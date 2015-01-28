package org.luapp.practise.leetcode;

/**
 * Created by lumeng on 2015/1/27.
 */
public class MajorityElement {

    public static int majorityElement(int[] num) {
        int idx = 0;
        int i = 1;
        int cnt = 1;
        int n = num.length / 2;

        while (i < num.length) {
            if (num[idx] == num[i]) {
                cnt++;
                i++;
            } else {
                // 如果不同，就把计数器-1，因为占比超过n/2，所以总会剩下最多的
                cnt--;
                if (cnt == 0) {
                    idx = i+1;
                    i = i + 2;
                    // 此处要把cnt设置为1，因为选中的idx的值后就相当于有1个该元素了，再出现就是有2个元素
                    cnt = 1;
                } else {
                    // 与下一个元素比较
                    i++;
                }
            }
        }

        return num[idx];
    }

    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{1, 2, 1, 2, 1}));
        System.out.println(majorityElement(new int[]{1, 1, 1, 2, 2}));
        System.out.println(majorityElement(new int[]{2, 2, 2,1,1,1,1}));
        System.out.println(majorityElement(new int[]{10,9,9,9,10}));
    }
}
