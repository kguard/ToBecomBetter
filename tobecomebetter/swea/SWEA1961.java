package com.kguard.tobecomebetter.swea;

import java.util.Scanner;

public class SWEA1961 {

    private static String[][] turn90(String[][] list) { // main이 static이라서
        String[][] answer = new String[list.length][list.length];
        for (int i = 0; i < list.length; i++)
            for (int j = 0; j < list.length; j++)
                answer[i][j] = list[list.length - j - 1][i];
        return answer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int t = 1; t <= tc; t++) {
            int s = sc.nextInt();
            String[][] list = new String[s][s];
            for (int i = 0; i < s; i++)
                for (int j = 0; j < s; j++)
                    list[i][j] = sc.next();

//            String[][] answer = new String[s][3];
//            for (int i = 0; i < 3; i++) {
//                switch (i) {
//                    case 0:
//                        for (int j = 0; j < s; j++) {
//                            String k = "";
//                            for (int y = s - 1; y >= 0; y--)
//                                k += list[y][j];
//                            answer[j][i] = k;
//                        }
//                        break;
//
//                    case 1:
//
//                        for (int j = 0; j < s; j++) {
//                            String k = "";
//                            for (int y = s - 1; y >= 0; y--)
//                                k += list[s - j - 1][y];
//                            answer[j][i] = k;
//                        }
//                        break;
//                    case 2:
//
//                        for (int j = 0; j < s; j++) {
//                            String k = "";
//                            for (int y = 0; y < s; y++)
//                                k += list[y][s - j - 1];
//                            answer[j][i] = k;
//                        }
//                        break;
//                }
//
//            }  // 기존의 문제 해결 방식
//            System.out.println("#" + t);
//            for (int i = 0; i < s; i++) {
//                for (int j = 0; j < 3; j++)
//                    System.out.print(answer[i][j] + " ");
//                System.out.println();
//            } // 기존 해결 방법

            String[][] r90 = turn90(list);
            String[][] r180 = turn90(r90);
            String[][] r270 = turn90(r180);

            System.out.println("#" + t);
            for (int i = 0; i < s; i++) {
                for (int j = 0; j < s; j++) {
                    System.out.print(r90[i][j]);
                }
                System.out.print(" ");
                for (int j = 0; j < s; j++) {
                    System.out.print(r180[i][j]);
                }
                System.out.print(" ");
                for (int j = 0; j < s; j++) {
                    System.out.print(r270[i][j]);
                }
                System.out.println();
            }

        }

    }

}
