package com.ylab.task1;

import java.util.Date;

public class Task1 {
    private final static int MULTIPLIER_A = (-1) * (int) new Date().getTime();
    private final static int INCREMENT_C = 12345;
    private final static int MODULUS = 2147483647;
    private static int seed = 3819201;

    public static int randomInt() {
        seed = (seed * MULTIPLIER_A + INCREMENT_C) % MODULUS;
        return seed;
    }

    public static int randomPositiveInt() {
        int tmp = randomInt();
        if (tmp < 0) {
            seed = tmp * -1;
        }
        return seed;
    }

    public static void main(String[] args) {
        int[][] firstArray = new int[5][5];
        double averageNumber = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                firstArray[i][j] = randomPositiveInt();
            }
        }

        System.out.println("Array : ");
        print(firstArray);
        sort(firstArray);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                averageNumber += firstArray[i][j];
            }
        }

        averageNumber = averageNumber / 25;
        System.out.println("Minimum number: " + firstArray[0][0]);
        System.out.printf("Average number: %.2f", averageNumber);
        System.out.println("\nMaximum number: " + firstArray[4][4]);
    }

    public static void sort(int[][] arr) {
        int i, j, temp;
        int n = arr.length;
        int m = arr[0].length;
        for (i = 0; i < n * m - 1; ++i) {
            for (j = 0; j < n * m - 1 - i; ++j) {
                if (arr[j / m][j % m] > arr[(j + 1) / m][(j + 1) % m]) {
                    temp = arr[(j + 1) / m][(j + 1) % m];
                    arr[(j + 1) / m][(j + 1) % m] = arr[j / m][j % m];
                    arr[j / m][j % m] = temp;
                }
            }
        }
    }

    public static void print(int arr[][]) {
        int i, j;
        int n = arr.length;
        int m = arr[0].length;
        for (i = 0; i < n; ++i) {
            for (j = 0; j < m; ++j) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

}

