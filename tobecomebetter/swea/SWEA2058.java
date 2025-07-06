package com.kguard.tobecomebetter.swea;

import java.util.Arrays;
import java.util.Scanner;

public class SWEA2058 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.next();
        int[] h = Arrays.stream(n.split("")).mapToInt(Integer::parseInt).toArray();
        int sum = 0;
        for (int i : h)
            sum += i;
        System.out.println(sum);
    }
}
