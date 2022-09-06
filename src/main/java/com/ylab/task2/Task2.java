package com.ylab.task2;

import java.util.Arrays;

public class Task2 {
    public static void main(String[] args) {
        int[] array = {30, 410, 15, 770, 830, 770, 15, -300};

        System.out.println("Before sorting: ");
        for (int i : array) System.out.print(i + " ");
        bubbleSort(array);
        System.out.println("\n\nAfter sorting: ");
        for (int i : array) System.out.print(i + " ");

        if (!Arrays.equals(array, new int[]{-300, 15, 15, 30, 410, 770, 770, 830})) {
            throw new RuntimeException("It's not the true answer");
        }
    }

    public static void bubbleSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }
}
