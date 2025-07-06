package com.kguard.tobecomebetter.swea;

import java.util.Scanner;

// 1 가위 2 바위 3 보
public class SWEA1936 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        switch (a - b) {
            case -2:
            case 1:
                System.out.println("A");
                break;
            case -1:
            case 2:
                System.out.println("B");
                break;
        }
    }
}
