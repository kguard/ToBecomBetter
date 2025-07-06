package com.kguard.tobecomebetter.swea;

import java.util.Scanner;

public class SWEA12712 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int t = 1; t <= tc; t++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] list = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    list[i][j] = sc.nextInt();
            int answer = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int cross = 0;
                    int straight = 0;
                    for (int k = -m + 1; k < m; k++) {
                        if (i + k >= 0 && i + k < n)
                            straight += list[i + k][j];
                        if (j + k >= 0 && j + k < n)
                            straight += list[i][j + k];
                        if (i + k >= 0 && i + k < n && j + k >= 0 && j + k < n)
                            cross += list[i + k][j + k];
                        if (i + k >= 0 && i + k < n && j - k >= 0 && j - k < n)
                            cross += list[i + k][j - k];
                    }
                    cross -= list[i][j];
                    straight -= list[i][j];
                    int max = Math.max(straight, cross);
                    answer = Math.max(answer, max);
                }
            }
            System.out.println("#" + t + " " + answer);
        }

    }
}
