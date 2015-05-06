package org.luapp.practise.alg;

/**
 * Created by lum on 2015/5/5.
 */
public class QuickSortBasic {

    public void sort(int[] input) {
        sort(input, 0, input.length - 1);
    }

    private void sort(int[] input, int lowIndex, int highIndex) {
        if (highIndex <= lowIndex) {
            return;
        }
        int partition = partition(input, lowIndex, highIndex);
        sort(input, lowIndex, partition - 1);
        sort(input, partition + 1, highIndex);
    }

    private int partition(int[] input, int lowIndex, int highIndex) {
        int i = lowIndex;
        int j = highIndex;
        int key = input[lowIndex];

        while (i < j) {
            while (input[j] >= key && j > i) {
                j--;
            }
            input[i] = input[j];

            while (input[i] <= key && i < j) {
                i++;
            }
            input[j] = input[i];
        }
        input[i] = key;
        return i;
    }

    public static void print(int[] input) {
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i] + "  ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] input = new int[]{11, 2, 4, 3, 5, 6, 9, 10};
        QuickSortBasic sorter = new QuickSortBasic();
        sorter.sort(input);
        print(input);
    }
}
